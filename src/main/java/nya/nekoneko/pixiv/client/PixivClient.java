package nya.nekoneko.pixiv.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nya.nekoneko.pixiv.model.*;
import nya.nekoneko.pixiv.model.app.*;
import nya.nekoneko.pixiv.model.web.WebAjaxUrl;
import nya.nekoneko.pixiv.model.web.WebBody;
import nya.nekoneko.pixiv.model.web.WebSeriesNavData;
import nya.nekoneko.pixiv.model.web.WebTag;
import nya.nekoneko.pixiv.util.Call;
import nya.nekoneko.pixiv.util.PixivRequestFactory;
import nya.nekoneko.pixiv.util.TimeUtils;
import okhttp3.Request;
import org.noear.snack.ONode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ho
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class PixivClient {
    private volatile String accessToken;
    private volatile String refreshToken;
    private int expiresIn;
    private LocalDateTime updateTime;

    public PixivClient(String refreshToken) {
        this.refreshToken = refreshToken;
        this.refreshToken();
        this.startTask();
    }

    public String generateCodeVerifier() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
    }

    public String generateCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }

    /**
     * 获取登录URL
     */
    public PixivLoginItem getLoginUrl() {
        try {
            String codeVerifier = generateCodeVerifier();
            String codeChallenge = generateCodeChallenge(codeVerifier);
            String loginUrl = "https://app-api.pixiv.net/web/v1/login";
            loginUrl = loginUrl + "?code_challenge=" + codeChallenge + "&code_challenge_method=S256&client=pixiv-android";
            return new PixivLoginItem(codeVerifier, loginUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取token
     *
     * @param codeVerifier
     * @param code
     * @return
     */
    public void doLogin(String codeVerifier, String code) {
        Request request = PixivRequestFactory.getPixivRequest()
                .url("https://oauth.secure.pixiv.net/auth/token")
                .postForm(new HashMap<String, String>() {
                    {
                        put("client_id", "MOBrBDS8blbauoSck0ZfDbtuzpyT");
                        put("client_secret", "lsACyCD94FhDUtGTXi3QzcFE2uU1hqtDaKeqrdwj");
                        put("code", code);
                        put("code_verifier", codeVerifier);
                        put("grant_type", "authorization_code");
                        put("include_policy", "true");
                        put("redirect_uri", "https://app-api.pixiv.net/web/v1/users/auth/pixiv/callback");
                    }
                })
                .header("User-Agent", "PixivAndroidApp/5.0.234 (Android 11; Pixel 5)")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .buildRequest();
        String result = Call.doCallGetString(request);
        ONode node = ONode.loadStr(result);
        accessToken = node.get("access_token").getRawString();
        refreshToken = node.get("refresh_token").getRawString();
        expiresIn = node.get("expires_in").getInt();
        updateTime = LocalDateTime.now();
        printLoginInfo();
    }

    public void refreshToken() {
        Request request = PixivRequestFactory.getPixivRequest()
                .url("https://oauth.secure.pixiv.net/auth/token")
                .postForm(new HashMap<String, String>() {
                    {
                        put("client_id", "MOBrBDS8blbauoSck0ZfDbtuzpyT");
                        put("client_secret", "lsACyCD94FhDUtGTXi3QzcFE2uU1hqtDaKeqrdwj");
                        put("grant_type", "refresh_token");
                        put("refresh_token", refreshToken);
                    }
                })
                .header("User-Agent", "PixivAndroidApp/5.0.234 (Android 11; Pixel 5)")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .buildRequest();
        String result = Call.doCallGetString(request);
        ONode node = ONode.loadStr(result);
        accessToken = node.get("access_token").getRawString();
        refreshToken = node.get("refresh_token").getRawString();
        expiresIn = node.get("expires_in").getInt();
        updateTime = LocalDateTime.now();
        printLoginInfo();
    }

    public Illust getIllustDetail(int illustId) {
        //https://app-api.pixiv.net/v1/illust/detail?illust_id=10000000
        Request request = PixivRequestFactory.getPixivRequest()
                .url("https://app-api.pixiv.net/v1/illust/detail")
                .addParam("illust_id", illustId)
                .header("Authorization", "Bearer " + accessToken)
                .buildRequest();
        String result = Call.doCallGetString(request);
        ONode node = ONode.loadStr(result);
        ONode illustNode = node.get("illust");
        if (!illustNode.isNull()) {

            AppIllust appIllust = illustNode.toObject(AppIllust.class);
            LocalDateTime createDate = TimeUtils.toBeijingTime(illustNode.get("create_date").getRawString());
            appIllust.setCreateDate(createDate);
            //处理成Illust对象
            Illust.IllustBuilder illustBuilder = Illust.builder()
                    .id(appIllust.getId())
                    .title(appIllust.getTitle())
                    .type(convertType(appIllust.getType()))
                    .description(appIllust.getCaption())
                    .restrict(appIllust.getRestrict())
                    .userId(appIllust.getUser().getId())
                    .tools(appIllust.getTools())
                    .createDate(appIllust.getCreateDate())
                    .pageCount(appIllust.getPageCount())
                    .width(appIllust.getWidth())
                    .height(appIllust.getHeight())
                    .sanityLevel(appIllust.getSanityLevel())
                    .xRestrict(appIllust.getXRestrict())
                    .viewCount(appIllust.getTotalView())
                    .bookmarkCount(appIllust.getTotalBookmarks())
                    .visible(appIllust.getVisible())
                    .commentCount(appIllust.getTotalComments())
                    .aiType(appIllust.getIllustAiType())
                    .illustBookStyle(appIllust.getIllustBookStyle())
                    .commentOff(appIllust.getCommentAccessControl())
                    .appRaw(node.toJson());
            //总体状态
            if (appIllust.getRestrict() != 0 || !appIllust.getVisible()) {
                illustBuilder.state(-1);
            } else {
                illustBuilder.state(0);
            }
            //处理url
            List<ImageUrl> list = new ArrayList<>();
            if (1 == appIllust.getPageCount()) {
                AppImageUrl imageUrls = appIllust.getImageUrls();
                ImageUrl imageUrl = ImageUrl.builder()
                        .squareMedium(
                                ImageUrlDetail.builder()
                                        .illustId(illustId)
                                        .index(0)
                                        .type(1)
                                        .url(imageUrls.getSquareMedium())
                                        .state(0)
                                        .build()
                        )
                        .medium(
                                ImageUrlDetail.builder()
                                        .illustId(illustId)
                                        .index(0)
                                        .type(2)
                                        .url(imageUrls.getMedium())
                                        .state(0)
                                        .build()
                        )
                        .large(
                                ImageUrlDetail.builder()
                                        .illustId(illustId)
                                        .index(0)
                                        .type(3)
                                        .url(imageUrls.getLarge())
                                        .state(0)
                                        .build()
                        )
                        .original(ImageUrlDetail.builder()
                                .illustId(illustId)
                                .index(0)
                                .type(5)
                                .url(appIllust.getMetaSinglePage().getOriginalImageUrl())
                                .state(0)
                                .build())
                        .build();
                list.add(imageUrl);
            } else {
                List<AppMetaPages> metaPages = appIllust.getMetaPages();
                int index = 0;
                for (AppMetaPages metaPage : metaPages) {
                    AppImageUrl imageUrls = metaPage.getImageUrls();
                    ImageUrl imageUrl = ImageUrl.builder()
                            .squareMedium(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(1)
                                            .url(imageUrls.getSquareMedium())
                                            .state(0)
                                            .build()
                            )
                            .medium(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(2)
                                            .url(imageUrls.getMedium())
                                            .state(0)
                                            .build()
                            )
                            .large(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(3)
                                            .url(imageUrls.getLarge())
                                            .state(0)
                                            .build()
                            )
                            .original(ImageUrlDetail.builder()
                                    .illustId(illustId)
                                    .index(index)
                                    .type(5)
                                    .url(imageUrls.getOriginal())
                                    .state(0)
                                    .build())
                            .build();
                    list.add(imageUrl);
                    index++;
                }
            }
            illustBuilder.urls(list);
            //处理用户
            AppUser user = appIllust.getUser();
            illustBuilder.user(User.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .account(user.getAccount())
                    .mediumProfileImageUrls(user.getProfileImageUrls().getMedium())
                    .build());
            //处理tag
            List<AppTag> tags = appIllust.getTags();
            List<Tag> tagList = new ArrayList<>();
            for (AppTag tag : tags) {
                tagList.add(Tag.builder()
                        .name(tag.getName())
                        .build());
            }
            illustBuilder.tags(tagList);
            //处理系列
            AppSeries series = appIllust.getSeries();
            if (null != series) {
                illustBuilder
                        .seriesId(series.getId())
                        .series(Series.builder()
                                .id(series.getId())
                                .title(series.getTitle())
                                .build());
            }
            return illustBuilder.build();
        } else {
            //{"error":{"user_message":"","message":"Rate Limit","reason":"","user_message_details":{}}}
            return Illust.builder()
                    .id(illustId)
                    .state(-1)
                    .appRaw(node.toJson())
                    .build();
        }

    }

    public Illust getIllustDetailWeb(int illustId) {
        Request request = PixivRequestFactory.getPixivRequest()
                .url("https://www.pixiv.net/ajax/illust/" + illustId)
                .get()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.56")
                .buildRequest();

        String result = Call.doCallGetString(request);
        ONode node = ONode.loadStr(result);
        if (!node.get("error").getRawBoolean()) {
            ONode bodyNode = node.get("body");

            WebBody body = bodyNode.toObject(WebBody.class);
            String createDate = bodyNode.get("createDate").getRawString();
            String uploadDate = bodyNode.get("uploadDate").getRawString();
            Illust.IllustBuilder builder = Illust.builder()
                    .id(body.getId())
                    .title(body.getTitle())
                    .type(body.getIllustType())
                    .description(body.getDescription())
                    .restrict(body.getRestrict())
                    .xRestrict(body.getXRestrict())
                    .userId(body.getUserId())
                    .createDate(TimeUtils.toBeijingTime(createDate))
                    .uploadDate(TimeUtils.toBeijingTime(uploadDate))
                    .pageCount(body.getPageCount())
                    .width(body.getWidth())
                    .height(body.getHeight())
                    .sanityLevel(body.getSl())
                    .viewCount(body.getViewCount())
                    .bookmarkCount(body.getBookmarkCount())
                    .commentCount(body.getCommentCount())
                    .likeCount(body.getLikeCount())
                    .aiType(body.getAiType())
                    .illustBookStyle(body.getIllustType())
                    .commentOff(body.getCommentOff())
                    .webRaw(node.toJson());
            //处理用户
            builder.user(User.builder()
                    .id(body.getUserId())
                    .name(body.getUserName())
                    .account(body.getUserAccount())
                    .build());
            //标签
            List<Tag> tagList = new ArrayList<>();
            List<WebTag> tags = body.getTags().getTags();
            for (WebTag tag : tags) {
                tagList.add(Tag.builder()
                        .name(tag.getTag())
                        .build());
            }
            builder.tags(tagList);
            //系列
            WebSeriesNavData seriesNavData = body.getSeriesNavData();
            if (null != seriesNavData) {
                builder
                        .seriesId(seriesNavData.getSeriesId())
                        .series(Series.builder()
                                .id(seriesNavData.getSeriesId())
                                .title(seriesNavData.getTitle())
                                .build())

                ;
            }
            //处理url
            //获取所有图片信息
            Request request2 = PixivRequestFactory.getPixivRequest()
                    .url("https://www.pixiv.net/ajax/illust/" + illustId + "/pages")
                    .get()
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.56")
                    .buildRequest();
            String s = Call.doCallGetString(request2);
            ONode node2 = ONode.loadStr(s);
            Boolean error = node2.get("error").getRawBoolean();
            if (!error) {
                List<WebAjaxUrl> body2 = node2.get("body").toObjectList(WebAjaxUrl.class);
                int index = 0;
                List<ImageUrl> list = new ArrayList<>();
                for (WebAjaxUrl webAjaxUrl : body2) {
                    ImageUrl imageUrl = ImageUrl.builder()
                            .thumbMini(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(0)
                                            .url(webAjaxUrl.getUrls().getThumbMini())
                                            .state(0)
                                            .build()
                            )
                            .medium(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(3)
                                            .url(webAjaxUrl.getUrls().getSmall())
                                            .state(0)
                                            .build()
                            )
                            .regular(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(5)
                                            .url(webAjaxUrl.getUrls().getRegular())
                                            .state(0)
                                            .build()
                            )
                            .original(
                                    ImageUrlDetail.builder()
                                            .illustId(illustId)
                                            .index(index)
                                            .type(6)
                                            .url(webAjaxUrl.getUrls().getOriginal())
                                            .height(webAjaxUrl.getHeight())
                                            .width(webAjaxUrl.getWidth())
                                            .state(0)
                                            .build()
                            )
                            .build();
                    list.add(imageUrl);
                    index++;
                }
                builder.urls(list);
                builder.state(0);
            } else {
                builder.state(-1);
            }
            return builder.build();
        } else {
            return Illust.builder()
                    .id(illustId)
                    .state(-1)
                    .webRaw(node.toJson())
                    .build();
        }
    }

    private Integer convertType(String type) {
//        if ()
        if ("illust".equals(type)) {
            return 0;
        }
        if ("manga".equals(type)) {
            return 1;
        }
        if ("ugoira".equals(type)) {
            return 2;
        }
        return -1;
    }

//    /**
//     * 获取标签详情
//     *
//     * @param tagName
//     */
//    public void getTagInfo(String tagName) {
//        //https://www.pixiv.net/ajax/search/tags/%E3%83%AA%E3%82%B3%E3%83%AA%E3%82%B9%E3%83%BB%E3%83%AA%E3%82%B3%E3%82%A4%E3%83%AB
//        Request request = PixivRequestFactory.getPixivRequest()
//                .url("https://www.pixiv.net/ajax/search/tags/" + tagName)
//                .header("Cookie", "")
//                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.56")
//                .buildRequest();
//        String result = Call.doCallGetString(request);
//        TagInfo t = ONode.loadStr(result).toObject(TagInfo.class);
//    }

    public void printLoginInfo() {
        log.info("==================");
        log.info("Pixiv登录信息：");
        log.info("accessToken：{}", accessToken);
        log.info("refreshToken：{}", refreshToken);
        log.info("更新时间：{}", TimeUtils.toDateTime(updateTime));
        Duration between = Duration.between(LocalDateTime.now(), updateTime);
        long seconds = between.toSeconds();
        long remain = expiresIn - seconds;
        if (remain > 0) {
            log.info("Token预计有效期剩余：{} 秒", remain);
        } else {
            log.info("Token可能已过期");
        }
    }

    public void startTask() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            log.info("自动刷新Token");
            refreshToken();
        }, 30, 30, TimeUnit.MINUTES);
    }

    @Data
    @AllArgsConstructor
    public static class PixivLoginItem {
        private String codeVerifier;
        private String loginUrl;
    }
}

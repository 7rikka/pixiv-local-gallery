package nya.nekoneko.pixiv.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nya.nekoneko.pixiv.model.Illust;
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
import java.util.Base64;
import java.util.HashMap;
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

    public Illust getIllustIdDetail(int illustId) {
        //https://app-api.pixiv.net/v1/illust/detail?illust_id=10000000
        //
        Request request = PixivRequestFactory.getPixivRequest()
                .url("https://app-api.pixiv.net/v1/illust/detail")
                .addParam("illust_id", illustId)
                .header("Authorization", "Bearer " + accessToken)
                .buildRequest();
        String result = Call.doCallGetString(request);
        ONode node = ONode.loadStr(result);
        log.info("json:{}", node.toJson());
        if (!node.get("illust").isNull()) {
            Illust illust = node.get("illust").toObject(Illust.class);
            LocalDateTime createDate = TimeUtils.toBeijingTime(node.get("illust").get("create_date").getRawString(), 9);
            illust.setCreateDate(createDate);
            illust.setRaw(node.toJson());
            return illust;
        } else {
            //{"error":{"user_message":"","message":"Rate Limit","reason":"","user_message_details":{}}}
            
            return null;
        }

    }

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

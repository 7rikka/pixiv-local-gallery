package nya.nekoneko.pixiv;

import nya.nekoneko.pixiv.model.illust.Illust;
import nya.nekoneko.pixiv.util.Call;
import nya.nekoneko.pixiv.util.PixivRequestFactory;
import nya.nekoneko.pixiv.util.TimeUtils;
import nya.nekoneko.pixiv.web.WebIllust;
import okhttp3.Request;
import org.noear.snack.ONode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Web {
    public static void main(String[] args) {
        String illustId = "34267793";
        Request request = PixivRequestFactory.getPixivRequest()
                .url("https://www.pixiv.net/artworks/"+illustId)
                .buildRequest();
        String s = Call.doCallGetString(request);
        Pattern r = Pattern.compile("<meta name=\"preload-data\" id=\"meta-preload-data\" content='(.*?)'>");
        Matcher matcher = r.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            String group = matcher.group(1);
            System.out.println(group);
            ONode node = ONode.loadStr(group);
            System.out.println(node.toString());
            ONode illustNode = node.get("illust");
            ONode userNode = node.get("user");
            ONode illustData = illustNode.get(illustId);
            WebIllust webIllust = illustData.toObject(WebIllust.class);
            System.out.println(webIllust);
//            System.out.println(webIllust.getIllustComment().equals(webIllust.getDescription()));
            System.out.println(webIllust.getIllustType());
            Illust.builder()
                    .id(webIllust.getId())
                    .title(webIllust.getTitle())
                    .caption(webIllust.getDescription())
                    .type(String.valueOf(webIllust.getIllustType()))
                    //createDate
                    //uploadDate
                    .restrict(webIllust.getRestrict())
                    .userId(Integer.valueOf(webIllust.getUserId()))
                    .createDate(TimeUtils.toBeijingTime(webIllust.getCreateDate(), 0))
                    .uploadDate(TimeUtils.toBeijingTime(webIllust.getUploadDate(),0))
                    .pageCount(webIllust.getPageCount())
                    .width(webIllust.getWidth())
                    .height(webIllust.getHeight())
                    .sanityLevel(webIllust.getSl())
                    .xRestrict(webIllust.getXRestrict())
                    .totalView(webIllust.getViewCount())
                    .totalBookmarks(webIllust.getBookmarkCount())
//                    .isBookmarked()
//                    .visible()
                    .build();

            //illust    = 0
            //manga     = 1
            //ugoira    = 2
        }
    }
}

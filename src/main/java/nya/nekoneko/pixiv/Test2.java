package nya.nekoneko.pixiv;

import nya.nekoneko.pixiv.client.PixivClient;
import nya.nekoneko.pixiv.model.illust.Illust;

public class Test2 {
    public static void main(String[] args) {
//        PixivClient client = new PixivClient("iOnxjjqUuM-7LCrfXWTshsr701uWtvmR0BRZlUgOsFA", "_DqciSKE18yE7OXcIu7dCnwccqTkB81VXV4RkG4Boco", 3600, LocalDateTime.now());
        //插画
//        client.getIllustIdDetail(10000000);
        //动图
//        client.getIllustIdDetail(99615360);
        //漫画
//        client.getIllustDetail(102932517);
        PixivClient client = new PixivClient("_DqciSKE18yE7OXcIu7dCnwccqTkB81VXV4RkG4Boco");
        Illust illustDetail = client.getIllustDetail(1000000);
        System.out.println(illustDetail);
    }
}

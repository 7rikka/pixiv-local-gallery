package nya.nekoneko.pixiv;

import nya.nekoneko.pixiv.client.PixivClient;

import java.time.LocalDateTime;

public class Test2 {
    public static void main(String[] args) {
        PixivClient client = new PixivClient("iOnxjjqUuM-7LCrfXWTshsr701uWtvmR0BRZlUgOsFA", "_DqciSKE18yE7OXcIu7dCnwccqTkB81VXV4RkG4Boco", 3600, LocalDateTime.now());
        //插画
//        client.getIllustIdDetail(10000000);
        //动图
//        client.getIllustIdDetail(99615360);
        //漫画
        client.getIllustIdDetail(102932517);
    }
}

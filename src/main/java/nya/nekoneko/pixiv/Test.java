package nya.nekoneko.pixiv;

import nya.nekoneko.pixiv.client.PixivClient;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        PixivClient client = new PixivClient();
        PixivClient.PixivLoginItem item = client.getLoginUrl();
        System.out.println(item.getLoginUrl());
        //获取code
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        client.doLogin(item.getCodeVerifier(), code);

    }
}

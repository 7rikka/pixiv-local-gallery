package nya.nekoneko.pixiv;

import org.noear.solon.Solon;

import java.util.TimeZone;

/**
 * 主程序入口
 *
 * @author Ho
 */
public class Main {
    public static void main(String[] args) {
//        System.setProperty("socksProxyHost", "127.0.0.1");
//        System.setProperty("socksProxyPort", "7890");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        Solon.start(Main.class, args);
    }
}
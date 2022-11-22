package nya.nekoneko.pixiv;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Test3 {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        2010-04-13T00:01:07+09:00
        System.out.println("当前本地时区："+ ZoneId.systemDefault());
        System.out.println("当前日期和时间：" + LocalDateTime.now());
        LocalDateTime parse = LocalDateTime.parse("2010-04-13 00:01:07", dateTimeFormatter);
        System.out.println(parse);
        LocalDateTime localDateTime = toUtcBeijing(parse);
        System.out.println(localDateTime);

    }

    public static LocalDateTime toUtcBeijing(LocalDateTime localDateTime){
        ZonedDateTime zonedtime = localDateTime.atZone(ZoneId.from(ZoneOffset.ofHours(9)));
        ZonedDateTime converted = zonedtime.withZoneSameInstant(ZoneOffset.ofHours(8));
        return converted.toLocalDateTime();
    }
}

package nya.nekoneko.pixiv.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String currentDate() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String currentTime() {
        return LocalTime.now().format(TIME_FORMAT);
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String currentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMAT);
    }

    /**
     * 转换为日期时间格式
     *
     * @param localDateTime
     * @return
     */
    public static String toDateTime(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(DATETIME_FORMAT);
    }

    /**
     * 转换为日期格式
     *
     * @param localDateTime
     * @return
     */
    public static String toDate(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(DATE_FORMAT);
    }

    /**
     * 转换为时间格式
     *
     * @param localDateTime
     * @return
     */
    public static String toTime(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(TIME_FORMAT);
    }

    public static LocalDateTime toBeijingTime(String time, int offset) {
        time = time.split("\\+")[0].replace("T", " ");
        LocalDateTime localDateTime = LocalDateTime.parse(time, DATETIME_FORMAT);
        ZonedDateTime zonedtime = localDateTime.atZone(ZoneId.from(ZoneOffset.ofHours(offset)));
        ZonedDateTime converted = zonedtime.withZoneSameInstant(ZoneOffset.ofHours(8));
        return converted.toLocalDateTime();
    }
    public static LocalDateTime toBeijingTime(LocalDateTime time, int offset) {
        System.out.println(time);
        ZonedDateTime zonedtime = time.atZone(ZoneId.from(ZoneOffset.ofHours(offset)));
        ZonedDateTime converted = zonedtime.withZoneSameInstant(ZoneOffset.ofHours(8));
        return converted.toLocalDateTime();
    }
}
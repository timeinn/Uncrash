package io.tooko.core.utils.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public interface DateFormatter {

    List<DateFormatter> SUPPORT_FORMATTER = new ArrayList<>(Arrays.asList(
        /*
         *常见格式
         * */
        // yyyyMMdd
        new DefaultDateFormatter(Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2}"), "yyyyMMdd")
        // yyyy-MM-dd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}"), "yyyy-MM-dd")
        // yyyy/MM/dd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}"), "yyyy/MM/dd")
        //yyyy年MM月dd日
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}年[0-9]{2}月[0-9]{2}日"), "yyyy年MM月dd日")
        //yyyy年M月d日
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}年[0-9]月[0-9]日"), "yyyy年M月d日")
        //yyyy年MM月d日
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}年[0-9]{2}月[0-9]日"), "yyyy年MM月d日")
        //yyyy年M月dd日
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}年[0-9]月[0-9]{2}日"), "yyyy年M月dd日")

        // yyyy-M-d
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]-[0-9]"), "yyyy-M-d")
        // yyyy-MM-d
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]"), "yyyy-MM-d")
        // yyyy-M-dd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]-[0-9]{2}"), "yyyy-M-dd")

        // yyyy/M/d
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]/[0-9]"), "yyyy/M/d")
        // yyyy/MM/d
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]"), "yyyy/MM/d")
        // yyyy/M/dd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]/[0-9]{2}"), "yyyy/M/dd")

        // yyyy-MM-dd HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy-MM-dd HH:mm:ss")
        // yyyy-M-d HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]-[0-9] [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy-M-d HH:mm:ss")
        // yyyy-M-d H:m:s
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]-[0-9] [0-9]:[0-9]:[0-9]"), "yyyy-M-d H:m:s")
        // yyyy-MM-d HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9] [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy-MM-d HH:mm:ss")
        // yyyy-M-dd HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy-M-dd HH:mm:ss")
        // yyyy-M-dd H:m:s
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]-[0-9]{2} [0-9]:[0-9]:[0-9]"), "yyyy-M-dd H:m:s")
        // yyyy-MM-d H:m:s
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9] [0-9]:[0-9]:[0-9]"), "yyyy-MM-d H:m:s")

        // yyyy/MM/dd HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy/MM/dd HH:mm:ss")
        // yyyy/M/d HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]/[0-9] [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy/M/d HH:mm:ss")
        // yyyy/M/d H:m:s
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]/[0-9] [0-9]:[0-9]:[0-9]"), "yyyy/M/d H:m:s")
        // yyyy/MM/d HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9] [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy/MM/d HH:mm:ss")
        // yyyy/M/dd HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy/M/dd HH:mm:ss")
        // yyyy/M/dd H:m:s
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]/[0-9]{2} [0-9]:[0-9]:[0-9]"), "yyyy/M/dd H:m:s")
        // yyyy/MM/d H:m:s
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9] [0-9]:[0-9]:[0-9]"), "yyyy/MM/d H:m:s")

        // yyyy-MM-dd HH:mm:ssZ
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\+[0-9]{4}"), "yyyy-MM-dd HH:mm:ssZ")
        //yyyy-MM-dd'T'HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyy-MM-dd'T'HH:mm:ss")
        //yyyy-MM-dd'T'HH:mm:ssZ
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\+[0-9]{4}"), "yyyy-MM-dd'T'HH:mm:ssZ")
        //yyyy年MM月dd日HH时mm分ss秒
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}年[0-9]{2}月[0-9]{2}日[0-9]{2}时[0-9]{2}分[0-9]{2}秒"), "yyyy年MM月dd日HH时mm分ss秒")
        //yyyy年MM月dd日 HH时mm分ss秒
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}年[0-9]{2}月[0-9]{2}日 [0-9]{2}时[0-9]{2}分[0-9]{2}秒"), "yyyy年MM月dd日 HH时mm分ss秒")
        // HH:mm:ss
        , new DefaultDateFormatter(Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}"), "HH:mm:ss")
        /*
         * 奇奇怪怪的格式
         * */
        // yyyyMMdd HH:mm:dd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"), "yyyyMMdd HH:mm:ss")
        // yyyyMMddHHmmdd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}"), "yyyyMMddHHmmss")
        // yyyyMMdd HHmmdd
        , new DefaultDateFormatter(Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2} [0-9]{2}[0-9]{2}[0-9]{2}"), "yyyyMMdd HHmmss")
    ));

    boolean support(String str);

    Date format(String str);

    String toString(Date date);

    String getPattern();

    static Date fromString(String dateString) {
        DateFormatter formatter = getFormatter(dateString);
        if (formatter != null) {
            return formatter.format(dateString);
        }
        return null;
    }

    static Date fromString(String dateString, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String toString(Date date, String format) {
        if (null == date) {
            return null;
        }
        for (DateFormatter formatter : SUPPORT_FORMATTER) {
            if (formatter.getPattern().equals(format)) {
                return formatter.toString(date);
            }
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(format));
    }

    static boolean isSupport(String dateString) {
        return !(dateString == null || dateString.length() < 4) && SUPPORT_FORMATTER.parallelStream().anyMatch(formatter -> formatter.support(dateString));
    }

    static DateFormatter getFormatter(String dateString) {
        if (dateString == null || dateString.length() < 4) {
            return null;
        }
        for (DateFormatter formatter : SUPPORT_FORMATTER) {
            if (formatter.support(dateString)) {
                return formatter;
            }
        }
        return null;
    }
}

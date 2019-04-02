package net.uncrash.core.utils.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class DefaultDateFormatter implements DateFormatter {

    private DateTimeFormatter formatter;

    private Predicate<String> predicate;

    private String formatterString;

    public DefaultDateFormatter(Pattern formatPattern, String formatter) {
        this(str -> formatPattern.matcher(str).matches(), DateTimeFormatter.ofPattern(formatter));
        this.formatterString = formatter;
    }

    public DefaultDateFormatter(Predicate<String> predicate, DateTimeFormatter formatter) {
        this.predicate = predicate;
        this.formatter = formatter;
    }

    @Override
    public boolean support(String str) {
        return predicate.test(str);
    }

    @Override
    public Date format(String str) {
        return Date.from(LocalDateTime.parse(str, formatter).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(getPattern()));
    }

    @Override
    public String getPattern() {
        return formatterString;
    }
}

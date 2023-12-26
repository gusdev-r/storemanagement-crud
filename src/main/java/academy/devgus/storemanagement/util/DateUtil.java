package academy.devgus.storemanagement.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public String formatLocalDateTImeToDataBaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss").format(localDateTime);
    }
}

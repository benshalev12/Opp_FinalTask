import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    static {
        DATE_FORMAT.setTimeZone(UTC_TIME_ZONE);
    }

    public static int convertDateToDays(String date) {
        try {
            Date parsedDate = DATE_FORMAT.parse(date);
            long diff = parsedDate.getTime();
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String convertDaysToDate(int days) {
        long ms = TimeUnit.DAYS.toMillis(days);
        Date date = new Date(ms);
        return DATE_FORMAT.format(date);
    }
}
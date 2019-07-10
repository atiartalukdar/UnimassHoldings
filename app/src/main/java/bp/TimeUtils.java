package bp;

import org.joda.time.Interval;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");

    public static int getDifferenceInDays(String date){
        int difference = 0;
        try {
            Date oldDate = dateFormat.parse(date);
            Interval interval = new Interval(  new Date().getTime(),oldDate.getTime());
            Period period = interval.toPeriod();
            difference = period.getDays();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return difference;
    }

}

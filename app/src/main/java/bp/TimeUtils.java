package bp;

import android.util.Log;

import org.joda.time.Interval;
import org.joda.time.Period;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    private static final String TAG =  "TimeUtils  Atiar - ";

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");


    public static int getDifferenceInDays(String date){
        int difference = -1;
        try {
            Date futureDate = dateFormat.parse(date);
            Date today = new Date();
            long diff =   futureDate.getTime() - today.getTime();
            int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
            if (numOfDays==0){
                return futureDate.getDay() == today.getDay() ? 0 : 1;
            }
            Log.e(TAG, "difference between today and " + date + " is = " + numOfDays);
            return numOfDays;

        } catch (ParseException e) {
            Log.e(TAG, "Error while generating date difference");
            e.printStackTrace();
        }
        return difference;
    }

}

package time;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhouliang
 * @since 2018-05-03 19:46
 **/
public class DateAndCalendarTest {
    public static void main(String[] args) {
        //今天的时间
        Date date = new Date();
        //昨天开始的时间
        Date start = getStartDate(date);
        //昨天结束的时间
        Date end = getEndDate(date);
        System.out.println(date);
        System.out.println(start);
        System.out.println(end);
    }

    static Date getStartDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    static Date getEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}

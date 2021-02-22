package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtil {

    public static final String DATE_FORMAT            = "yyyyMM";
    public static final String default_pattern        = "yyyy-MM-dd HH:mm:ss";
    public static final String default_ymd_pattern    = "yyyy-MM-dd";
    
    public static String defaultFormatYMDDate(Date date) {
        if (null == date) {
            return null;
        }
        return formatDate(date, default_ymd_pattern);
    }
    
    public static String defaultFormatDate(Date date) {
        if (null == date) {
            return null;
        }
        return formatDate(date, default_pattern);
    }
    
    public static String yearMonthFormatDate(Date date) {
    	if (null == date) {
    		return null;
    	}
    	return formatDate(date, DATE_FORMAT);
    }
    
    /**
     * 日期加减n月
     * 
     * @param date
     * @param addMonths
     * @return
     */
    public static Date addMonth(Date date, int addMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonths);
        Date nowDate = calendar.getTime();
        return nowDate;
    }

    /**
     * 日期加减n月
     * 
     * @param date
     * @param addMonths
     * @return
     */
    public static Date addWeek(Date date, int addMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, addMonths);
        Date nowDate = calendar.getTime();
        return nowDate;
    }
    
    public static String formatDate(Date date, String pattern) {
        if (null != date) {
            return DateFormatUtils.format(date, pattern);
        }
        return "";
    }
    
    /**
     * 指定日期格式
     * 
     * @param dates
     * @param pattern
     * @return
     */
    public static Date parseDate(String dates, String pattern) {
        if (StringUtils.isBlank(dates)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dates);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取给定日期的年份
     * 
     * @param date 给定日期
     * @return 给定日期的年份
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    
    /**
     * 取得当前日期是多少周
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 得到某年某周的第一天
     * 
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }
    
    public static Date getFirstDayOfWeek(Date date) {
        Calendar current = Calendar.getInstance();
        current.setTime(date);

        int dayOfWeek = current.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 6;
        } else {
            dayOfWeek = dayOfWeek - 1;
        }
        current.add(Calendar.DATE, -dayOfWeek);
        Calendar shortDate = new GregorianCalendar(current.get(Calendar.YEAR),
            current.get(Calendar.MONTH), current.get(Calendar.DATE), 0, 0, 0);
        return shortDate.getTime();
    }
    
    public static String getTimeFormat(long time){
        StringBuffer label = new StringBuffer();
        long hour = time / 3600000;
        time = time % 3600000;
        long min = time / 60000;
        time = time % 60000 / 1000;
        label.append("");
            if (hour > 0) {
                if (hour < 10) {
                    label.append("0");
                }
                label.append(hour);
                label.append("小时");
            }
            if (min > 0) {
                if (min < 10) {
                    label.append("0");
                }
                label.append(min);
                label.append("分钟");
            }
            if (time < 10) {
                label.append("0");
            }
            label.append(time);
            label.append("秒");
        return label.toString();
    }
    
}

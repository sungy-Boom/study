package com.daily.learn.json.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by SunGuiyong
 * on 2018/9/13.
 */
public class HandleDateUtils {

    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static Logger logger = LogManager.getLogManager().getLogger("HandleDateUtils");

    /**
     * String 转 Date
     *
     * @param time
     * @return
     */
    public static Date string2Date(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
        try {
            return formatter.parse(time);
        } catch (ParseException e) {
            logger.info("[时间转换]字符串转成Date时间出错 msg: " + e.getMessage());
            return null;
        }
    }

    /**
     * Date时间转成String类型
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);
        try {
            return format.format(date);
        } catch (Exception e) {
            logger.info("[时间转换]Date转成String类型出错 msg: " + e.getMessage());
            return null;
        }
    }

    /**
     * 时间转成unix时间戳
     *
     * @param date
     * @return
     */
    public static Long date2TimeStamp(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_PATTERN);
            String dateStr = sdf.format(date.getTime());
            return sdf.parse(dateStr).getTime() / 1000;
        } catch (Exception e) {
            logger.info("[时间转换]时间转成unix时间戳出错 msg: " + e.getMessage());
        }
        return null;
    }

    /**
     * 时间戳转Date 精确到毫秒
     *
     * @param timestamp
     * @return
     */
    public static Date timestamp2Date(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);
        long lt = new Long(timestamp);
        Date date = new Date(lt * 1000);
        try {
            return format.parse(format.format(date));
        } catch (ParseException e) {
            logger.info("[时间转换]时间戳转成Date类型出错 msg:{} " + e.getMessage());
            return null;
        }
    }

    /**
     * 获取下一天晚上24点
     *
     * @param nextDays 从今天开始往后多少天
     * @return 2018:-9-19 24:00:00
     */
    public static Date getNextDays(int nextDays) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, nextDays);

        return cal.getTime();
    }
}

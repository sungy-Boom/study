package com.daily.learn.jode_time;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class JodeTimeTest {

    public static void main(String[] args) {

        //空是会报np的
//        DateTime start = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(null);
        DateTime end = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2018-02-28 15:29:49");
        end = end.plusDays(1);
        System.out.println(end.toLocalDate().compareTo(end.toLocalDate()));
//        System.out.println(start.compareTo(end));
    }
}

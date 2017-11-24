package com.daily.study.jode_time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class JodeTimeTest {

    public static void main(String[] args) {

        DateTime start = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(null);
        DateTime end = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2017-11-24 15:29:49");
        System.out.println(start.compareTo(end));
    }
}

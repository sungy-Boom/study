package com.daily.learn.jode_time;


public class JodeTimeTest {

    public static void main(String[] args) {

        //空是会报np的
//        DateTime start = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(null);
        DateTime end = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2017-11-24 15:29:49");
//        System.out.println(start.compareTo(end));
    }
}

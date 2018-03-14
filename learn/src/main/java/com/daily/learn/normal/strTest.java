package com.daily.learn.normal;

import com.daily.learn.jode_time.JodeTimeTest;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Splitter.on;

/**
 * Created by SunGuiyong
 * on 2018/1/31.
 */
public class strTest {

    private static String str;

    public static void main(String[] args) {
//        System.out.println(str);
//        String str = "http://peiyou-pad-mgmt.oss-cn-beijing.aliyuncs.com/test/course_file/20171211/67100a42-b388-4a9b-9f35-d48919a70a77.mp4";
//        List<String> strList = Splitter.on("/").omitEmptyStrings().splitToList(str);
//        strList = strList.subList(2, strList.size());
//        str = Joiner.on("/").join(strList);
//        str = "/" + str;
//        System.out.println(str);
//        System.out.println(strList);
//        List<ExceptionTest> list = new ArrayList<>();
//        list.add(null);

//        listTest();

        List<TestEntity> list = new ArrayList<>();
        TestEntity t1 = new TestEntity("test");
        TestEntity t2 = new TestEntity("test1");
        TestEntity t3 = new TestEntity("test2");
        TestEntity t4 = new TestEntity("aaa");
        TestEntity t5 = new TestEntity("bbb");
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list = listChange(list);
        for (TestEntity entity : list) {
            System.out.print(entity.getTest() + "  ");
        }

        multiMapTest();

    }

    private static void listTest() {
        List<String> listTest_1 = new ArrayList<>();
        List<String> listTest = new ArrayList<>();
        List<String> list = new ArrayList<>();
        listTest.add("test");
        list.addAll(listTest_1);
        list.addAll(listTest);
        System.out.println(list);
    }

    //直接返回list并不可以，因为传入的list从新new了一个对象
    private static List<TestEntity> listChange(List<TestEntity> fuzzyList) {
        List<TestEntity> list = new ArrayList<>();
        List<TestEntity> sameNameList = new ArrayList<>();
        for (TestEntity item : fuzzyList) {
            if ("test".equals(item.getTest())) {
                sameNameList.add(item);
            } else if (item.getTest().startsWith("test")) {
                list.add(item);
            }
        }
        fuzzyList = new ArrayList<>();
        fuzzyList.addAll(sameNameList);
        fuzzyList.addAll(list);
        for (TestEntity entity : fuzzyList) {
            System.out.print(entity.getTest() + "  ");
        }
        System.out.println();
        return fuzzyList;
    }

    private static int[] daysArray = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void multiMapTest() {
        Multimap<String, String> map = HashMultimap.create();
        String start = "2018-02-03 00:00:00";
        String end = "2018-02-22 00:00:00";

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime1 = DateTime.parse(start, format);
        DateTime dateTime2 = DateTime.parse(end, format);
        System.out.println(dateTime2);
        System.out.println(dateTime1);
        System.out.println(dateTime2);
        System.out.println(dateTime1.toLocalDate().compareTo(dateTime2.toLocalDate()));
    }
}

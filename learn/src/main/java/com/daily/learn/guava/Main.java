package com.daily.learn.guava;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String str = "测试";
        System.out.println(str.length());
        strTest();
        splitTest();
        trimTest();
    }

    private static void strTest() {
        List<Integer> strList = new ArrayList<>();
        strList.add(1);
        strList.add(2);
        strList.add(3);
        strList.add(4);

        if (strList.contains(3)) {
            System.out.println("3");
        }
    }

    private static void splitTest() {
        String str = "Test.pdf";
        str = str.split("\\.")[0];
        System.out.println(str);
        str = "Test";
        str = str.split("\\.")[0];
        System.out.println("222   " + str);
    }

    private static void trimTest() {
        String str = "  Test  ff ";
        System.out.println(str.trim());
    }
}

package com.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        listTest();
//
//        boolean res = returnTest("test");
//        System.out.println(res);
//        res = returnTest("");
//        System.out.println(res);
        t();
    }

    private static void listTest() {

        List<String> str = new ArrayList<>();
        str.add("test1");
        System.out.println(str);
        str = new ArrayList<>();
        str.add("test2");
        System.out.println(str);
    }

    private static boolean returnTest(String str) {
        return "".equals(str);
    }

    private static void t() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
        }
    }
}
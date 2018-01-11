package com.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        listTest();
    }

    private static void listTest() {

        List<String> str = new ArrayList<>();
        str.add("test1");
        System.out.println(str);
        str = new ArrayList<>();
        str.add("test2");
        System.out.println(str);
    }
}
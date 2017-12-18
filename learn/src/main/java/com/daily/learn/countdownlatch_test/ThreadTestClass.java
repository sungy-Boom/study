package com.daily.learn.countdownlatch_test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTestClass {

    public List<String> sayOut(String fileName) {
        System.out.println("say out " + fileName);
        List<String> list = new ArrayList<>();
        list.add("out");
        return list;
    }

    public List<String> sayIn(String fileName) {
        System.out.println("say in " + fileName);
        List<String> list = new ArrayList<>();
        list.add("1");
        return list;
    }
}

package com.daily.learn.thread.countdownlatch_test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTestClass {

    public ThreadTestClass() {
    }

    private List<String> list = new ArrayList<>();

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
        this.list = list;
        return list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

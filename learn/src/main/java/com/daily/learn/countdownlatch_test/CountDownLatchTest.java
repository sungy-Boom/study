package com.daily.learn.countdownlatch_test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    private static CountDownLatch latch = new CountDownLatch(2);
    private static ExecutorService exec = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
//    outputTest(str());

        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        exec.execute(new SuggestThread("fileName", true, list));
        exec.execute(new SuggestThread("fileName", false, list1));

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());

        exec.shutdown();

    }

    public static class SuggestThread implements Runnable {

        private String fileName;
        public boolean flg;
        public ThreadTestClass t = new ThreadTestClass();
        public List<String> resultList;

        public SuggestThread(String fileName, boolean flg, List<String> resultList) {
            this.fileName = fileName;
            this.flg = flg;
            this.resultList = resultList;
        }

        @Override
        public void run() {
            if (flg) {
                resultList.addAll(t.sayOut(fileName));
            } else {
                resultList.addAll(t.sayIn(fileName));
            }
            latch.countDown();
        }
    }
}

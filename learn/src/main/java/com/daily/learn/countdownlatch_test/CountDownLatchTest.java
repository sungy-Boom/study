package com.daily.learn.countdownlatch_test;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * in http_client used countDownLatch
 */
public class CountDownLatchTest {

    private static CountDownLatch latch = new CountDownLatch(2);
    private static ExecutorService exec = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        //countDownLatch test 1
      /*  List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        exec.execute(new SuggestThread("fileName", true, list));
        exec.execute(new SuggestThread("fileName", false, list1));*/

        //countDownLatch test 2
        for (int j = 0; j < 5; j++) {
            latch = new CountDownLatch(2);
            for (int i = 0; i < 2; i++) {
                exec.execute(new ListTestThread());
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public static class ListTestThread implements Runnable {
        ListTestThread() {

        }


        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                List<String> testList = new ArrayList<>();
                testList.add("test" + i);
                listTest(testList);
            }
            latch.countDown();
        }
    }

    private static void listTest(List<String> list) {
        for (String item : list) {
            System.out.println(item);
        }
    }
}

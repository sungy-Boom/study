package com.daily.learn.thread.L191127;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @auth amos
 * @date 2019/11/27
 **/
public class ThreadPool {
    static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        class ThreadTest implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                }
            }
        }
    }
}

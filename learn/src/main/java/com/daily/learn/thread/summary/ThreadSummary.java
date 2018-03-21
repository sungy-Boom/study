package com.daily.learn.thread.summary;

/**
 * Created by SunGuiyong
 * on 2018/3/21.
 */
public class ThreadSummary implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "  " + Thread.currentThread().getPriority());
    }
}

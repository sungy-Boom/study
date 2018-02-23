package com.daily.learn.thread.java7.chapter2.part4.core;

import com.daily.learn.thread.java7.chapter2.part4.task.Job;
import com.daily.learn.thread.java7.chapter2.part4.task.PrintQueue;

/**
 * LockMain class of the example.
 */
public class LockMain {

    /**
     * LockMain method of the class. Run ten jobs in parallel that
     * send documents to the print queue at the same time.
     */
    public static void main(String args[]) {

        // Creates the print queue
        PrintQueue printQueue = new PrintQueue();

        // Creates ten Threads
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // Starts the Threads
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }

}

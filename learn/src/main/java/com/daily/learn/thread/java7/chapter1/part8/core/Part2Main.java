package com.daily.learn.thread.java7.chapter1.part8.core;

import com.daily.learn.thread.java7.chapter1.part8.event.Event;
import com.daily.learn.thread.java7.chapter1.part8.task.CleanerTask;
import com.daily.learn.thread.java7.chapter1.part8.task.WriterTask;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Part2Main class of the example. Creates three WriterTaks and a CleanerTask
 */
public class Part2Main {

    /**
     * Part2Main method of the example. Creates three WriterTasks and a CleanerTask
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // Creates the Event data structure
        Deque<Event> deque = new ArrayDeque<Event>();

        // Creates the three WriterTask and starts them
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
            thread.join();
        }

        // Creates a cleaner task and starts them
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
        System.out.println("thread end");
    }

}

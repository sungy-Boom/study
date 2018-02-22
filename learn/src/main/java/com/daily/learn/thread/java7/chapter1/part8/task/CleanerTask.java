package com.daily.learn.thread.java7.chapter1.part8.task;

import com.daily.learn.thread.java7.chapter1.part8.event.Event;

import java.util.Date;
import java.util.Deque;

/**
 * Class that review the Event data structure and delete
 * the events older than ten seconds
 */
public class CleanerTask extends Thread {

    /**
     * Data structure that stores events
     */
    private Deque<Event> deque;

    /**
     * Constructor of the class
     *
     * @param deque data structure that stores events
     */
    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        // Establish that this is a Daemon Thread
        setDaemon(true);
    }


    /**
     * Part2Main method of the class
     */
    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    /**
     * Method that review the Events data structure and delete
     * the events older than ten seconds
     *
     * @param date
     */
    private void clean(Date date) {
        long difference;
        boolean delete;

        System.out.println("守护线程");
        if (deque.size() == 0) {
            return;
        }
        System.out.println(deque.size());

        delete = false;
        do {
//            System.out.println("before clean ");
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if (difference > 1) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
                System.out.println("after clean");
            }
        } while (difference > 1);
        if (delete) {
            System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
        }
    }
}

package com.daily.learn.thread.summary;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SunGuiyong
 * on 2018/3/21.
 */
public class SummaryMain {

    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {

//        ThreadSummary summary = new ThreadSummary();
//        Thread t = new Thread(summary);
//        t.setPriority(1);
//        t.run();

//        byThread();
//        byService();
//        notUseStop();
//        lockAndSync();

        //使用lock锁，实现资源同步

        final Lock lock = new ReentrantLock();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "  " + new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(10);
    }

    //1~10
    //设置优先级，使用三个优先级
    private static void byThread() {
        ThreadSummary summary = new ThreadSummary();
//        Thread t;
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(summary);
            if (i % 5 == 0) {
                t.setPriority(Thread.MIN_PRIORITY);
            } else {
                t.setPriority(Thread.MAX_PRIORITY);
            }
            t.start();
        }
    }

    private static void byService() {
        for (int i = 0; i < 10; i++) {
            service.execute(new ThreadSummary());
        }
        service.shutdown();
    }

    //stop方法使得线程执行不完整
    private static void notUseStop1() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is a stop test");
            }
        };
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.stop();
    }

    //使用stop使得线程失去原子性
    private static void notUseStop() {
        NotUseStop notUseStop = new NotUseStop();
        Thread t = new Thread(notUseStop);
        t.start();
        for (int i = 0; i < 5; i++) {
            new Thread(notUseStop).start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.stop();
    }

    private static void lockAndSync() throws InterruptedException {
        System.out.println("========lock test=========");
        for (int i = 0; i < 3; i++) {
            service.execute(new LockTest());
        }
        TimeUnit.SECONDS.sleep(10);

        System.out.println("========synchronized test ========");
        for (int i = 0; i < 3; i++) {
            service.execute(new SynchronizedTest());
        }
        TimeUnit.SECONDS.sleep(10);
        service.shutdown();
    }
}

class NotUseStop implements Runnable {
    private int a = 0;

    @Override
    public void run() {
        synchronized (this) {
            a++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a--;
            String name = Thread.currentThread().getName();
            System.out.println(name + "  " + "a=  " + a);
        }
    }
}

class LockTest implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +
                    "  " + new Date());
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }
}

class SynchronizedTest implements Runnable {
    @Override
    public void run() {
        synchronized ("") {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                        "  " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

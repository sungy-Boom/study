package com.daily.learn.thread.summary;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SunGuiyong
 * on 2018/3/21.
 */
public class SummaryMain {

    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {

        //设置优先级，使用三个优先级
//        use2Priority();
//        use10Priority();
        //使用执行器框架运行线程
//        byService();
        //不适用stop结束线程
//        notUseStop();
        //使用lock锁和synchronized关键字
//        lockAndSync();
        //信号量方式控制资源同步
        semaphoreTest();

        //使用lock锁，实现资源同步
//        final Lock lock = new ReentrantLock();
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> {
//                try {
//                    lock.lock();
//                    Thread.sleep(1000);
//                    System.out.println(Thread.currentThread().getName() + "  " + new Date());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    lock.unlock();
//                }
//            }).start();
//        }
//        TimeUnit.SECONDS.sleep(10);

        //三种创建线程的方法
//        runThread();
    }

    //1~10
    //设置优先级，使用两个优先级
    private static void use2Priority() {
        ThreadSummary summary = new ThreadSummary();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(summary);
            if (i % 2 == 0) {
                t.setPriority(Thread.MIN_PRIORITY);
            } else {
                t.setPriority(Thread.MAX_PRIORITY);
            }
            t.start();
        }
    }

    //使用10个线程优先级
    private static void use10Priority() {
        ThreadSummary summary = new ThreadSummary();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(summary);
            t.setPriority(i + 1);
            t.start();
        }
    }

    //使用执行器框架运行线程
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

    //使用lock锁和synchronized关键字
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

    //多线程不同实现的启动方法
    private static void runThread() throws InterruptedException {
        ThreadTest test = new ThreadTest();
        test.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(test).start();
        TimeUnit.SECONDS.sleep(2);

        RunnableTest runnable = new RunnableTest();
        new Thread(runnable).start();
        TimeUnit.SECONDS.sleep(2);

        CallableTest callable = new CallableTest();
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future, "callable test").start();
        TimeUnit.SECONDS.sleep(2);
    }

    private static void semaphoreTest(){
        SemaphoreTest sema = new SemaphoreTest();
        for (int i = 0; i < 5; i++) {
            new Thread(sema).start();
        }
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

class ThreadTest extends Thread {
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

class RunnableTest implements Runnable {
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

class CallableTest implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        synchronized ("") {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                        "  " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}

//使用信号量
class SemaphoreTest implements Runnable {
    private Semaphore sema = new Semaphore(1);
    private volatile int a = 0;

    @Override
    public void run() {
        try {
            sema.acquire();
            a++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a--;
            String name = Thread.currentThread().getName();
            System.out.println(name + "  " + "a=  " + a);
        }catch (Exception e){
        }finally {
            sema.release();
        }
    }
}

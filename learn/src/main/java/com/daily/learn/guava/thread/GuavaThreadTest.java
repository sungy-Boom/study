package com.daily.learn.guava.thread;

import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by SunGuiyong
 * on 2018/1/15.
 */
public class GuavaThreadTest {

    private static ExecutorService exec = Executors.newFixedThreadPool(10);
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(exec);

    public static void main(String[] args) {
        //future
//        futureTest();
        //listenableFuture
//        listeningFutureTest();
        //completeFuture
//        completeFutureTest_1();
//        completeFutureTest_2();
        completableFutureTest_3();
    }

    /**
     * Java5 Future
     */
    private static void futureTest() {

        try {
            List<Future<String>> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final int j = i;

                Future<String> stringTask = exec.submit(() -> {
                    if (j == 3) {
                        Thread.sleep(5000);
                    }
                    return ("hello world" + j);
                });
                list.add(stringTask);
            }

            for (Future<String> item : list) {
                System.out.println(item.get());
            }
//            System.out.println(stringTask.get());
           /* while (true) {
                if (stringTask.isDone() && !stringTask.isCancelled()) {
                    String result = stringTask.get();
                    System.out.println("StringTask: " + result);
                    break;
                }
            }*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            exec.shutdown();
        }
            /*Future<Integer> future = exec.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 123;
                }
            });
            System.out.println(future.get());

            Future<Integer> future_1 = exec.submit(() -> 123456);
            System.out.println(future_1.get());

            Future<Future<Integer>> f_2 = exec.submit(() -> future);
            System.out.println(f_2.get().get());*/
    }

    /**
     * Guava ListenableFuture
     */
    private static void listeningFutureTest() {
       /* ListenableFuture<Integer> explosion = service.submit(() -> 123);

        Futures.addCallback(explosion, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failed");
            }
        });*/

        List<ListenableFuture<Integer>> futures = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            ListenableFuture<Integer> future = service.submit(new ThreadTest(i));
            Futures.addCallback(future, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer integer) {
                    System.out.println("success" + j);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("failed");
                }
            });
            futures.add(future);
        }

        try {
            ListenableFuture<List<Integer>> allList = Futures.allAsList(futures);
            System.out.println(allList.get());
            long end = System.currentTimeMillis();
            System.out.println("time:" + (end - start));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            service.shutdown();
        }
    }

    public static class ThreadTest implements Callable<Integer> {

        private Integer value = 0;

        ThreadTest(Integer value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(value);
            if (value == 3) {
                Thread.sleep(10000);
            }
            return this.value;
        }
    }

    /**
     * Java8 CompleteFuture
     * CompletableFuture并非一定要交给线程池执行才能实现异步
     */
    private static void completeFutureTest_1() {
        System.out.println("CompletableFuture");
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(() -> {
            System.out.println("a");
            timeSleep();
        }, exec);
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(() -> {
            System.out.println("b");
            timeSleep();
        });
        try {
            futureA.runAfterEither(futureB, () -> {
                System.out.println("c");
                timeSleep();
            }).get();
        } catch (Exception e) {
            System.out.println("get exception " + e.getMessage());
        }
    }


    /**
     * runAsync(Runnable runnable)
     * runAsync(Runnable runnable, Executor executor)
     * supplyAsync(Supplier<U> supplier)
     * supplyAsync(Supplier<U> supplier, Executor executor)
     * <p>
     * //future.get()在等待执行结果时，程序会一直block,如果调用complete就会直接返回结果，
     * //但是如果future已经返回了结果，再调用complete是没有用的
     * complete(T t)
     * completeExceptionally(Throwable ex)
     */
    private static void completeFutureTest_2() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf1");
            timeSleep();
            return "as";
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("cf2");
//                Thread.sleep(11000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return 1234;
        });
        // 如果cf2返回值还没有获取到，那么就会返回complete的值
        // 如果cf2结果已经获取到，就不会使用complete的值
        timeSleep();
        cf2.complete(111111);
        //cf2 如果等待时间短，会输出返回值
        // 等待时间长，并不会阻塞在这里
        cf2.thenApply(fn -> fn).thenAcceptAsync(re -> System.out.println(re));

//        cf2.thenApply(fn -> fn).thenAccept(re -> System.out.println(re));

        cf1.thenApply((fn) -> fn).thenAcceptAsync(re -> System.out.println(re));

        //为什么使用join？
        //因为默认是守护进程，如果想获取结果，需要人工阻塞
        cf1.join();

//        timeSleep();
    }

    private static void completableFutureTest_3() {
        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                if (j == 3) {
                    timeSleep();
                    System.out.println("test03");
                }
                return "test_" + j;
            });
            list.add(future);
        }

        timeSleep();
        for (CompletableFuture<String> item : list) {
            item.thenApply(f -> f).thenAcceptAsync(res -> System.out.println(res));
        }

        System.out.println("end");
    }

    private static void timeSleep() {
        try {
            Random random = new Random();
            int time = random.nextInt(10);
            time = 1;
            System.out.println("休眠时间 ：" + time);
            TimeUnit.SECONDS.sleep(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

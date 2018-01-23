package com.daily.learn.guava.thread;

import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by SunGuiyong
 * on 2018/1/15.
 */
public class GuavaThreadTest {

    private static ExecutorService exec = Executors.newFixedThreadPool(10);
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(exec);

    public static void main(String[] args) {
//        futureTest();
        listeningFutureTest();
    }

    private static void futureTest() {

        // 任务2
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            exec.shutdown();
        }

    }

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
                Thread.sleep(5000);
            }
            return this.value;
        }
    }
}

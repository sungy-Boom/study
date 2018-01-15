package com.daily.learn.guava.thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SunGuiyong
 * on 2018/1/15.
 */
public class GuavaThreadTest {
    public static List<String> createTickets() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("车票" + i);
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = createTickets();//获取车票

        List<ListenableFuture<Integer>> futures = Lists.newArrayList();
        ExecutorService pool = Executors.newFixedThreadPool(10);//定义线程数
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(pool);
        int j = 0;
        while (true) {
            int i = j;
            if (i >= list.size()) {
                break;
            }
            for (; i < 10; i++) {
                futures.add(executorService.submit(new Task(list.get(i))));
            }
            j = j + 10;
        }

        final ListenableFuture<List<Integer>> resultsFuture = Futures.successfulAsList(futures);
        try {//所有都执行完毕
            resultsFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("操作完毕");
            pool.shutdown();
        }
    }

    public static class Task implements Callable<Integer> {
        private String ticket;

        /**
         * 构造方法，用于参数传递
         *
         * @param ticket
         */
        public Task(String ticket) {
            this.ticket = ticket;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("已卖" + ticket);//执行卖票过程
            return 1;
        }
    }
}

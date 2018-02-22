package com.daily.learn.thread.java7.chapter1.part1;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by SunGuiyong
 * on 2018/2/22.
 */
public class Part1Main {

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        //用一个状态数组存10个线程的运行状态
        Thread.State[] states = new Thread.State[10];
        //10个线程设置优先级，5最高，5最低
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            //设置线程name
            threads[i].setName("Thread" + i);
        }

        //在线程未执行之前，将状态写入文档
        String str;
        for (int i = 0; i < 10; i++) {
            str = "Part2Main : status of thread " + i + " " + threads[i].getState();
            write(str);
            states[i] = threads[i].getState();
        }
        //执行线程
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        boolean finish = false;
        while (!finish) {
            for (int i = 0; i < 10; i++) {
                if (threads[i].getState() == Thread.State.TERMINATED) {
                    //线程执行后，写入文件
                    str = "Part2Main : id " + threads[i].getId() + " name" + threads[i].getName() + "\n"
                            + "Part2Main : Priority " + threads[i].getPriority() + "\n"
                            + "Part2Main : old state " + states[i] + "\n";

                    states[i] = threads[i].getState();
                    str += "Part2Main : new state " + states[i] + "\n"
                            + "Part2Main********************************************";
                    write(str);
                }
            }
            finish = true;
            for (int i = 0; i < 10; i++) {
                finish = finish & (threads[i].getState() == Thread.State.TERMINATED);
            }
        }

    }

    private static void write(String content) throws Exception {
        PrintWriter writer = new PrintWriter(new FileWriter(
                "learn/src/main/java/com/daily/learn/thread/java7/chapter1/part1/log.txt", true));
        writer.println(content);
        writer.close();
    }
}

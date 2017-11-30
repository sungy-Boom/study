package com.daily.study.code_standard;

import org.hibernate.jpa.criteria.expression.function.AggregationFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 改善java编程的151个建议
 *
 * @author Soul
 */
public class FifthArrNotCollection {

    public static void main(String[] args) {

        FifthArrNotCollection test = new FifthArrNotCollection();

        System.out.println("**********性能**********");
        test.compareEffectionByAdd();

        System.out.println();
        System.out.println("**********使用变长数组**********");
        test.useExtendCapacity();

        System.out.println();
        System.out.println("**********集合初始化大小**********");
        test.initSizeOfCollection();
    }

    /**
     * 10000以内，效率差别不大，数据越多，int[]效率越好
     */
    private final int MAX_SIZE = 10000;
    private List<Integer> intList = new ArrayList<>(MAX_SIZE);

    private int[] intArr = new int[MAX_SIZE];

    /**
     * 1. 效率比较
     */
    private void compareEffectionByAdd() {
        for (int i = 0; i < MAX_SIZE; i++) {
            intList.add(i);
        }
        long endTime;
        long startTime = System.currentTimeMillis();

        int sum = 0;
        //list
        for (int item : intList) {
            sum += item;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Integer run time " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        sum = 0;
        //array
        for (int i = 0; i < intArr.length; i++) {
            sum += intArr[i];
        }
        endTime = System.currentTimeMillis();
        System.out.println("array run time " + (endTime - startTime));
    }

    /**
     * 2. 使用变长数组
     */
    private void useExtendCapacity() {
        int[] arr = new int[10];

        arr = Arrays.copyOf(arr, 20);
        System.out.println(arr.length);
    }

    private final int MAX_SIZE_OF_COLLECTION = 1000000;
    /**
     * 使用list的时候给定一个初始的大小
     */
    private void initSizeOfCollection() {
        List<String> listTest1 = new ArrayList<>();
        List<String> listTest2 = new ArrayList<>();

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_SIZE_OF_COLLECTION; i++) {
            listTest1.add(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("no init size " + (endTime - startTime));


        startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_SIZE_OF_COLLECTION; i++) {
            listTest2.add(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("init size " + (endTime - startTime));
    }
}

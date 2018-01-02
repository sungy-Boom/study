package com.daily.learn.code_standard;

import java.util.*;

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

        System.out.println();
        System.out.println("**********基本数组转list**********");
        test.basicArrToList();

        System.out.println();
        System.out.println("**********list create by Arrays.asList can't change**********");
        test.userArraysAsList();

        System.out.println();
        System.out.println("**********list equals vector when it's elements is them same**********");
        test.listEqualSet();

        System.out.println();
        System.out.println("**********it will throw exception changing list when it's useing sublist**********");
        test.subListTest();

        System.out.println();
        System.out.println("**********CompareTest**********");
        test.compareTest();
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

    /**
     * 基本类型转数组
     */
    private void basicArrToList() {
        int[] arr = new int[]{1, 2, 3, 4, 2, 1};
        List list = Arrays.asList(arr);
        System.out.println(list.size());

        Integer[] arr1 = new Integer[]{123, 34, 2, 5};
        List list2 = Arrays.asList(arr1);
        System.out.println(list2.size());
    }

    /**
     * 直接通过Arrays.asList生成的List不可以修改
     */
    private void userArraysAsList() {
        List list = Arrays.asList();
//        list.add("t");
    }

    /**
     * 对于list来说，只要是实现了List的类，只要元素相同，就是想等的
     */
    private void listEqualSet() {
        List<String> list = new ArrayList<>();
        list.add("A");

        Vector<String> vector = new Vector<>();
        vector.add("A");

        System.out.println("does list equal to vector : " + list.equals(vector));
    }

    /**
     * 使用subList之后，不修改原列表
     */
    private void subListTest() {
        List<String> listTest = new ArrayList<>(Arrays.asList("123", "456", "789", "012"));

        List<String> subListTest = listTest.subList(0, 2);
//        listTest.add("A");
        listTest = Collections.unmodifiableList(listTest);
        subListTest.add("A");
        System.out.println(listTest);
        System.out.println(subListTest);
    }

    private void compareTest() {
        TestPerson[] test = new TestPerson[5];

        for (int i = 0; i < 5; i++) {
            test[i] = new TestPerson("name_" + i, i);
        }

        Arrays.sort(test);
        for (TestPerson item : test) {
            System.out.printf("%s  %d\n", item.name, item.age);
        }
        System.out.println();

       /* Comparator<TestPerson> compare = new Comparator<TestPerson>() {
            @Override
            public int compare(TestPerson o1, TestPerson o2) {
                return o1.name.compareTo(o2.name);
            }
        };*/

        Comparator<TestPerson> compare = (o1, o2) -> o1.name.compareTo(o2.name);

        Comparator<TestPerson> compare1 = Comparator.comparing(TestPerson::getName);

        Arrays.sort(test, compare1);
        for (TestPerson item : test) {
            System.out.printf("%s  %d\n", item.name, item.age);
        }
    }

    public class TestPerson implements Comparable<TestPerson> {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        TestPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(TestPerson o) {
            return o.age - this.age;
        }
    }
}

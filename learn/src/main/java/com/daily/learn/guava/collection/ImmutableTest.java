package com.daily.learn.guava.collection;

import com.google.common.collect.*;

import javax.jnlp.IntegrationService;
import java.util.*;

/**
 * Created by SunGuiyong
 * on 2017/12/27.
 * 不可变集合
 */
public class ImmutableTest {

    public static void main(String[] args) {
        ImmutableTest test = new ImmutableTest();
        test.createImmutable();
    }

    private void createImmutable() {
        Set<String> immutableSet = ImmutableSet.of("fdd", "fdd", "fdd", "tt");
        System.out.println("immutable set test : " + immutableSet);

        Multimap<String, Integer> immutableMap = ImmutableMultimap
                .of("k1", 1, "k2", 2, "k1", 5);
        System.out.println("immutable multimap test: " + immutableMap);

        List<String> immutableList = ImmutableList.of("t1", "t2", "t1", "t2");
        System.out.println("immutable list test: " + immutableList);

        List<String> list = new ArrayList<>(Arrays.asList("gg1", "gg2", "gg3"));
        Multimap<String, Integer> map = HashMultimap.create();
        map.put("test1", 1);
        map.put("test2", 3);

        immutableList = ImmutableList.copyOf(list);
        list.add("aaa");
        System.out.println("immutable list test: (add by copyOf) " + immutableList);
        immutableMap = ImmutableMultimap.copyOf(map);
        System.out.println("immutable map test: (add by copyOf) " + immutableMap);

        //immutableList.add("f");
        Table<String, String, String> immutableTable = ImmutableTable.of("r","c","v");
        System.out.println("immutable table test :" + immutableTable);
    }
}

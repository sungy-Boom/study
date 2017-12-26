package com.daily.learn.guava.string;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.*;

/**
 * Created by sunguiyong on 2017/12/26.
 * <p>
 * static Joiner on(String separator)
 * static Joiner on(char separator)
 * <p>
 * Joiner skipNulls()
 * Joiner useForNull(String nullText)
 * Joiner.MapJoiner withKeyValueSeparator(String keyValueSeparator)
 */
public class JoinerTest {

    public static void main(String[] args) {
        JoinerTest test = new JoinerTest();

        test.joinerTest1();
    }

    private void joinerTest1() {
        List<String> list1 = ImmutableList.of("data1", "data2", "1", "2");
        System.out.println("on(char separator) test : " + Joiner.on(',').join(list1));
        System.out.println("on(String separator) test : " + Joiner.on("--").join(list1));
//        list1.add("null");

        System.out.println();
        List<String> list2 = new ArrayList<>(Arrays.asList("1", "test", "4"));
        list2.add("null");
        list2.add("");
        System.out.println("test : " + Joiner.on("--").join(list2));
        System.out.println("skipNulls() test : " + Joiner.on("--").skipNulls().join(list2, null));
        System.out.println("useForNull() test : " + Joiner.on("--").useForNull("this is a null").join(list2, null));

        System.out.println();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Joiner.MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=");
        System.out.println("withKeyValueSeparator() test : " + mapJoiner.join(map));
    }
}

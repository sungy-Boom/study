package com.daily.learn.guava.string;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.*;

/**
 * Created by sunguiyong on 2017/12/26.
 * <p>
 * // 通过使用指定参数，对字符串进行拼接
 * static Joiner on(String separator)
 * static Joiner on(char separator)
 * <p>
 * // join里边是要拼接的字符串
 * String join(Iterable<?> parts)
 * String join(Iterator<?> parts)
 * String join(Object[] parts)
 * String join(Object first, Object second, Object... rest)
 * <p>
 * <p>
 * // 对null进行处理
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

        System.out.println();
        List<String> list2 = new ArrayList<>(Arrays.asList("1", "test", "4"));
        list2.add("null");
        list2.add("");
        System.out.println("test : " + Joiner.on("--").join(list2));
        System.out.println("skipNulls() test : " + Joiner.on("--")
                .skipNulls().join(list2, null, 1));
        System.out.println("useForNull() test : " + Joiner.on("--")
                .useForNull("this is a null").join(list2, null, 2, 1));

        System.out.println();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Joiner.MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=");
        System.out.println("withKeyValueSeparator() test : " + mapJoiner.join(map));
    }
}

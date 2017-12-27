package com.daily.learn.guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by sunguiyong on 2017/12/26.
 * <p>
 * // 设置分割字符
 * static Splitter on(char separator)
 * static Splitter on(String separator)
 * static Splitter on(CharMatcher separatorMatcher)
 * static Splitter on(Pattern separatorPattern)
 * static Splitter onPattern(String separatorPattern)
 * <p>
 * // 返回的结果是Iterable接口类型的数据
 * Iterable<String> split(CharSequence sequence)
 * List<String> splitToList(CharSequence sequence)
 * <p>
 * // 去掉子串中的空格
 * Splitter trimResults()
 * Splitter trimResults(CharMatcher trimmer)
 * <p>
 * // 直接返回Map的键值对
 * Splitter.MapSplitter withKeyValueSeparator(char separator)
 * Splitter.MapSplitter withKeyValueSeparator(String separator)
 * Splitter.MapSplitter withKeyValueSeparator(Splitter keyValueSplitter)
 *
 * <p>
 * // 将给定的字符串分割为长度为length的子字符串
 * static Splitter fixedLength(int length)
 * <p>
 * // 分割的子字符串达到了limit个时
 * Splitter limit(int limit)
 * // 去掉空的子字符串
 * Splitter omitEmptyStrings()
 */
public class SplitterTest {

    public static void main(String[] args) {
        SplitterTest test = new SplitterTest();
        test.spiltterTest();

    }

    private final Pattern testPattern = Pattern.compile("@");
    private void spiltterTest() {
        String str = "this is a   test.@99999@email";
        System.out.println("on(String) test: " + Splitter.on(" ").split(str));
        System.out.println("on(CharMatcher) test: " + Splitter.on(CharMatcher.is('9')).split(str));
        System.out.println("on(Pattern) test: " + Splitter.on(testPattern).split(str));
        System.out.println("onPattern(String) test: " + Splitter.onPattern("@").split(str));

        System.out.println();
        System.out.println("fixedLength(length) test : " + Splitter.fixedLength(2).split(str));
        System.out.println("fixedLength(length) and trimResults test : " +
                Splitter.fixedLength(2).trimResults().split(str));

        System.out.println();
        System.out.println("on(String) test: " + Splitter.on(" ").split(str));
        System.out.println("fixedLength(length) and omitEmptyStrings test : " +
                Splitter.on(" ").omitEmptyStrings().split(str));

        System.out.println();
        Map<String,String> split = Splitter.on(";").trimResults()
                .withKeyValueSeparator("=").split("a=2;b=3");
        System.out.println("withKeyValueSeparator test: " + split.get("a"));
    }
}

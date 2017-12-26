package com.daily.learn.guava.string;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created by SunGuiyong
 * on 2017/12/26.
 */
public class StringTest {

    public static void main(String[] args) {

        String str = "t1,t2,t3,";
        List<String> methodInfoList = Splitter.on(",").omitEmptyStrings().splitToList(str);
        System.out.println(methodInfoList);
    }
}

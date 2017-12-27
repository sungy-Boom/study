package com.daily.learn.guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
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

        String str2 = "dfsdfa.1";
        System.out.println(str2.contains("."));
        String[] nameTest = str2.split("\\.");
        String name;
        List<String> namelist = Splitter.on(".").splitToList(str2);
        namelist = namelist.subList(0, namelist.size()-1);
        name = Joiner.on(".").join(namelist);
        System.out.println(namelist);
        System.out.println(name);
    }
}

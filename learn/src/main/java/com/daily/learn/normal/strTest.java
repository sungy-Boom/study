package com.daily.learn.normal;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

import static com.google.common.base.Splitter.on;

/**
 * Created by SunGuiyong
 * on 2018/1/31.
 */
public class strTest {

    public static void main(String[] args) {
        String str = "http://peiyou-pad-mgmt.oss-cn-beijing.aliyuncs.com/test/course_file/20171211/67100a42-b388-4a9b-9f35-d48919a70a77.mp4";
        List<String> strList = Splitter.on("/").omitEmptyStrings().splitToList(str);
        strList = strList.subList(2, strList.size());
        str = Joiner.on("/").join(strList);
        str = "/" + str;
        System.out.println(str);
        System.out.println(strList);
    }
}

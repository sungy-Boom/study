package com.daily.learn.part_word;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/**
 * Created by SunGuiyong
 * on 2018/1/5.
 * HanLP 分词工具
 */
public class PartWordTest {

    public static void main(String[] args) {

        System.out.println(HanLP.segment("这是第一个分词的测试"));
        System.out.println(StandardTokenizer.segment("这是第一个分词的测试"));
        System.out.println(NLPTokenizer.segment("这是第一个分词的测试"));
        subTest();
    }

    public static void subTest() {
        try {
            String test_1 = "\"certinfo\":{\"certnick\":\"\\u6b22\\u4e50\\u7684\\u5927\\u809a\\u5b50\\u6d77\\u76d7\\u8700\\u9ecd\",\"certhead\":\"\",\"certinfo\":\"\",\"certidentityid\":\"10003\",\"certappids\":\"10002\"},\"remark\":\"\",\"fnd\":0}";
            String test = "\"certinfo\":{\"certnick\":\"\\u6b22\\u4e50\\u7684\\u5927\\u809a\\u5b50\\u6d77\\u76d7\\u8700\\u9ecd\",\"certhead\":\"\",\"certinfo\":\"\",\"certidentityid\":\"10003\",\"certappids\":\"10002\"},\"remark\":\"\",\"fnd\":0";
            String str/* = test.substring(test.indexOf("is"),test.indexOf("test"))*/;
            int start = test.indexOf("\"certinfo\":") + "\"certinfo\":".length();
            int end;
            StringBuilder sb = new StringBuilder();
            String res = test.substring(0, start);
            res += "\"\"";
            sb.append(res);
            end = test.indexOf(",\"remark\"");
            res = test.substring(end, test.length());
            sb.append(res);
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

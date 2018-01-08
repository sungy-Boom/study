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
    }
}

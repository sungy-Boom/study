package com.daily.learn.guava.string;

import com.google.common.base.CaseFormat;

/**
 * Created by sunguiyong on 2017/12/27.
 *
 * //testData
 * LOWER_CAMEL
 * // test-data
 * LOWER_HYPHEN
 * test_data
 * LOWER_UNDERSCORE
 * //TestData
 * UPPER_CAMEL
 * //TEST_DATA
 * UPPER_UNDERSCORE
 *
 * String to(CaseFormat format, String str)
 * static CaseFormat valueOf(String name)
 * static CaseFormat[] values()
 */
public class CaseFormatTest {

    public static void main(String[] args){
        CaseFormatTest test = new CaseFormatTest();;

        test.caseFormatTest();
    }

    private void caseFormatTest(){
        System.out.println("LOWER_HYPHEN to UPPER_CAMEL: " +
                CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "test-data"));
        System.out.println("LOWER_UNDERSCORE to LOWER_CAMEL: " +
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println("UPPER_UNDERSCORE to UPPER_CAMEL: " +
                CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "Test_Data"));
        System.out.println("LOWER_CAMEL to LOWER_UNDERSCORE: " +
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testData"));
        System.out.println("LOWER_CAMEL to LOWER_HYPHEN: " +
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));
        System.out.println("LOWER_CAMEL to UPPER_UNDERSCORE: " +
                CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "testData"));
    }
}

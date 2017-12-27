package com.daily.learn.guava.string;

import com.google.common.base.CharMatcher;

/**
 * Created by sunguiyong on 2017/12/27.
 * ANY: 匹配任何字符
 * ASCII: 匹配是否是ASCII字符
 * BREAKING_WHITESPACE: 匹配所有可换行的空白字符(不包括非换行空白字符,例如"\u00a0")
 * DIGIT: 匹配ASCII数字
 * INVISIBLE: 匹配所有看不见的字符
 * JAVA_DIGIT: 匹配UNICODE数字, 使用 Character.isDigit() 实现
 * JAVA_ISO_CONTROL: 匹配ISO控制字符, 使用 Charater.isISOControl() 实现
 * JAVA_LETTER: 匹配字母, 使用 Charater.isLetter() 实现
 * JAVA_LETTER_OR_DIGET: 匹配数字或字母
 * JAVA_LOWER_CASE: 匹配小写
 * JAVA_UPPER_CASE: 匹配大写
 * NONE: 不匹配所有字符
 * SINGLE_WIDTH: 匹配单字宽字符, 如中文字就是双字宽
 * WHITESPACE: 匹配所有空白字符
 * <p>
 * //返回匹配指定字符的Matcher
 * CharMatcher is(char match):
 * //返回不匹配指定字符的Matcher
 * CharMatcher isNot(char match):
 * <p>
 * //返回匹配sequence中任意字符的Matcher
 * CharMatcher anyOf(CharSequence sequence):
 * //返回不匹配sequence中任何一个字符的Matcher
 * CharMatcher noneOf(CharSequence sequence):
 * <p>
 * //删除sequence中匹配到到的字符并返回
 * String removeFrom(CharSequence sequence):
 * //保留sequence中匹配到的字符并返回
 * String retainFrom(CharSequence sequence):
 * <p>
 * //返回以当前Matcher判断规则相反的Matcher
 * CharMatcher negate():
 * <p>
 * //返回与other匹配条件组合做与来判断的Matcher
 * CharMatcher and(CharMatcher other):
 * //返回与other匹配条件组合做或来判断的Matcher
 * CharMatcher or(CharMatcher other):
 * <p>
 * //返回匹配范围内任意字符的Matcher
 * CharMatcher inRange(char startInclusive, char endIncludesive):
 * //返回使用predicate的apply()判断匹配的Matcher
 * CharMatcher forPredicate(Predicate<? super Charater> predicate):
 * //只要sequence中有任意字符能匹配Matcher,返回true
 * boolean matchesAnyOf(CharSequence sequence):
 * //sequence中所有字符都能匹配Matcher,返回true
 * boolean matchesAllOf(CharSequence sequence):
 * // sequence中所有字符都不能匹配Matcher,返回true
 * boolean matchesNoneOf(CharSequence sequence):
 * //返回sequence中匹配到的第一个字符的坐标
 * int indexIn(CharSequence sequence):
 * //返回从start开始,在sequence中匹配到的第一个字符的坐标
 * int indexIn(CharSequence sequence, int start):
 * //返回sequence中最后一次匹配到的字符的坐标
 * int lastIndexIn(CharSequence sequence):
 * //返回sequence中匹配到的字符计数
 * int countIn(CharSequence sequence):
 * <p>
 * //替换sequence中匹配到的字符并返回
 * String replaceFrom(CharSequence sequence, char replacement):
 * //删除首尾匹配到的字符并返回
 * String trimFrom(CharSequence sequence):
 * //删除首部匹配到的字符
 * String trimLeadingFrom(CharSequence sequence):
 * //删除尾部匹配到的字符
 * String trimTrailingFrom(CharSequence sequence):
 * //将匹配到的组(连续匹配的字符)替换成replacement
 * String collapseFrom(CharSequence sequence, char replacement):
 * //先trim再replace
 * String trimAndCollapseFrom(CharSequence sequence, char replacement):
 */
public class CharMatcherTest {
    public static void main(String[] args) {
        CharMatcherTest test = new CharMatcherTest();
        test.charMatcherTest();
    }

    public void charMatcherTest() {
        // removerFrom
        String removeFromResult = CharMatcher.isNot('a').removeFrom("abacd");
        System.out.println("removeForm:" + removeFromResult);
        // retainFrom method
        String retainFormResult = CharMatcher.is('a').retainFrom("abacd");
        System.out.println("retainForm:" + retainFormResult);
        // replaceFrom method
        String replaceFormResult1 = CharMatcher.WHITESPACE.replaceFrom("a bcd",
                'f');
        System.out.println("replaceFrom_1:" + replaceFormResult1);
        String replaceFormResult2 = CharMatcher.DIGIT.replaceFrom("a3bcd",
                "Three");
        System.out.println("replaceFrom_2:" + replaceFormResult2);
        // trimFrom
        System.out.println();
        //trimFrom 删除首位匹配到的字符串
        String trimFromResult = CharMatcher.anyOf("ab").trimFrom("catbanan");
        System.out.println("trimFrom:" + trimFromResult);
        System.out.println();
        // trimLeadingFrom
        String trimLeadingFromResult = CharMatcher.anyOf("ab").trimLeadingFrom(
                "abacatabb");
        System.out.println("trimLeadingFrom:" + trimLeadingFromResult);
        // trimTrailingFrom
        String trimTrailingFromResult = CharMatcher.anyOf("ab")
                .trimTrailingFrom("abacatabb");
        System.out.println("trimTrailingFrom:" + trimTrailingFromResult);
        // collapseFrom
        String collapseFromResult = CharMatcher.anyOf("bre").collapseFrom(
                "bookkeeper", '-');
        System.out.println("collapseFrom:" + collapseFromResult);
        // trimAndCollapseFrom
        String trimAndCollapseFromResult = CharMatcher.anyOf("bre")
                .trimAndCollapseFrom("bookkeeperkko", '-');
        System.out.println("trimAndCollapseFrom:" + trimAndCollapseFromResult);
        // matchesAllOf
        boolean matchesAllOfResult = CharMatcher.JAVA_UPPER_CASE
                .matchesAnyOf("hcd");
        System.out.println("matchesAnyOf:" + matchesAllOfResult);
        // or and negate
        String orResult = CharMatcher.JAVA_DIGIT
                .or(CharMatcher.JAVA_UPPER_CASE).retainFrom("dd59cF");
        System.out.println("or:" + orResult);
        // negate
        String negateResult = CharMatcher.JAVA_DIGIT.negate().retainFrom(
                "dd59cF");
        System.out.println("negate:" + negateResult);

        // and
        String andResult = CharMatcher.DIGIT
                .and(CharMatcher.WHITESPACE).retainFrom(
                        "1gg4 7d d5R9cFa4");
        System.out.println("and:" + andResult);
    }
}

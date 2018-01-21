package com.daily.learn.guava.primitive;

import com.google.common.primitives.Chars;

import java.util.List;

/**
 * Created by sunguiyong on 2018/1/20.
 * Guava Chars
 * <p>
 * static List<Character> asList(char... backingArray)
 * static char[] concat(char[]... arrays)
 * <p>
 * //List<Character> 转short数组
 * static char[] toArray(Collection<? extends Number> collection)
 * static boolean contains(char[] array, char target)
 * <p>
 * static byte[] toByteArray(char value)
 * //byte array 转short
 * static char fromByteArray(byte[] bytes)
 * <p>
 * static int indexOf(char[] array, char target)
 * static int indexOf(char[] array, char[] target)
 * static int lastIndexOf(char[] array, char target)
 * <p>
 * //返回最大、最小值
 * static char max(char... array)
 * static char min(char... array)
 */
public class CharsTest {
    public static void main(String args[]) {
        CharsTest tester = new CharsTest();
        tester.testChars();
    }

    private void testChars() {
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        //convert array of primitives to array of objects
        List<Character> objectArray = Chars.asList(charArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        charArray = Chars.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("c is in list? " + Chars.contains(charArray, 'c'));

        //return the index of element
        System.out.println("c position in list " + Chars.indexOf(charArray, 'c'));

        //Returns the minimum
        System.out.println("Min: " + Chars.min(charArray));

        //Returns the maximum
        System.out.println("Max: " + Chars.max(charArray));
    }
}

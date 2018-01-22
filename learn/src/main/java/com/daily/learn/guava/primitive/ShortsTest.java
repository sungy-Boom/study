package com.daily.learn.guava.primitive;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Shorts;

import java.util.List;

/**
 * Created by sunguiyong on 2018/1/20.
 * <p>
 * static List<Short> asList(short... backingArray)
 * static short[] concat(short[]... arrays)
 * <p>
 * //List<Short> 转short数组
 * static short[] toArray(Collection<? extends Number> collection)
 * static boolean contains(short[] array, short target)
 * <p>
 * static byte[] toByteArray(short value)
 * //byte array 转short
 * static short fromByteArray(byte[] bytes)
 * <p>
 * static int indexOf(short[] array, short target)
 * static int indexOf(short[] array, short[] target)
 * static int lastIndexOf(short[] array, short target)
 * <p>
 * //返回最大、最小值
 * static short max(short... array)
 * static short min(short... array)
 * <p>
 */
public class ShortsTest {
    public static void main(String args[]) {
        ShortsTest tester = new ShortsTest();
        tester.testShorts();
    }

    private void testShorts() {
        short[] shortArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //convert array of primitives to array of objects
        List<Short> objectArray = Shorts.asList(shortArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        shortArray = Shorts.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < shortArray.length; i++) {
            System.out.print(shortArray[i] + " ");
        }
        System.out.println("]");
        short data = 5;
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Shorts.contains(shortArray, data));

        //Returns the minimum
        System.out.println("Min: " + Shorts.min(shortArray));

        //Returns the maximum
        System.out.println("Max: " + Shorts.max(shortArray));
        data = -2400;
        //get the byte array from an integer
        //进行移位运算  2400 --> 0000 1001 0110 0000
        byte[] byteArray = Shorts.toByteArray(data);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println();
        short res = Shorts.fromByteArray(byteArray);
        System.out.println("from byte array " + res);
    }
}

package com.daily.learn.guava.primitive;

import com.google.common.primitives.Ints;

import java.util.List;

/**
 * Created by sunguiyong on 2018/1/20.
 * Guava Ints
 * <p>
 * static List<Integer> asList(int... backingArray)
 * static int[] concat(int[]... arrays)
 * static boolean contains(int[] array, int target)
 * <p>
 * static byte[] toByteArray(int value)
 * static int fromByteArray(byte[] bytes)
 * static int fromBytes(byte b1, byte b2, byte b3, byte b4)
 * <p>
 * static int indexOf(int[] array, int target)
 * static int indexOf(int[] array, int[] target)
 * static int lastIndexOf(int[] array, int target)
 * <p>
 * static int max(int... array)
 * static int min(int... array)
 */
public class IntsTest {
    public static void main(String args[]) {
        IntsTest tester = new IntsTest();
        tester.testInts();
    }

    private void testInts() {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //convert array of primitives to array of objects
        List<Integer> objectArray = Ints.asList(intArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        intArray = Ints.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Ints.contains(intArray, 5));

        //Returns the minimum
        System.out.println("Min: " + Ints.min(intArray));

        //Returns the maximum
        System.out.println("Max: " + Ints.max(intArray));

        //get the byte array from an integer
        //int toByteArray 进行移位，24 16 8 0
        byte[] byteArray = Ints.toByteArray(20000);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
    }
}

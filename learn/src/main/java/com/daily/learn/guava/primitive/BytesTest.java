package com.daily.learn.guava.primitive;

import com.google.common.primitives.Bytes;

import java.util.List;

/**
 * Created by sunguiyong on 2018/1/20.
 * Guava Bytes
 * <p>
 * static List<Byte> asList(byte... backingArray)
 * <p>
 * static byte[] toArray(Collection<? extends Number> collection)
 * <p>
 * // byte数组进行拼接
 * static byte[] concat(byte[]... arrays)
 * <p>
 * //返回第一次出现的位置
 * static int indexOf(byte[] array, byte target)
 * <p>
 * //最后一次出现的位置
 * static int lastIndexOf(byte[] array, byte target)
 * <p>
 * //返回所包含的数组的第一个位置
 * static int indexOf(byte[] array, byte[] target)
 * <p>
 * // * static byte[] ensureCapacity(byte[] array, int minLength, int padding)
 */
public class BytesTest {

    public static void main(String args[]) {
        BytesTest tester = new BytesTest();
        tester.testBytes();
    }

    private void testBytes() {
        byte[] byteArray = {1, 2, 3, 4, 5, 5, 7, 9, 9};
        byte[] byteArray_1 = {1, 2, 3, 4, 5, 5, 7, 9, 9};
        byte[] byteArray_2 = {1, 2, 3, 4, 5, 5, 7, 9, 9};

        //convert array of primitives to array of objects
        List<Byte> objectArray = Bytes.asList(byteArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        byteArray = Bytes.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println("]");
        byte data = 5;
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Bytes.contains(byteArray, data));

        //Returns the index
        System.out.println("Index of 5: " + Bytes.indexOf(byteArray, data));

        //Returns the last index maximum
        System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray, data));

        //concat
        byteArray = Bytes.concat(byteArray, byteArray_1, byteArray_2);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println();
        //indexOf
        int index = Bytes.indexOf(byteArray, byteArray_1);
        System.out.println("byte array indexOf :" + index);

        //ensureCapacity
       /* byte[] res = Bytes.ensureCapacity(byteArray, 40, 2);
        System.out.println();
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }*/
    }
}

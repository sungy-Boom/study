package com.daily.learn.guava.primitive;

import com.google.common.primitives.Booleans;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by sunguiyong on 2018/1/20.
 * Guava Booleans
 * <p>
 * static List<Boolean> asList(boolean... backingArray)
 * static boolean[] toArray(Collection<Boolean> collection)
 * <p>
 * static boolean[] concat(boolean[]... arrays)
 * static boolean contains(boolean[] array, boolean target)
 * //返回包含的true的数量
 * static int countTrue(boolean... values)
 * <p>
 * static int indexOf(boolean[] array, boolean target)
 * static int indexOf(boolean[] array, boolean[] target)
 * static int lastIndexOf(boolean[] array, boolean target)
 */
public class BooleansTest {
    public static void main(String args[]) {
        BooleansTest tester = new BooleansTest();
        tester.testBooleans();
    }

    private void testBooleans() {
        boolean[] booleanArray = {true, true, false, true, true, false, false};

        //convert array of primitives to array of objects
        List<Boolean> objectArray = Booleans.asList(booleanArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        booleanArray = Booleans.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < booleanArray.length; i++) {
            System.out.print(booleanArray[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("true is in list? " + Booleans.contains(booleanArray, true));

        //return the first index of element
        System.out.println("true position in list " + Booleans.indexOf(booleanArray, true));

        //Returns the count of true values
        System.out.println("true occured: " + Booleans.countTrue(booleanArray));

        //Returns the comparisons
        System.out.println("false Vs true: " + Booleans.compare(false, true));
        System.out.println("false Vs false: " + Booleans.compare(false, false));
        System.out.println("true Vs false: " + Booleans.compare(true, false));
        System.out.println("true Vs true: " + Booleans.compare(true, true));
    }
}

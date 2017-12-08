package com.daily.learn.guava.foundation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.*;

/**
 * Guava Ordering
 *
 * @author Soul
 */
public class OrderingTest {

  //    1, 2, 3, 4, 1123, 4, 123, 5, 23, 76, 213, 4, 756, 324, 6
  private List<Integer> list_1 = new ArrayList<>(Arrays.asList(1, 0, 3, 4, 2, 1));
  private List<Integer> list_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
  private List<String> abc = ImmutableList.of("a", "b", "c");//创建一个不可变集合
  private List<Integer> comparator_test = new ArrayList<>(Arrays.asList(-2, 1, 4, 3, 5, -9, -1));

  public static void main(String[] args) {
    OrderingTest test = new OrderingTest();

    //对于给定的数据按照自然序排列
    Ordering ordering = Ordering.natural();
    test.binarySearchTest(ordering);
    test.compareTest(ordering);
    test.isSortedTest(ordering);
    test.reverseTest(ordering);
    test.maxMinTest(ordering);
    test.otherMethodTest(ordering);
    test.useComparatorTest(ordering);

   /* ordering = Ordering.allEqual();
    ordering = Ordering.arbitrary();
    ordering = Ordering.usingToString();
    ordering = Ordering.from();*/

    test.sortedSet();

  }

  /**
   * ordering.binarySearch(sortedList, key); attention sortedList
   *
   * list_1 = ordering.sortedCopy(list_1);
   */
  private void binarySearchTest(Ordering ordering) {
    list_1 = ordering.sortedCopy(list_1);
    System.out.println("sorted copy ：" + list_1);
    System.out.println("is strictly sorted list_1： " + ordering.isStrictlyOrdered(list_1));//false
    int res = ordering.binarySearch(list_1, 5);
    System.out.println("binary search ：" + res);
  }

  private void compareTest(Ordering ordering) {
    int res = ordering.compare(1, 2);

    System.out.println("compare two parameters：" + res);
    res = ordering.compare(2, 2);

    System.out.println("compare two parameters：" + res);
    res = ordering.compare(3, 2);

    System.out.println("compare two parameters：" + res);
  }

  /**
   * 判断集合是否已经排序
   */
  private void isSortedTest(Ordering ordering) {
    System.out.println("sorted test ：" + ordering.isOrdered(list_2));//true
    System.out.println("strictly sorted test ：" + ordering.isStrictlyOrdered(list_2));
  }

  /**
   * reverse
   */
  private void reverseTest(Ordering ordering) {
    System.out.println("test list_2 reverse :" + ordering.reverse().sortedCopy(list_2));
    System.out.println("isOrdered reverse :" + ordering.reverse().isOrdered(abc));
  }

  /**
   * max min
   */
  private void maxMinTest(Ordering ordering) {
    System.out.println("max :" + ordering.max(abc));
    System.out.println("min :" + ordering.min(abc));
  }

  /**
   * ImmutableSortedSet
   */
  private void sortedSet() {
    Set<String> imSortList = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
    System.out.println("imSortSet：" + imSortList);
  }

  private void otherMethodTest(Ordering ordering) {

    System.out.println("leastOf:" + ordering.leastOf(list_2, 2));
    System.out.println("greatestOf:" + ordering.greatestOf(list_2, 3));
    System.out.println("reverse list_2 :" + ordering.reverse().sortedCopy(list_2));
    list_2.add(null);
    System.out.println("add null list_2:" + list_2);
    System.out.println("nullsFirst list_2 :" + ordering.nullsFirst().sortedCopy(list_2));
    System.out.println("nullsLast list_2 :" + ordering.nullsLast().sortedCopy(list_2));
  }

  /**
   * 对ordering使用自定义排序器
   */
  private void useComparatorTest(Ordering ordering) {
    /*Comparator<Integer> comparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return Math.abs(o1) - Math.abs(o2);
      }
    };*/

    Comparator<Integer> comparator = (Integer o1, Integer o2) -> (o1.compareTo(o2));
    System.out.println("comparator test: " + Ordering.from(comparator).sortedCopy(comparator_test));
  }
}
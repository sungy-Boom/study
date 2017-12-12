package com.daily.learn.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Multiset常用方法
 *
 * <p>
 *
 * int add(E element) :向其中添加单个元素
 *
 * int add(E element,int occurrences) : 向其中添加指定个数的元素
 *
 * int count(Object element) : 返回给定参数元素的个数
 *
 * boolean contains(Object element) ：判断集合中是否包含指定元素
 *
 * boolean containsAll(Collection<?> elements) ：判断当前集合是不是指定集合的子集
 *
 * boolean equals(Object object) ：判断集合是否相等
 *
 * boolean remove(E element) : 移除一个元素，其count值 会响应减少
 *
 * int remove(E element,int occurrences): 移除相应个数的元素
 *
 * elementSet() : 将不同的元素放入一个Set中
 *
 * entrySet(): 类似与Map.entrySet 返回Set<Multiset.Entry>。包含的Entry支持使用getElement()和getCount()
 *
 * setCount(E element ,int count): 设定某一个元素的重复次数
 *
 * setCount(E element,int oldCount,int newCount): 将符合原有重复个数的元素修改为新的重复次数
 *
 * retainAll(Collection c) : 保留出现在给定集合参数的所有的元素
 *
 * removeAll(Collectionc) : 去除出现给给定集合参数的所有的元素
 *
 * </p>
 */
public class MultisetTest {

  public static void main(String[] args) {

    Set<String> set = new HashSet<>();

    Multiset<String> multiset_1 = LinkedHashMultiset.create();
    Multiset<String> multiset_2 = TreeMultiset.create();
    Multisets multisets;

    Multiset<String> multiset = HashMultiset.create();
    MultisetTest test = new MultisetTest();
    test.addSetElement(multiset);
    test.countAndJudge(multiset);
  }

  /**
   * 集合中增加元素
   */
  private void addSetElement(Multiset<String> multiset) {
    multiset.add("raw");
    multiset.add("raw");
    multiset.add("raw");
    multiset.add("qw");

    //加入null值
    multiset.add(null);

    //指定添加元素的个数,这个个数最大为Integer.MAX_VALUE
    System.out.println(multiset.add("count", Integer.MAX_VALUE));
    System.out.println(multiset.add("qw", 3));
    System.out.println(multiset.add("e", 0));
    System.out.println("向集合中添加元素：" + multiset);
  }

  /**
   * multiset集合统计与判断
   */
  private void countAndJudge(Multiset<String> multiset) {
    System.out.println("计算集合中指定元素的个数：" + multiset.count("raw"));
    System.out.println("集合中是否包含指定的元素：" + multiset.contains("raw"));
    Multiset<String> set = HashMultiset.create();
    set.add("raw");
    List<String> list = new ArrayList<>(Arrays.asList("raw", "qw"));
    System.out.println("集合中是否包含指定的集合：" + multiset.containsAll(set));
    System.out.println("集合中是否包含指定的集合：" + multiset.containsAll(list));

  }
}

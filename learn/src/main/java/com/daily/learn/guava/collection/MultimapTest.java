package com.daily.learn.guava.collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SunGuiyong
 *
 * //Multimap 中加入元素，如果加入元素在之前不存在，返回true，如果存在，返回false。 对于同一个key，如果put不同的value，会将value形成一个Collection
 * boolean put(K, V)
 *
 * //清除map中的所有内容
 *
 * void clear()
 *
 * //判断是否包含 key-value 对 boolean containsEntry(Object key, Object value) boolean containsKey(Object
 * key) boolean containsValue(Object value)
 *
 * Collection<Map.Entry<K,V>> entries()
 */
public class MultimapTest {

  public static void main(String[] args) {
    Multimap<String, Integer> multiMap = HashMultimap.create();
    Multimap<String, List<Integer>> map = HashMultimap.create();
    MultimapTest test = new MultimapTest();

    test.addTest(multiMap, map);
    System.out.println();
    test.judgeContain(multiMap, map);
    System.out.println();

    test.clearTest(multiMap, map);
  }

  /**
   * put key-value to map
   */
  private void addTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
    multiMap.put("test", 1);
    System.out.println("is the value put success : " + multiMap.put("test", 2));
    System.out.println("after addTest map : " + multiMap);
    System.out.println("map get key : " + multiMap.get("test"));

    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(1);
    map.put("Test", list);
    List<Integer> list_2 = new ArrayList<>();
    list_2.add(1);
    list_2.add(1);
    System.out.println("is the value list put success : " + map.put("Test", list_2));
    System.out.println("map put list : " + map);
    list_2.add(2);
    System.out.println("is the value list put success : " + map.put("Test", list_2));
    System.out.println("map put list : " + map);
  }

  /**
   * judge
   */
  private void judgeContain(Multimap<String, Integer> multiMap,
      Multimap<String, List<Integer>> map) {
    System.out
        .println("is multiMap contain the key-value pair : " + multiMap.containsEntry("test", 1));
    System.out.println("is multiMap contain the specify key : " + multiMap.containsKey("test"));
    System.out.println("is multiMap contain the specify value : " + multiMap.containsValue(1));

    System.out.println("is map contain the key-value pair : " +
        map.containsEntry("test", Arrays.asList(1, 1)));
    System.out.println("is map contain the key-value pair : " +
        map.containsEntry("Test", Arrays.asList(1, 1)));
    System.out.println("is map contain the specify key : " + multiMap.containsKey("test"));
    System.out.println("is map contain the specify value : " +
        multiMap.containsValue(Arrays.asList(1, 1)));
  }

  /**
   * go through map
   */
  private void throughTest(Multimap<String, Integer> multiMap,
      Multimap<String, List<Integer>> map) {
  }

  /**
   * clear map
   */
  private void clearTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
    multiMap.clear();
    map.clear();
    System.out.println("multiMap after clear : " + multiMap);
    System.out.println("map after clear : " + map);
  }

}

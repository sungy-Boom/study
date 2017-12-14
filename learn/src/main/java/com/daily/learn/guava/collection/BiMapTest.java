package com.daily.learn.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;

/**
 * BiMap实现键值对的双向映射，并保持它们间的同步
 *
 * V put(K key, V value) 如果key存在，返回之前的value，如果不存在，返回null
 *
 * V forcePut(K key, V value) 如果key存在，返回之前的value，如果不存在，返回null
 *
 * void putAll(Map<? extends K,? extends V> map)
 *
 * Set<V> values()
 */
public class BiMapTest {

  public static void main(String[] args) {
    BiMapTest test = new BiMapTest();
    BiMap<String, Integer> biMap = HashBiMap.create(16);
    test.addElement(biMap);
    test.keyToValue(biMap);
  }

  /**
   * biMap中添加元素 value already present 对于不同的键，不可以有相同的值
   */
  private void addElement(BiMap<String, Integer> biMap) {
    System.out.println("test return value: " + biMap.put("test1", 9));
    System.out.println("test return value: " + biMap.put("test1", 0));
    biMap.put("test2", 3);
    System.out.println(biMap);

    Map<String, Integer> map = new HashMap<>();
    map.put("map", 1);
    map.put("map1", 2);
    map.put("map2", 4);
    biMap.putAll(map);
    System.out.println("put map into biMap: " + biMap);
    System.out.println(biMap.forcePut("test3", 3));
    System.out.println(biMap.forcePut("test3", 3));
    System.out.println("force put into biMap:" + biMap);
  }

  /**
   * search value by key
   */
  private void keyToValue(BiMap<String, Integer> biMap) {
    System.out.println("get value by key: " + biMap.get("map"));

    System.out.println("get key by value: " + biMap.inverse().get(1));

    System.out.println("get values: " + biMap.values());
    System.out.println("get keys: " + biMap.inverse().values());
  }

}

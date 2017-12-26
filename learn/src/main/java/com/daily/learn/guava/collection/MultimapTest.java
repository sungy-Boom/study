package com.daily.learn.guava.collection;

import com.google.common.collect.*;

import java.util.*;

/**
 * @author SunGuiyong
 * <p>
 * //Multimap 中加入元素，如果key在之前不存在，返回true，如果存在，返回false。 对于同一个key，如果put不同的value，会将value形成一个Collection
 * <p>
 * boolean put(K, V)
 * <p>
 * boolean putAll(K key, Iterable<? extends V> values)
 * <p>
 * //清除map中的所有内容
 * <p>
 * void clear()
 * <p>
 * //判断是否包含 key-value 对
 * <p>
 * boolean containsEntry(Object key, Object value)
 * <p>
 * boolean containsKey(Object * key)
 * <p>
 * boolean containsValue(Object value)
 * <p>
 * // 返回一个存放在Collection中的map
 * <p>
 * Collection<Map.Entry<K,V>> entries()
 * <p>
 * // 根据key返回value
 * <p>
 * Collection<V> get(K key)
 * <p>
 * //返回所有的key，不重复
 * <p>
 * Multiset<K> keys()
 * <p>
 * Set<K> keySet()
 * <p>
 * //返回一个value的集合
 * <p>
 * Collection<V> values()
 * <p>
 * //返回一个map，value放到数组中，返回一个Map<key, Collection<value>>
 * <p>
 * Map<K,Collection<V>> asMap()
 * <p>
 * // remove掉一个键值对，如果没有这键值对，返回false，否则返回true
 * <p>
 * boolean remove(Object key, Object value)
 * <p>
 * //remove删除指定key所对应的value，返回Collection<value>
 * <p>
 * Collection<V> removeAll(Object key)
 * <p>
 * // 把指定key原来的value替换为指定的value。返回原value
 * <p>
 * Collection<V> replaceValues(K key, Iterable<? extends V> values)
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
        test.throughTest(multiMap, map);
        System.out.println();
        test.replceTest(multiMap, map);
        System.out.println();
        test.removeTest(multiMap, map);
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
        System.out.println("is the value list put success : " + map.put("Test1", list_2));
        System.out.println("map put list : " + map);
    }

    /**
     * judge
     */
    private void judgeContain(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        System.out.println("is multiMap contain the key-value pair : " +
                multiMap.containsEntry("test", 1));
        System.out.println("is multiMap contain the specify key : " +
                multiMap.containsKey("test"));
        System.out.println("is multiMap contain the specify value : " +
                multiMap.containsValue(1));

        System.out.println("is map contain the key-value pair : " +
                map.containsEntry("test", Arrays.asList(1, 1)));
        System.out.println("is map contain the key-value pair : " +
                map.containsEntry("Test", Arrays.asList(1, 1)));
        System.out.println("is map contain the specify key : " +
                multiMap.containsKey("test"));
        System.out.println("is map contain the specify value : " +
                multiMap.containsValue(Arrays.asList(1, 1)));

        //Multimap如果value是list，那么list中的每一个数都有一个对应的key
        System.out.println(multiMap);
        System.out.println("is map contain the specify value : " +
                multiMap.containsValue(1));
    }

    /**
     * go through map
     */
    private void throughTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        // map.entries()
        Collection<Map.Entry<String, List<Integer>>> collectionMap = map.entries();
        Iterator<Map.Entry<String, List<Integer>>> iter = collectionMap.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, List<Integer>> entry = iter.next();
            System.out.println("key-value " + entry);
            System.out.println("key " + entry.getKey());
            System.out.println("value " + entry.getValue());
        }

        //map.get(K key)
        Collection<List<Integer>> list = map.get("Test");
        System.out.println("getKey : " + list);

        //map.keys()
        Multiset<String> set = map.keys();
        System.out.println("keys: " + set);

        //map.keySet()
        Set<String> keySet = map.keySet();
        System.out.println("keySet: " + keySet);

        //map.asMap()
        Map<String, Collection<Integer>> asmap = multiMap.asMap();
        System.out.println("asMap: " + asmap);

        //map.values()
        Collection<Integer> multi = multiMap.values();
        System.out.println("values: " + multi);
    }

    //replace
    private void replceTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        System.out.println("replace test: " + multiMap.replaceValues("test", ImmutableList.of(1, 23, 4)));
        System.out.println(multiMap);

        System.out.println("replace test: " + multiMap.replaceValues("error_test", ImmutableList.of(1, 23, 4)));
        System.out.println(multiMap);
    }

    // remove
    private void removeTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        System.out.println("multimap remove false: " + multiMap.remove("test", ImmutableList.of(1, 2)));
        System.out.println("multimap remove true: " + multiMap.remove("test", 1));
        System.out.println("multimap after remove : " + multiMap);

        System.out.println("map remove false: " + map.remove("Test", ImmutableList.of(1, 1)));
        System.out.println("map remove true: " + map.remove("Test", 1));
        System.out.println("map after remove : " + map);

        // map.removeAll()
        System.out.println("removeAll test: " + multiMap.removeAll("test"));
        System.out.println("removeAll test: " + multiMap.removeAll("error_test"));
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

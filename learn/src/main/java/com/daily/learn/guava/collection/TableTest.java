package com.daily.learn.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * //获取指定列的值，返回行和值形成的键值对
 * <p>
 * Map<R,V> column(C columnKey)
 * <p>
 * // 获取指定行的值，返回列和值形成的键值对
 * <p>
 * Map<C,V>	row(R rowKey)
 * <p>
 * // 获取列元素
 * <p>
 * Set<C> columnKeySet()
 * <p>
 * // 获取行元素
 * <p>
 * Set<R> rowKeySet()
 * <p>
 * // 返回以row为键，列和值为值得键值对
 * Map<R,Map<C,V>> rowMap()
 * Map<C,Map<R,V>> columnMap()
 * <p>
 * // 返回所有值
 * Collection<V> values()
 * //根据行和列确定值
 * V get(Object rowKey, Object columnKey)
 * //根据行和列移除元素，如果存在返回值，不存在返回null
 * V remove(Object rowKey, Object columnKey)
 * <p>
 * //判断元素是否存在
 * boolean contains(Object rowKey, Object columnKey)
 * boolean containsColumn(Object columnKey)
 * boolean containsRow(Object rowKey)
 * boolean containsValue(Object value)
 */
public class TableTest {

    public static void main(String[] args) {
        TableTest test = new TableTest();
        Table<String, String, Integer> table = HashBasedTable.create();

        test.putTest(table);
        System.out.println();
        test.throughTableTest(table);
        System.out.println();
        test.containTest(table);
    }

    //添加元素
    private void putTest(Table<String, String, Integer> table) {
        table.put("语文", "张三", 110);
        table.put("语文", "李四", 120);
        table.put("语文", "王五", 130);

        table.put("数学", "张三", 110);
        table.put("数学", "李四", 120);
        table.put("数学", "王五", 130);
        System.out.println("table :" + table);
    }

    // 遍历
    private void throughTableTest(Table<String, String, Integer> table) {
        System.out.println(table);
        Map<String, Integer> columnMap = table.column("张三");
        System.out.println("column values: " + columnMap);

        Map<String, Integer> rowMap = table.row("语文");
        System.out.println("row values: " + rowMap);

        Set<String> columnSet = table.columnKeySet();
        System.out.println("column key set: " + columnSet);

        Set<String> rowSet = table.rowKeySet();
        System.out.println("row key set: " + rowSet);

        Map<String, Map<String, Integer>> rowMapMap = table.rowMap();
        System.out.println("rowMap test: " + rowMapMap);

        Map<String, Map<String, Integer>> columnMapMap = table.columnMap();
        System.out.println("columnMap test: " + columnMapMap);

        Collection<Integer> collectionTest = table.values();
        System.out.println("collection : " + collectionTest);

        Integer value = table.get("语文", "张三");
        System.out.println("get value by row and column :" + value);

        System.out.println("根据行和列移除元素1： " + table.remove("张三", "语文"));
        System.out.println("根据行和列移除元素2： " + table.remove("张三", "张三"));
        System.out.println("根据行和列移除元素3： " + table.remove("语文", "张三"));
    }

    private void containTest(Table<String, String, Integer> table) {
        System.out.println("contains key and value1 : " + table.contains("张三", "语文"));
        System.out.println("contains key and value2 : " + table.contains("语文", "李四"));
        System.out.println("contain row: " + table.containsRow("语文"));
        System.out.println("contain column: " + table.containsColumn("张三"));
        System.out.println("contain value: " + table.containsValue(110));
    }
}

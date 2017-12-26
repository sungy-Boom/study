package com.daily.learn.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * //获取指定行的值
 * <p>
 * Map<R,V> column(C columnKey)
 */
public class TableTest {

    public static void main(String[] args) {
        TableTest test = new TableTest();
        Table<String, String, Integer> table = HashBasedTable.create();

        test.putTest(table);
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
        Map<String, Integer> columnMap = table.column("语文");
        System.out.println("column vlaues: " + columnMap);
    }
}

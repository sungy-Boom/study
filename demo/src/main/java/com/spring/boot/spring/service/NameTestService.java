package com.spring.boot.spring.service;

import com.spring.boot.spring.entity.NameTestEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author SunGuiyong
 */
public interface NameTestService {

    /**
     * 获取list
     */
    List<NameTestEntity> getNameTestByName(String name);

    /**
     * 更新
     */
    void updateByName(Date updateTime, String name);

    /**
     * 插入一个用户
     */
    boolean insertDB(String name);

    List<NameTestEntity> getList(String name);

    /**
     * 测试sql中的case用法
     */
    List<NameTestEntity> testCaseInSql(String name, String sub);

    /**
     * 删除数据
     */
    boolean deleteRecord(String name);

    /**
     * jpa中使用求模运算
     * @param num
     * @return
     */
    List<NameTestEntity> getModRes(BigInteger num);

    /**
     * 在jpa中使用 where id in list
     *
     * @param isList
     * @return
     */
    List<NameTestEntity> getByInSQL(List<Integer> isList);

    void asyncTest();
}

package com.daily.study.spring_boot.dao;

import com.daily.study.spring_boot.entity.NameTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author SunGuiyong
 */
@Repository
public interface INameTestDAO extends JpaRepository<NameTestEntity, Long> {

    /**
     * 通过名字获取list
     *
     * @param name
     * @return
     */
    List<NameTestEntity> getNameTestEntityByName(String name);

    /**
     * 更新数据
     *
     * @param updateTime
     * @param name
     */
    @Query(value = "update name_test set sub=999, update_time=:updateTime where name=:name", nativeQuery = true)
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void updateByName(Date updateTime, String name);
}

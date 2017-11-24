package com.daily.study.spring_boot.service;

import com.daily.study.spring_boot.entity.NameTestEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author SunGuiyong
 */
@Component
public interface NameTestService {

    /**
     * 获取list
     *
     * @param name
     * @return
     */
    List<NameTestEntity> getNameTestByName(String name);

    /**
     * 更新
     *
     * @param updateTime
     * @param name
     */
    void updateByName(Date updateTime, String name);
}

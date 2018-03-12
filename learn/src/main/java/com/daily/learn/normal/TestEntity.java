package com.daily.learn.normal;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by SunGuiyong
 * on 2018/3/12.
 */
@Setter
@Getter
public class TestEntity {
    private String test;

    public TestEntity(String test) {
        this.test = test;
    }
}

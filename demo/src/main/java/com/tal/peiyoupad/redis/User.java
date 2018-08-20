package com.tal.peiyoupad.redis;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by SunGuiyong
 * on 2018/8/20.
 */
@Setter
@Getter
public class User {

    private String username;
    private int age;
    private List<String> infoList;
}

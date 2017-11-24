package com.daily.study.spring_boot.controller;

import com.daily.study.spring_boot.entity.NameTestEntity;
import com.daily.study.spring_boot.service.NameTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author SunGuiyong
 */
@Controller
public class NameTestController {
    @Autowired
    private NameTestService nameTestService;

    @GetMapping(name = "/name")
    @ResponseBody
    public List<NameTestEntity> getNameTest(){
        return nameTestService.getNameTestByName("as");
    }

    @GetMapping("/test")
    @ResponseBody
    public String hello(String name, String hello){
        return name;
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateData(){
        nameTestService.updateByName(new Date(), "f");

        return "success";
    }
}

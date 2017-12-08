package com.spring.boot.demo.spring_boot.controller;

import com.spring.boot.demo.spring_boot.entity.NameTestEntity;
import com.spring.boot.demo.spring_boot.service.NameTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author SunGuiyong
 */
@Controller
public class NameTestController {
    @Autowired
    private NameTestService nameTestService;

    @GetMapping(name = "/getFirstByName")
    @ResponseBody
    public List<NameTestEntity> getNameTest(String name) {
        return nameTestService.getNameTestByName(name);
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateData() {
        nameTestService.updateByName(new Date(), "f");

        return "success";
    }

    @PostMapping("/insert")
    @ResponseBody
    public boolean insertData(String name) {
        return nameTestService.insertDB(name);
    }

    @GetMapping("/getList")
    @ResponseBody
    public List<NameTestEntity> getList(String name) {

        return nameTestService.getList(name);
    }
}

package com.spring.boot.demo.spring_boot.controller;

import com.spring.boot.demo.spring_boot.entity.NameTestEntity;
import com.spring.boot.demo.spring_boot.service.NameTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/ok")
    @ResponseBody
    public String okTest() {
        return "this is ok";
    }

    @GetMapping(name = "/getFirstByName")
    @ResponseBody
    public List<NameTestEntity> getNameTest(@Param("name") String name) {
        return nameTestService.getNameTestByName(name);
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateData(@Param("name") String name) {
        nameTestService.updateByName(new Date(), name);

        return "success";
    }

    @GetMapping("/insert")
    @ResponseBody
    public boolean insertData(@Param("name") String name) {
        return nameTestService.insertDB(name);
    }

    @GetMapping("/getList")
    @ResponseBody
    public List<NameTestEntity> getList(@Param("name") String name) {

        return nameTestService.getList(name);
    }

    @GetMapping("/testSQL")
    @ResponseBody
    public List<NameTestEntity> getTestRes(@Param("name") String name, @Param("sub") String sub) {
        return nameTestService.testCaseInSql(name, sub);
    }

    @GetMapping("/deleteRecord")
    @ResponseBody
    public boolean deleteRecord(String name) {
        return nameTestService.deleteRecord(name);
    }
}

package com.tal.peiyoupad.controller;

import com.tal.annotation_test.annotation.TestAnnotation;
import com.tal.peiyoupad.dao.INameTestDAO;
import com.tal.peiyoupad.dto.NameTestDTO;
import com.tal.peiyoupad.entity.NameTestEntity;
import com.tal.peiyoupad.service.NameTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author SunGuiyong
 */
@Controller
public class NameTestController {

    @Autowired
    private NameTestService nameTestService;

    @RequestMapping("/hello")
    public String helloHtml() {
        return "/index";
    }

    @GetMapping("/ok")
    @ResponseBody
    @TestAnnotation
    public String okTest() {
       /* TALAmonitorAOP talAmonitorAOP = new TALAmonitorAOP();
        talAmonitorAOP.annotationProcessor_1();*/
        return "this is ok";
    }

    @GetMapping(name = "/getFirstByName")
    @ResponseBody
//    @TALMonitor
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

    @PostMapping("/requestTest")
    @ResponseBody
    public String requestTest(HttpServletRequest request) {
        String name = request.getHeader("name");

        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String n = names.nextElement();
            System.out.println(n + "  " + request.getHeader(n));
        }
        return name;
    }

    @GetMapping("/useDefaultValue")
    @ResponseBody
    public void defaultValue(@RequestParam(defaultValue = "0") long time) {

        System.out.println(time);
    }

    @GetMapping("/useObjectPara")
    @ResponseBody
    public void useObject(Object value) {
        System.out.println((Integer) value);
    }

    @GetMapping("/mod")
    @ResponseBody
    public List<NameTestEntity> getMod(NameTestDTO dto) {
        return nameTestService.getModRes(dto.getNum());
    }

    @GetMapping("/in")
    @ResponseBody
    public List<NameTestEntity> getByIn() {
        List<Integer> list = new ArrayList<Integer>() {{
            add(34);
            add(35);
        }};
        return nameTestService.getByInSQL(list);
    }

    @GetMapping("/async")
    @ResponseBody
    public void asyncTest() {
        nameTestService.asyncTest();
    }


    @GetMapping("/part-test")
    @ResponseBody
    public List<NameTestEntity> getPartData() {
        return nameTestService.getPartOfData();
    }


    @Autowired
    private INameTestDAO nameTestDAO;

    @GetMapping("/save-test")
    @ResponseBody
    public void test() {
        NameTestEntity name = new NameTestEntity("test1", 234);
        for (int i = 0; i < 3; i++) {
            name = new NameTestEntity("test212", i + 10);
            nameTestDAO.save(name);
        }
    }
}

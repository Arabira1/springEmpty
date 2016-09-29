package com.spring.Controller;

import com.spring.Entity.TestEntity;
import com.spring.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arabira on 2016/9/8.
 */
@Controller
public class TestController {
    @Autowired
    private TestService service;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    @ResponseBody
    @RequestMapping("/return")
    public Map<String, String> test2() {
        Map<String, String > map = new HashMap<String, String>();
        map.put("status", "ok");
        return map;
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public Map<String, Object> test3(TestEntity testEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean result = service.addUser(testEntity);
        if (result) {
            map.put("status", "ok");
            map.put("result", result);
            return map;
        }
        else {
            map.put("status", "error");
            map.put("result", result);
            return map;
        }
    }

    @ResponseBody
    @RequestMapping("/findUser")
    public Map<String, Object> test4 (int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        TestEntity testEntity = service.findUser(id);
        map.put("status", "ok");
        map.put("result", testEntity);
        return map;
    }
 }

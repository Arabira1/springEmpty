package com.spring.Controller;

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
}

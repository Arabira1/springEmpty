package com.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Arabira on 2016/9/8.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}

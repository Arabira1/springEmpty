package com.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Arabira on 2016/11/5.
 */
@Controller
public class DeniedController {
    /**
     * @description 没有权限和未登录的信息跳转位置
     * @return string
     */
    @RequestMapping("/denied")
    public String denied () {
        return "fail";
    }
}

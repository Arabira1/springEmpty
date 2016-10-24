package com.spring.Controller;

import com.spring.Entity.UserEntity;
import com.spring.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arabira on 2016/10/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Map map;

    @RequestMapping("/signin")
    @ResponseBody
    public Map login(UserEntity userEntity){
        map = new HashMap();
        //创建
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getLoginName(), userEntity.getPassWord());
        try {
            subject.login(token);
            map.put("status",true);
            map.put("description", "登陆成功");
        }
        catch (IncorrectCredentialsException e) {
            map.put("status",false);
            map.put("description", "账号密码有误");
        }
        catch (ExcessiveAttemptsException e) {
            map.put("status", false);
            map.put("description", "多次尝试登陆");
        }
        catch (UnknownAccountException e) {
            map.put("status", false);
            map.put("description", "账号不存在");
        }
        catch (LockedAccountException e) {
            map.put("status", false);
            map.put("description", "账号被锁，请联系管理员");
        }
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map add(UserEntity userEntity) {
        map = new HashMap();
        String message = userService.add(userEntity);
        map.put("description", message);
        return map;
    }
}

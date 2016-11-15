package com.spring.Controller;

import com.spring.Entity.RoleEntity;
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

    /**
     * @description 用户登录
     * @param userEntity
     * @param session
     * @return map
     */
    @RequestMapping("/signin")
    @ResponseBody
    public Map login(UserEntity userEntity, HttpSession session){
        map = new HashMap();
        //创建subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getLoginName(), userEntity.getPassWord());
        try {
            subject.login(token);
            session.setAttribute("login", userEntity.getLoginName());
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

    /**
     * @description 添加用户操作
     * @param userEntity
     * @return map
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map add(UserEntity userEntity) {
        map = new HashMap();
        String message = userService.add(userEntity);
        map.put("description", message);
        return map;
    }

    /**
     * @description
     * @param userName
     * @return map
     * @throws Exception
     */
    @RequestMapping("/find")
    @ResponseBody
    public Map find(String userName) throws Exception {
        map = new HashMap();
        UserEntity userEntity = userService.findByName(userName);
        //密码进行处理
        userEntity.setPassWord("******");
        map.put("status", true);
        map.put("body", userEntity);
        return map;
    }

    /**
     * @description 更新用户信息
     * @param userEntity
     * @param session
     * @param oldPassword
     * @return map
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map update(UserEntity userEntity, HttpSession session, String oldPassword) {
        String loginName = (String) session.getAttribute("login");
        map = new HashMap();
        if (!userEntity.getUserName().isEmpty()) {
            boolean b = userService.updateUserName(loginName, userEntity.getUserName());
            if (b) {
                map.put("status",true);
                map.put("description","ok");
            }
        }
        else if (!userEntity.getPassWord().isEmpty()) {
            boolean b = userService.updatePassword(loginName, oldPassword, userEntity.getPassWord());
        }
        else {
            map.put("status", false);
            map.put("description", "error");
        }
        return map;
    }

    /**
     * @description 删除用户
     * @param userId
     * @param adminPassword
     * @param session
     * @return map
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(long userId, String adminPassword, HttpSession session) {
        map = new HashMap();
        String loginName = (String) session.getAttribute("login");
        boolean result = userService.delete(userId, loginName, adminPassword);
        if (result) {
            map.put("status",true);
            map.put("description", "删除成功");
        }
        else {
            map.put("status", false);
            map.put("description", "删除失败");
        }
        return map;
    }
}

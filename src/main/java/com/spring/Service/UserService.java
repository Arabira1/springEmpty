package com.spring.Service;

import com.spring.Dao.UserDao;
import com.spring.Entity.UserEntity;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Arabira on 2016/10/21.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserEntity  findByName(String userName) {
        if (userName == null || userName.equals("")) {
            return null;
        }
        return userDao.findUser(userName);
    }

    private UserEntity findById(Long userId) {
        return userDao.findById(userId);
    }

    public String add(UserEntity userEntity) {
        if (userEntity == null || userEntity.getLoginName().equals("")
                || userEntity.getPassWord().equals("")) {
            return "无效数据";
        }
        /**
         * SImpleHash为加密密码的组件，与shiro配置文件中的credentialsMatcher的一些参数对应，
         * 此处初始化的参数的意义分为：使用MD5加密、加密的值为用户的密码、加密所用的盐、进行hash的次数
         * 也就是：（MD5（MD5（userEntity.getPassWord()）））
         */
        SimpleHash hash = new SimpleHash("md5", userEntity.getPassWord(), "ccsi", 2);
        Long aLong = Long.valueOf(1);
        userEntity.setId(aLong);
        //进行hash后以16进制输出
        userEntity.setPassWord(hash.toHex());
        userEntity.setPrimarySalt("ccsi");
        userEntity.setLocked(true);
        try {
            userDao.addUser(userEntity);
            return "添加成功，等待管理员审核";
        }
        catch (Exception e) {
            return "添加失败，数据库有误";
        }
    }

    public boolean delete(UserEntity userEntity) {
        if (userEntity == null || userEntity.getId().equals("")) {
            return false;
        }
        UserEntity check = findById(userEntity.getId());
        if (check == null) {
            return false;
        }
        userDao.delUser(userEntity);
        return true;
    }

    public String updateUserName(Long userId, String userName) {
        UserEntity check = findById(userId);
        if (check == null) {
            return "无效操作";
        }
        if (userName.length() <= 0) {
            return "名称不可为空";
        }
        userDao.updateName(userId, userName);
        return userName;
    }

    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        if (userId == null || userId.equals("") || oldPassword.equals("") || newPassword.equals("")) {
            return false;
        }
        UserEntity check = findById(userId);
        if (check == null) {
            return false;
        }
        userDao.updatePassword(userId, oldPassword, newPassword);
        return true;
    }
}

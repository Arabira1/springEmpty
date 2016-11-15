package com.spring.Service;

import com.spring.Dao.RoleDao;
import com.spring.Dao.UserDao;
import com.spring.Dao.UserRoleDao;
import com.spring.Entity.RoleEntity;
import com.spring.Entity.UserEntity;
import com.spring.Entity.UserRoleEntity;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Arabira on 2016/10/21.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserRoleDao userRoleDao;

    /**
     * @description 通过用户登录名称查找用户信息
     * @param logName
     * @return UserEntity
     */
    public UserEntity  findByName(String logName) {
        if (logName == null || logName.equals("")) {
            return null;
        }
        return userDao.findUser(logName);
    }

    /**
     * @description 通过用户id查找用户信息
     * @param userId
     * @return UserEntity
     */
    private UserEntity findById(Long userId) {
        return userDao.findById(userId);
    }

    /**
     * @description 添加用户
     * @param userEntity
     * @return String（状态信息）
     */
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

    /**
     * @description 修改用户名称
     * @param loginUserName
     * @param userName
     * @return boolean
     */
    public boolean updateUserName(String loginUserName, String userName) {
        if (userName.length() <= 0) {
            return false;
        }
        userDao.updateName(loginUserName, userName);
        return true;
    }

    /**
     * @description 修改用户密码
     * @param logName
     * @param oldPassword
     * @param newPassword
     * @return boolean
     */
    public boolean updatePassword(String logName, String oldPassword, String newPassword) {
        UserEntity check = findByName(logName);
        if (null == check || oldPassword.isEmpty() || newPassword.isEmpty()) {
            return false;
        }
        if (!oldPassword.equals(check.getPassWord()) || newPassword.length() < 7) {
            return false;
        }
        try{
            SimpleHash oldpasswd = new SimpleHash("md5", oldPassword, "ccsi", 2);
            SimpleHash newpasswd = new SimpleHash("md5", newPassword, "ccsi", 2);
            userDao.updatePassword(check.getId(), oldpasswd.toHex(), newpasswd.toHex());
            return true;
        }
        catch (Exception e) {
            return true;
        }
    }

    /**
     * @decription 删除用户
     * @param userId
     * @param adminName
     * @param password
     * @return boolean
     */
    public boolean delete(long userId, String adminName, String password) {
        try{
            UserEntity adminEntity = findByName(adminName);
            if (adminEntity.getPassWord().equals(password)) {
                userDao.delUser(userId);
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }

    /***
     * @description 通过用户名查找用户权限
     * @param userName
     * @return RoleEntity
     * @throws Exception
     */
    public RoleEntity findUserRoleByUserName(String userName) throws Exception {
        if (userName.isEmpty()) {
            throw new Exception();
        }
        UserRoleEntity userRoleEntity = userRoleDao.findUserAndRole(userName);
        RoleEntity roleEntity = roleDao.findRole(userRoleEntity.getForRoleId());
        return roleEntity;
    }
}
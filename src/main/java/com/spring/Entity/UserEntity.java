package com.spring.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by Arabira on 2016/10/21.
 */
//用户实体
@Alias("user")
public class UserEntity {
    private Long id;
    private String loginName;
    private String userName;
    private String passWord;
    private String primarySalt;
    private boolean locked;
    private RoleEntity primaryRole;
    private RoleEntity[] otherRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPrimarySalt() {
        return primarySalt;
    }

    public void setPrimarySalt(String primarySalt) {
        this.primarySalt = primarySalt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public RoleEntity getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(RoleEntity primaryRole) {
        this.primaryRole = primaryRole;
    }

    public RoleEntity[] getOtherRole() {
        return otherRole;
    }

    public void setOtherRole(RoleEntity[] otherRole) {
        this.otherRole = otherRole;
    }
}

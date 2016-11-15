package com.spring.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by Arabira on 2016/10/22.
 */
//用户和角色对应的实体
@Alias("userRole")
public class UserRoleEntity {
    private Long forUserId;
    private Long forRoleId;
    private boolean primary;
    private UserEntity userEntity;
    private RoleEntity roleEntity;
    public Long getForUserId() {
        return forUserId;
    }

    public void setForUserId(Long forUserId) {
        this.forUserId = forUserId;
    }

    public Long getForRoleId() {
        return forRoleId;
    }

    public void setForRoleId(Long forRoleId) {
        this.forRoleId = forRoleId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}

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
}

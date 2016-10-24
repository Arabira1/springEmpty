package com.spring.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by Arabira on 2016/10/22.
 */
//角色的实体，下一次提交使用
@Alias("role")
public class RoleEntity {
    private Long roleId;
    private String role;
    private String description;
    private boolean available;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

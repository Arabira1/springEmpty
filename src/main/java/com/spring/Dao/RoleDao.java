package com.spring.Dao;

import com.spring.Entity.RoleEntity;
import com.spring.Entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Arabira on 2016/10/22.
 */
@Repository
public interface RoleDao {
    public RoleEntity findRole(String role);
    public void addRole(RoleEntity roleEntity);
    public void delRole(RoleEntity roleEntity);
    public void updateRoleName(String roleName);
    public void updateRoleDescription(String description);

    public RoleEntity findUserRole(Long userId);
    public RoleEntity[] findOtherRole(Long userId);
    public void updateUserRole(UserRoleEntity userRoleEntity);
    public void delUserRole(UserRoleEntity userRoleEntity);
    public void addUserRole(UserRoleEntity userRoleEntity);
}

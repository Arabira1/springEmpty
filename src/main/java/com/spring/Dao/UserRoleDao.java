package com.spring.Dao;


import com.spring.Entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Arabira on 2016/11/5.
 */
@Repository
public interface UserRoleDao {
    public UserRoleEntity findUserAndRole(String userName);
}

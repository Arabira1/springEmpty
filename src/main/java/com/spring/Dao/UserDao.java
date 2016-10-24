package com.spring.Dao;

import com.spring.Entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Arabira on 2016/10/21.
 */
@Repository
//详细设置在resources/Mapper/UserMapper.xml中
public interface UserDao {
    public UserEntity findUser(@Param("userName") String userName);
    public UserEntity findById(@Param("id") Long userId);
    public void addUser(UserEntity userEntity);
    public void delUser(UserEntity userEntity);
    public void updateName(@Param("id") Long id, @Param("newName") String newUserName);
    public void updatePassword(@Param("id") Long id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);
}

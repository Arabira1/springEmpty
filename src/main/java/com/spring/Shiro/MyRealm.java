package com.spring.Shiro;

import com.spring.Entity.UserEntity;
import com.spring.Service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Arabira on 2016/10/21.
 */
public class MyRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;

    //权限验证部分
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    //登录验证部分
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) atoken;
        String userName = token.getUsername();
        UserEntity userEntity = userService.findByName(userName);
        if (userEntity == null) {
            throw new UnknownAccountException();
        }
        if (userEntity.isLocked()) {
            throw new LockedAccountException();
        }
        /**
         * 此处使用了4个参数的构造方法，说明使用了安全策略（即使用了密码加密），
         * 如果使用的是三个参数的构造方法则说明未使用安全策略；但是如果在credentialsMatcher
         * 中配置了加密的相关参数但这里未使用安全策略则会报错
         * */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, userEntity.getPassWord(), ByteSource.Util.bytes(userEntity.getPrimarySalt()), getName());
        //交给Matcher处理,如果配置了的话
        return info;
    }
}

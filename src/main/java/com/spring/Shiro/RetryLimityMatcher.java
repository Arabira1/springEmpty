package com.spring.Shiro;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * Created by Arabira on 2016/10/21.
 */
public class RetryLimityMatcher extends HashedCredentialsMatcher {

    private Ehcache passwordRetryCache;

    public RetryLimityMatcher(String cacheFile, String cacheName) {
        //使用缓存的配置方法
        CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource(cacheFile));
        passwordRetryCache = cacheManager.getCache(cacheName);
    }

    //对登录次数进行限制操作
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        Element element = passwordRetryCache.get(username);
        if(element == null) {
            element = new Element(username , new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
        /**
         * 验证controller传进来的token和realm传进来的token中信息是否相同,
         * 不相同则返回false，相同返回true
         */
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //登录验证正确，删除在缓存中的记录信息
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}

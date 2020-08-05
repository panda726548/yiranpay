package com.yiran.framework.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.yiran.common.constant.Constants;
import com.yiran.common.constant.ShiroConstants;
import com.yiran.common.exception.user.UserPasswordNotMatchException;
import com.yiran.common.exception.user.UserPasswordRetryLimitExceedException;
import com.yiran.common.utils.MessageUtils;
import com.yiran.framework.manager.AsyncManager;
import com.yiran.framework.manager.factory.AsyncFactory;
import com.yiran.project.system.user.domain.User;
import com.yiran.project.tool.ues.enums.EncryptType;
import com.yiran.project.tool.ues.service.IUesServiceClient;

/**
 * 登录密码方法
 * 
 * @author yiran
 */
@Component
public class PasswordService
{
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;
    @Autowired
    private IUesServiceClient uesServiceClient;
    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    public void validate(User user, String password)
    {
        String loginName = user.getLoginName();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(User user, String newPassword)
    {
    	System.out.println("输入的密码：" + newPassword);
    	String  password = uesServiceClient.getDataByTicket(user.getPassword(), EncryptType.AES);
    	System.out.println("解密后的密码：" + password);
        return newPassword.equals(password);
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    public void unlock(String loginName)
    {
        loginRecordCache.remove(loginName);
    }

    public static void main(String[] args)
    {
        System.out.println(new PasswordService().encryptPassword("admin", "admin123", "111111"));
        System.out.println(new PasswordService().encryptPassword("ry", "admin123", "222222"));
    }
}

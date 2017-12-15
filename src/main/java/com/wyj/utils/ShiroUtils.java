package com.wyj.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wyj.entity.system.User;

/**
 * shiro工具类
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年12月15日 下午8:22:45
 */
public class ShiroUtils {
    
    public static User getUserEntity() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    
    /**
     * 当前登录用户id
     * @return
     */
    public static Long getUserId() {
        return getUserEntity().getUserId();
    }
    
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}

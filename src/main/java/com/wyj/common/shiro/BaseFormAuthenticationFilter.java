package com.wyj.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.wyj.common.entity.Retval;
import com.wyj.utils.WebUtils;

/**
 * shiro 认证
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年12月15日 下午8:22:45
 */
public class BaseFormAuthenticationFilter extends FormAuthenticationFilter {


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if(WebUtils.isAjax(httpReq)) {
            System.err.println("ajax 登录验证");
            HttpServletResponse httpRes = (HttpServletResponse) response;
            Retval retval = Retval.newInstance();
            retval.fail("未登录");
            WebUtils.write(httpRes, retval);
        }
        return false;
    }
	
}

package com.shiro.logical.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @Author jianglanglang
 * @Date 2020/12/21 14:11
 * @Description 自定义session管理
 */
public class SessionManager extends DefaultWebSessionManager {

    /*传统结构项目中，shiro从cookie中读取sessionId以此来维持会话，在前后端分离的项目中（也可在移动APP项目使用）
    我们选择在ajax的请求头中传递sessionId，因此需要重写shiro获取sessionId的方式。
    自定义MySessionManager类继承DefaultWebSessionManager类，重写getSessionId方法*/


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader("Authorization");
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
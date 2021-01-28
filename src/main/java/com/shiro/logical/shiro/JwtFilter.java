package com.shiro.logical.shiro;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author jianglanglang
 * @Date 2021/1/26 15:48
 * @Description jwtToken 过滤器
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    /**
     * 登录标识
     */
    private static String LOGIN_SIGN = "Token";


    /**
     * 执行登录认证
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        logger.warn("----------------进入JwtFilter 过滤器 isAccessAllowed方法被调用----------------");
        try {
            return executeLogin(request, response);
        } catch (AuthenticationException  e) {
            logger.error("----------------JwtFilter过滤器验证token授权失败！----------------");
            outPrintWriter((HttpServletResponse) response, e.getMessage());
            // 返回false始终会调用executeLogin方法
            return false;
        }
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        /*这里我们详细说明下为什么重写
        可以对比父类方法，只是将executeLogin方法调用去除了
        如果没有去除将会循环调用doGetAuthenticationInfo方法*/
        this.sendChallenge(request, response);
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws IOException {
        logger.warn("----------------进入JwtFilter 过滤器 executeLogin方法被调用----------------");
        // 这里和前端约定要求前端将jwtToken放在请求的Header部分
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(LOGIN_SIGN);
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm登录认证；最终还是调用SystemRealm进行的认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 认证失败输出打印
     * @param response
     * @param string
     */
    public static void outPrintWriter(HttpServletResponse response, String string){
        PrintWriter printWriter;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 3010); jsonObject.put("msg", string);
        jsonObject.put("count", 0); jsonObject.put("data", "");
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            printWriter = response.getWriter();
            printWriter.write(JSONObject.toJSONString(jsonObject));
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对跨域提供支持
     */
    /*@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }*/



}
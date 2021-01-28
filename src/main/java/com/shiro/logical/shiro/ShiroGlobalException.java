package com.shiro.logical.shiro;

import com.shiro.common.returns.JsonResult;
import com.shiro.common.returns.ResultCode;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author jianglanglang
 * @Date 2021/1/21 15:45
 * @Description shiro处理全局异常
 */
@ControllerAdvice
public class ShiroGlobalException {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public JsonResult UnauthorizedException() {
        return JsonResult.failure(ResultCode.SHIRO_UNAUTHORIZED.getCode(), "未授权，无此操作权限!");
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public JsonResult AuthorizationException() {
        return JsonResult.failure(ResultCode.SHIRO_AUTHENTICATION_FAILURE.getCode(), "认证失败!");
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult ShiroException() {
        return JsonResult.failure(ResultCode.SHIRO_ACCESS_AUTHORITY.getCode(), "您没有权限访问！");
    }
}
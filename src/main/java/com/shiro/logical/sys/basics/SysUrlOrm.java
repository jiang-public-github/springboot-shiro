package com.shiro.logical.sys.basics;

import com.shiro.common.returns.JsonResult;
import com.shiro.common.returns.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author jianglanglang
 * @Date 2021/1/15 17:00
 * @Description 系统的路径映射类
 */
@RestController
@RequestMapping(value = "a/sys/url", produces = "application/json;charset=utf-8")
public class SysUrlOrm {

    // 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
    @ApiIgnore
    @RequestMapping(value = "/unauth")
    public JsonResult unauth() {
        return JsonResult.failure(ResultCode.PARAM_IS_BLANK.getCode(), "未授权，请先登录!");
    }




}
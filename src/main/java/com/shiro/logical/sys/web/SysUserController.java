package com.shiro.logical.sys.web;

import com.alibaba.fastjson.JSONObject;
import com.shiro.common.returns.JsonResult;
import com.shiro.common.returns.ResultCode;
import com.shiro.common.util.EhCacheUtil;
import com.shiro.logical.sys.entity.SysUser;
import com.shiro.logical.sys.service.ISysUserService;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Api(tags = {"用户管理"})
@RestController
@RequestMapping(value = "a/sys/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;


    @DynamicParameters(properties = {
            @DynamicParameter(name = "loginName", value = "用户名", required = true, dataTypeClass = String.class),
            @DynamicParameter(name = "password", value = "用户密码", required = true, dataTypeClass = String.class),
    })
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public JsonResult login(@RequestBody JSONObject params) throws IOException {
        if (StringUtils.isBlank(params.getString("loginName")) || StringUtils.isBlank(params.getString("password"))) {
            return JsonResult.failure(ResultCode.PARAM_IS_BLANK.getCode(), "请输入用户名和密码!");
        }
        return sysUserService.login(params);
    }

    @ApiOperation(value = "用户退出")
    @GetMapping(value = "/logout")
    public JsonResult logout() {
        return sysUserService.logout();
    }


    @DynamicParameters(properties = {
            @DynamicParameter(name = "page", value = "页码", required = true, dataTypeClass = Integer.class),
            @DynamicParameter(name = "limit", value = "数量", required = true, dataTypeClass = Integer.class),
    })
    @ApiOperation(value = "分页查询用户")
    @PostMapping(value = "/selectAllUser")
    public JsonResult selectAllUser(@RequestBody JSONObject params) {
        return sysUserService.selectAllUser(params);
    }


    @ApiOperation(value = "登录访问")
    @GetMapping(value = "/shiro")
    public JsonResult shiro() {
        String loginName = (String) SecurityUtils.getSubject().getPrincipal();
        Object object = EhCacheUtil.get("userCache", loginName);
        return JsonResult.success(-1000, "你已经登录了系统，现在可以进行操作了！",0, object != null ?object:"数据已经缓存失效！");
    }

    @RequiresPermissions("role:sys:show")
    @ApiOperation(value = "权限访问show")
    @GetMapping("/show")
    public String show() {
        return "测试，你有这个权限！";
    }

    @RequiresPermissions("role:sys:from")
    @ApiOperation(value = "权限访问from")
    @GetMapping("/from")
    public String from() {
        return "测试，你有这个权限！";
    }
}
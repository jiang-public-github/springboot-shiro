package com.shiro.logical.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.common.returns.JsonResult;
import com.shiro.common.returns.ResultCode;
import com.shiro.logical.sys.dao.SysUserMapper;
import com.shiro.logical.sys.entity.SysUser;
import com.shiro.logical.sys.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public JsonResult login(JSONObject params) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(params.getString("loginName"), params.getString("password"));
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                return JsonResult.success(ResultCode.USER_LOGIN_SUCCESS.getCode(), "登录成功!");
            }
        } catch (Exception e) {
            String msg = e.getClass().getName();
            if (msg != null) {
                if (IncorrectCredentialsException.class.getName().equals(msg)) {
                    return JsonResult.failure(ResultCode.USER_LOGIN_ERROR.getCode(), "密码错误!");
                } else if (UnknownAccountException.class.getName().equals(msg)) {
                    return JsonResult.failure(ResultCode.USER_LOGIN_ERROR.getCode(), "账号不存在!");
                } else if (DisabledAccountException.class.getName().equals(msg)) {
                    return JsonResult.failure(ResultCode.USER_LOGIN_ERROR.getCode(), "该帐号已被禁止登录，请联系系统管理员！");
                } else {
                    return JsonResult.failure(ResultCode.PROGRAM_EXCEPTION.getCode(), "登录异常!");
                }
            }
        }
        return null;
    }

    @Async
    @Override
    public JsonResult logout() {
        SecurityUtils.getSubject().logout();
        return JsonResult.success(ResultCode.USER_LOGOUT_SUCCESS.getCode(), "退出成功!");
    }

    @Override
    public JsonResult selectAllUser(JSONObject params) {
        PageHelper.startPage(params.getInteger("page"), params.getInteger("limit"));
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag","0")
                .orderBy( true,false,"create_date");
        List<SysUser> userList = this.sysUserMapper.selectList(queryWrapper);
        int count = (int) new PageInfo<>(userList).getTotal();
        return JsonResult.success(ResultCode.SUCCESS.getCode(), "查询成功!", count, userList);
    }
}
package com.shiro.logical.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.common.returns.JsonResult;
import com.shiro.common.returns.ResultCode;
import com.shiro.common.util.BasicsUtils;
import com.shiro.common.util.EhCacheUtil;
import com.shiro.common.util.JwtUtil;
import com.shiro.logical.sys.dao.SysUserMapper;
import com.shiro.logical.sys.entity.SysUser;
import com.shiro.logical.sys.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public JsonResult login(JSONObject params) {
        String loginName = params.getString("loginName");
        String password = params.getString("password");
        try {
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            // 根据loginName/phone先查询用户的登录名用于密码的加盐值
            queryWrapper.eq("del_flag", 0).eq("login_name", loginName).or().eq("phone", loginName);
            SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
            if (Optional.ofNullable(sysUser).isPresent()) {
                // 匹配密码
                if (!BasicsUtils.simpleHash(password, sysUser.getLoginName()).equals(sysUser.getPassword())) {
                    return JsonResult.success(ResultCode.USER_LOGIN_ERROR.getCode(), "密码错误!");
                }
                if (sysUser.getIsForbidden().equals("1")) {
                    return JsonResult.success(ResultCode.USER_ACCOUNT_FORBIDDEN.getCode(), "该帐号已被禁止登录，请联系系统管理员！");
                }
                // 缓存用户信息
                EhCacheUtil ehCacheUtil = EhCacheUtil.getInstance();
                ehCacheUtil.put("userCache", sysUser.getLoginName(), sysUser);
                // 生成token
                Map<String, Object> chaim = new HashMap<>(16);
                chaim.put("userName", loginName);
                chaim.put("userId", sysUser.getId());
                String token = JwtUtil.createToken(loginName, 2, chaim);
                return JsonResult.success(ResultCode.USER_LOGIN_SUCCESS.getCode(), "登录成功!", 0, token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(ResultCode.PROGRAM_EXCEPTION.getCode(), "登录异常!");
        }
        return JsonResult.success(ResultCode.USER_LOGIN_ERROR.getCode(), "账号不存在！");
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
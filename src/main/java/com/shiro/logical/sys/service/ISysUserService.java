package com.shiro.logical.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiro.common.returns.JsonResult;
import com.shiro.logical.sys.entity.SysUser;

import java.io.IOException;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 用户登录
     * @param params
     * @return
     * @throws IOException
     */
    JsonResult login(JSONObject params) throws IOException;

    /**
     * 用户注销
     * @return
     */
    JsonResult logout();

    /**
     * 分页查询用户
     * @param params
     * @return
     */
    JsonResult selectAllUser(JSONObject params);
}

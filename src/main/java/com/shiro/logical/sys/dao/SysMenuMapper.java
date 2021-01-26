package com.shiro.logical.sys.dao;

import com.shiro.logical.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据登录名、手机号查询用户授权菜单
     * @param loginName
     * @return
     */
    List<Map<String,String>> selectUserMenuByLoninName(@Param("loginName") String loginName);

}

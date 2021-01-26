package com.shiro.logical.sys.dao;

import com.shiro.logical.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据登录名、手机号查询用户所属角色
     * @param loginName
     * @return
     */
    List<Map<String,String>> selectUserRoleByLoninName(@Param("loginName") String loginName);

}

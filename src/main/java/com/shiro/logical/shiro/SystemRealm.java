package com.shiro.logical.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shiro.common.util.EhCacheUtil;
import com.shiro.common.util.JwtUtil;
import com.shiro.logical.sys.dao.SysMenuMapper;
import com.shiro.logical.sys.dao.SysRoleMapper;
import com.shiro.logical.sys.dao.SysUserMapper;
import com.shiro.logical.sys.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author jianglanglang
 * @Date 2020/12/17 15:34
 * @Description 系统安全认证类
 */
@Component
public class SystemRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 使用JWT替代原生Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JwtToken;
    }


    /**
     * 授权 查询用户的所属权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("-----------------------shiro授权-----------------------");
        String loginName = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if(StringUtils.isNotBlank(loginName)){
            Set<String> roleSet = new HashSet<>(16);
            Set<String> menuSet = new HashSet<>(16);
            // 给用户设置角色
            List<Map<String,String>> roleList = sysRoleMapper.selectUserRoleByLoninName(loginName);
            roleList.stream().forEach(string -> {
                roleSet.add(string.get("englishName"));
            });
            authorizationInfo.setRoles(roleSet);
            // 给用户设置权限
            List<Map<String,String>> menuList = sysMenuMapper.selectUserMenuByLoninName(loginName);
            menuList.stream().forEach(string -> {
                if (StringUtils.isNotBlank(string.get("permission"))) {
                    for (String permission : StringUtils.split(string.get("permission"),",")){
                        menuSet.add(permission);
                    }
                }
            });
            authorizationInfo.setStringPermissions(menuSet);
        } else {
            throw new AuthenticationException("授权失败，未获取到用户名！");
        }
        return authorizationInfo;
    }



    /**
     * 认证 登录时调用，验证用户输入的信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("-----------------------shiro认证-----------------------");
        String token = (String) authenticationToken.getCredentials();
        if (token == null) {
            throw new AuthenticationException("未检测到token，认证失败！");
        }
        // 验证token
        JwtUtil jwtUtil = new JwtUtil();
        if (!jwtUtil.verifyToken(token)) {
            throw new AuthenticationException("token凭证错误或失效，请重新登录！");
        }
        // 根据登录名或者手机号获取用户信息
        String username = jwtUtil.analysisToken(token).get("subject").toString();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag","0").eq("login_name", username).or().eq("phone", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (Optional.ofNullable(sysUser).isPresent()) {
            if (sysUser.getIsForbidden().equals("1")) {
                throw new AuthenticationException("该帐号已被禁止登录，请联系系统管理员！");
            }
        } else {
            throw new AuthenticationException("账号不存在，非法操作！");
        }
        return new SimpleAuthenticationInfo(username, token, getName());
    }

}
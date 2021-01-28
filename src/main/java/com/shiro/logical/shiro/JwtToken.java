package com.shiro.logical.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author jianglanglang
 * @Date 2021/1/26 15:37
 * @Description 封装token来替换Shiro原生Token
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = -8451637096112402805L;

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
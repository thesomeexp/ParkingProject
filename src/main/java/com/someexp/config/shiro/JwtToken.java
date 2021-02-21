package com.someexp.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义jwtToken
 *
 * @author someexp
 * @date 2020/9/23
 */
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }
}

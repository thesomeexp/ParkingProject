package com.someexp.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义jwtToken
 * @author someexp
 * @date 2020/9/23
 */
public class JwtToken implements AuthenticationToken {

    private String jwtToken;

    public JwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.jwtToken;
    }

    @Override
    public Object getCredentials() {
        return this.jwtToken;
    }
}

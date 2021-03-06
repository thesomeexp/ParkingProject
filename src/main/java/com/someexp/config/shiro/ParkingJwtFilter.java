package com.someexp.config.shiro;

import com.google.gson.Gson;
import com.someexp.common.domain.Result;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证JWT是否合法, 是否过期
 */
@Slf4j
public class ParkingJwtFilter extends AccessControlFilter {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    protected static final String JWT_PREFIX = "Bearer";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // 跨域配置, OPTIONS 预请求不带token
        if (StringUtils.equals(((HttpServletRequest) request).getMethod(), RequestMethod.OPTIONS.name())) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
            httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod() + ",PUT,PATCH");
            httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return checkAuthzHeader(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

    /**
     * 返回真正的jwt
     *
     * @param authorizationHeader
     * @return
     * @throws Exception
     */
    protected String getJwtToken(String authorizationHeader) throws Exception {

        if (authorizationHeader == null || authorizationHeader.length() == 0) {
            throw new AuthenticationException(MsgUtils.get("user.login.jwt.is.empty"));
        }

        if (!authorizationHeader.startsWith(JWT_PREFIX)) {
            throw new AuthenticationException(MsgUtils.get("user.login.jwt.format.illegal"));
        }

        return authorizationHeader.length() > JWT_PREFIX.length() ?
                authorizationHeader.substring(JWT_PREFIX.length() + 1).trim() : null;
    }

    protected boolean checkAuthzHeader(ServletRequest request, ServletResponse response) {
        String authorizationHeader = getAuthzHeader(request);
        try {
            String jwt = getJwtToken(authorizationHeader);
            JwtToken jwtToken = new JwtToken(jwt);
            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
            // 禁止管理员的token使用用户的接口
            String role = JwtUtils.getRoleByToken(jwt);
            if (!"user".equals(role)) {
                throw new BusinessException("请使用用户token");
            }
            return true;
        } catch (AuthenticationException | BusinessException authenticationException) {
            return onLoginFailure(authenticationException, request, response);
        } catch (Exception e) {
            log.error("未知错误JwtFilter.executeLogin(): ", e);
            return onLoginFailure(e, request, response);
        }
    }

    protected boolean onLoginFailure(Exception e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        // 这里嘛, 先200一把梭
        httpResponse.setStatus(HttpStatus.OK.value());
        try {
            httpResponse.getWriter().print(new Gson().toJson(Result.fail(e.getMessage())));
        } catch (IOException ioException) {
            log.error("登录错误IO异常: JwtFilter.onLoginFailure(): ", ioException);
        }
        return false;
    }
}

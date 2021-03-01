package com.someexp.config.shiro;

import com.google.gson.Gson;
import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ParkingJwtFilter extends AccessControlFilter {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    protected static final String JWT_PREFIX = "Bearer";

    private static final Logger log = LoggerFactory.getLogger(ParkingJwtFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // OPTIONS 预请求不带token
        if (StringUtils.equals(((HttpServletRequest) request).getMethod(), RequestMethod.OPTIONS.name())) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
            httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
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

    private String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

    private boolean checkAuthzHeader(ServletRequest request, ServletResponse response) {
        String authorizationHeader = getAuthzHeader(request);

        try {
            if (authorizationHeader == null || authorizationHeader.length() == 0) {
                throw new AuthenticationException(MsgUtils.get("user.login.jwt.is.empty"));
            }

            if (!authorizationHeader.startsWith(JWT_PREFIX)) {
                throw new AuthenticationException(MsgUtils.get("user.login.jwt.format.illegal"));
            }

            String jwt = authorizationHeader.substring(JWT_PREFIX.length() + 1).trim();
            JwtToken jwtToken = new JwtToken(jwt);

            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
            return true;
        } catch (AuthenticationException authenticationException) {
            return onLoginFailure(authenticationException, request, response);
        } catch (Exception e) {
            log.error("未知错误JwtFilter.executeLogin(): ", e);
            return false;
        }
    }

    private boolean onLoginFailure(AuthenticationException authenticationException, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        // 这里嘛, 先200一把梭
        httpResponse.setStatus(HttpStatus.OK.value());
        try {
            httpResponse.getWriter().print(new Gson().toJson(Result.fail(authenticationException.getMessage())));
        } catch (IOException ioException) {
            log.error("登录错误IO异常: JwtFilter.onLoginFailure(): ", ioException);
        }
        return false;
    }
}

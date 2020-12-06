package com.someexp.config.shiro;

import com.google.gson.Gson;
import com.someexp.common.domain.Result;
import com.someexp.common.utils.JwtUtil;
import com.someexp.common.utils.MsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
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
 * 实现对前端请求统一拦截处理
 * 所有请求都会经过这个Filter, 继承官方BasicHttpAuthenticationFilter
 * 然后重写鉴权方法
 *
 * @author someexp
 * @date 2020/9/23
 */
public class JwtFilter extends AuthenticatingFilter {

    private static transient final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    /**
     * 执行顺序: 1
     * 允许客户端发送的OPTIONS请求, 其他请求一律需要携带jwtToken
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return StringUtils.equals(((HttpServletRequest) request).getMethod(), RequestMethod.OPTIONS.name());
    }

    /**
     * 执行顺序: 2(前提是1为false)
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        return executeLogin(request, response);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        AuthenticationToken token = null;
        try {
            token = createToken(request, response);
            if (token == null) {
                String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                        "must be created in order to execute a login attempt.";
                throw new IllegalStateException(msg);
            }
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        } catch (Exception e) {
            log.error("未知错误JwtFilter.executeLogin(): ", e);
            return false;
        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws AuthenticationException {
        return new JwtToken(getRequestJwtToken((HttpServletRequest) request));
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        // 这里嘛, 毕设需要, 200一把梭
        httpResponse.setStatus(HttpStatus.OK.value());
        try {
            httpResponse.getWriter().print(new Gson().toJson(Result.fail(e.getMessage())));
        } catch (IOException ioException) {
            log.error("登录错误IO异常: JwtFilter.onLoginFailure(): ", ioException);
        }
        return false;
    }

    /**
     * 获取请求头中的jwtToken
     *
     * @param httpRequest
     * @return
     */
    private String getRequestJwtToken(HttpServletRequest httpRequest) throws AuthenticationException {
        String jwtToken = null;
        String authorization = httpRequest.getHeader(JwtUtil.getJwtProperties().getHeader());
        if (StringUtils.isEmpty(authorization)) {
            throw new AuthenticationException(MsgUtil.get("user.login.jwt.is.empty"));
        }

        if (!authorization.startsWith(JwtUtil.getJwtProperties().getJwtPrefix())) {
            throw new AuthenticationException(MsgUtil.get("user.login.jwt.format.illegal"));
        } else {
            jwtToken = authorization.substring(JwtUtil.getJwtProperties().getJwtPrefix().length() + 1).trim();
        }
        return jwtToken;
    }
}

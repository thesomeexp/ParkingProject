package com.someexp.config.shiro;

import com.google.gson.Gson;
import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
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
 * 管理员过滤器, 主要用来判断jwt中角色是否包含admin
 *
 * @author someexp
 * @date 2020/10/28
 */
public class AdminFilter extends AuthorizationFilter {

    private static transient final Logger log = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        if (StringUtils.equals(((HttpServletRequest) request).getMethod(), RequestMethod.OPTIONS.name())) {
            return true;
        } else {
            Subject subject = ShiroUtils.getSubject();
            return subject.isPermitted("admin");
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        // 这里嘛, 毕设需要, 200一把梭
        httpResponse.setStatus(HttpStatus.OK.value());
        try {
            httpResponse.getWriter().print(new Gson().toJson(Result.fail(MsgUtils.get("admin.permission.denied"))));
        } catch (IOException ioException) {
            log.error("admin权限错误IO异常: JwtFilter.onLoginFailure(): ", ioException);
        }
        return false;
    }

}

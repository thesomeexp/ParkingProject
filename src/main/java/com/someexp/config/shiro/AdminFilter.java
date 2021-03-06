package com.someexp.config.shiro;

import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 管理员过滤器, 主要用来判断jwt中角色是否包含admin
 *
 * @author someexp
 * @date 2020/10/28
 */
@Slf4j
public class AdminFilter extends ParkingJwtFilter {

    @Override
    protected boolean checkAuthzHeader(ServletRequest request, ServletResponse response) {
        String role;
        try {
            String jwt = getJwtToken(getAuthzHeader(request));
            JwtToken jwtToken = new JwtToken(jwt);
            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
            role = JwtUtils.getRoleByToken(jwt);
            if (!"admin".equals(role)) {
                throw new AuthenticationException(MsgUtils.get("admin.filter.not.allow"));
            }
            return true;
        } catch (Exception e) {
            log.error("Admin过滤器授权异常", e);
            return onLoginFailure(e, request, response);
        }
    }

}

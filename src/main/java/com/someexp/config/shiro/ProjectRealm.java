package com.someexp.config.shiro;

import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.modules.sys.domain.entity.ShiroUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @author someexp
 * @date 2020/9/23
 */
@Component
public class ProjectRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 操作管理员账号相关时, 验证是否有权限, 必须认证该账号的状态是否正常(用Aop吧, 不用Shiro了)
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 登录的合法性认证, 登录是在登录接口完成的, 这里只是验证jwt是否过期
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();

        Long userId;
        String role;
        try {
            userId = JwtUtils.getIdByToken(jwt);
            role = JwtUtils.getRoleByToken(jwt);
        } catch (ExpiredJwtException expiredJwtException) {
            throw new AuthenticationException(MsgUtils.get("user.login.expire"));
        } catch (Exception e) {
            throw new AuthenticationException(MsgUtils.get("user.login.verify.fail"));
        }

        if (userId == null) {
            throw new AuthenticationException(MsgUtils.get("jwt.parameter.is.null", new String[]{"id"}));
        }
        if (role == null) {
            throw new AuthenticationException(MsgUtils.get("jwt.parameter.is.null", new String[]{"role"}));
        }

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(userId);
        shiroUser.setRole(role);

        return new SimpleAuthenticationInfo(shiroUser, userId, getName());
    }

    /**
     * 验证凭证的逻辑, 如果验证错误抛出异常
     *
     * @param token
     * @param info
     * @throws AuthenticationException
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {

    }
}

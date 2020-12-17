package com.someexp.config.shiro;

import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.sys.domain.entity.User;
import com.someexp.modules.sys.mapper.ShiroMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author someexp
 * @date 2020/9/23
 */
@Component
public class JwtRealm extends AuthorizingRealm {

    private static transient final Logger log = LoggerFactory.getLogger(JwtRealm.class);

    @Resource
    private ShiroMapper shiroMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 检测权限时调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = ShiroUtils.getUser();
        Set<String> permsSet = new HashSet<>();
        // 因为此时只有一个, 简单设置一下就好
        permsSet.add(user.getRole());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 登录的合法性认证, 其实已经登陆, 这里验证jwt的合法性
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwtToken = (String) token.getPrincipal();

        Long userId;
        try {
            userId = JwtUtils.getIdByToken(jwtToken);
        } catch (ExpiredJwtException expiredJwtException) {
            throw new AuthenticationException(MsgUtils.get("user.login.expire"));
        } catch (Exception e) {
//            log.error("认证失败JwtRealm.AuthenticationInfo()", e);
            throw new AuthenticationException(MsgUtils.get("user.login.verify.fail"));
        }

        User user = shiroMapper.getById(userId);
        if (user == null) {
            throw new AuthenticationException(MsgUtils.get("user.information.expired"));
        }

        if (user.getStatus().equals(2L)) {
            throw new AuthenticationException(MsgUtils.get("user.account.locked"));
        }

        return new SimpleAuthenticationInfo(user, userId, getName());
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

package com.someexp.config.shiro;

import com.someexp.common.utils.JwtUtil;
import com.someexp.common.utils.MsgUtil;
import com.someexp.modules.user.domain.entity.User;
import com.someexp.modules.user.mapper.UserMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author someexp
 * @date 2020/9/23
 */
@Component
public class JwtRealm extends AuthorizingRealm {

    private static transient final Logger log = LoggerFactory.getLogger(JwtRealm.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

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
        System.out.println("调用了授权");
        Set<String> permsSet = new HashSet<>();
        permsSet.add("user:11");

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
            userId = JwtUtil.getIdByToken(jwtToken);
        } catch (ExpiredJwtException expiredJwtException) {
            throw new AuthenticationException(MsgUtil.get("user.login.expire"));
        } catch (Exception e) {
            log.error("认证失败JwtRealm.AuthenticationInfo()");
            e.printStackTrace();
            throw new AuthenticationException(MsgUtil.get("user.login.verify.fail"));
        }

        User user = userMapper.getById(userId);
        if (user == null) {
            throw new AuthenticationException(MsgUtil.get("user.information.expired"));
        }

        if (user.getStatus().equals(2L)) {
            throw new AuthenticationException(MsgUtil.get("user.account.locked"));
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

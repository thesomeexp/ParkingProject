package com.someexp.config.shiro;

import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.admin.domain.entity.Admin;
import com.someexp.modules.sys.domain.entity.ShiroUser;
import com.someexp.modules.sys.mapper.ShiroMapper;
import com.someexp.modules.user.domain.entity.User;
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
public class ProjectRealm extends AuthorizingRealm {

    private static transient final Logger log = LoggerFactory.getLogger(ProjectRealm.class);

    @Resource
    private ShiroMapper shiroMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 对于一些能更改资源状态的管理员接口, 必须认证该账号的状态是否正常
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = ShiroUtils.getShiroUser();
        Set<String> permsSet = new HashSet<>();
        // 用角色做权限, 懒得区分了, 这个role在登陆时设定了
        permsSet.add(shiroUser.getRole());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
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

    private User getUser(Long userId) {
        User user = shiroMapper.getUserById(userId);
        if (user == null) {
            throw new AuthenticationException(MsgUtils.get("user.information.expired"));
        }

        // 锁定的状态为2
        if (user.getStatus() == 2) {
            throw new AuthenticationException(MsgUtils.get("user.account.locked"));
        }
        return user;
    }

    private Admin getAdmin(Long userId) {
        Admin admin = shiroMapper.getAdminById(userId);
        if (admin == null) {
            throw new AuthenticationException(MsgUtils.get("user.information.expired"));
        }

        if (admin.getStatus() == 2) {
            throw new AuthenticationException(MsgUtils.get("user.account.locked"));
        }
        return admin;
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

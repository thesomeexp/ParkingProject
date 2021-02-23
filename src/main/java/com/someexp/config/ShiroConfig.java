package com.someexp.config;

import com.someexp.config.shiro.AdminFilter;
import com.someexp.config.shiro.JwtRealm;
import com.someexp.config.shiro.ParkingJwtFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * Shiro配置
 *
 * @author someexp
 * @date 2020/9/23
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Lazy DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();

        filters.put("jwt", new ParkingJwtFilter());
        filters.put("admin", new AdminFilter());
        shiroFilterFactoryBean.setFilters(filters);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        // 访问图片不需要验证
        filterChainDefinitionMap.put("/parking/photo/**", "anon");

        filterChainDefinitionMap.put("/admin/login", "anon");
        filterChainDefinitionMap.put("/admin/**", "jwt, admin");

        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(JwtRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

}

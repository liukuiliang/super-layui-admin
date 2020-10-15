package com.lkl.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @date 2020/10/15
 * @author 刘奎亮
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap();
        /* 放行文件 */
        filterMap.put("/component/**","anon");
        filterMap.put("/admin/**","anon");
        filterMap.put("/js/**","anon");
        /* 授权文件 */

        /* 认证文件 */
        filterMap.put("/readme/**","authc");
        filterMap.put("/view/**","authc");
        filterMap.put("/*","authc");

        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/view/error/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }

    @Bean("authRealm")
    public AuthRealm getAuthRealm(){
        return new AuthRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}

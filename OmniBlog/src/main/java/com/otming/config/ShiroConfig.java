package com.otming.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();



        //添加shiro的内置过滤器
        /*
        anon：无需认证
        authc:必须认证了才能访问
        user:拥有记住我功能才能用
        perms:拥有对某个资源的权限才能访问
        role:拥有某个角色权限才能访问
         */

        filterChainDefinitionMap.put("/intercept/torelease","authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);



        bean.setLoginUrl("/shiro/toLogin");

        return bean;
    }


    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);

        return securityManager;
    }


    //创建Realm对象，需要自定义类

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }




}

package com.liudao51.datacenter.core.config;

import com.liudao51.datacenter.core.shiro.UserRealm;
import com.liudao51.datacenter.core.util.ShiroUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * class: shiro配置类
 * <p>
 * 描述: 用于配置Shiro的信息
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean过滤器工厂:
     * 主要设置过滤规则,权限规则
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录入口url(如不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射)
        shiroFilterFactoryBean.setLoginUrl("/account/login/index");
        //设置无权限时跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/account/unauthorized");
        //设置登录成功后要跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/index");


        //设置shiro内置过滤器，实现权限相关的url拦截
        /**
         * 常见过滤器：
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user：如果使用Remember Me的功能，可以直接访问
         * perms：该资源必须得到资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //登录: 开放权限
        filterChainDefinitionMap.put("/account/login", "anon");
        filterChainDefinitionMap.put("/account/login/index", "anon");

        //游客: 开放权限
        filterChainDefinitionMap.put("/guest/**", "anon");

        //swagger: 开放权限
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        //用户: 需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "roles[user]");

        //管理员: 需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");

        //其余接口一律拦截（该规则必须要放在所有权限设置的最后,不然会导致所有url都会被拦截）
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        System.out.println("Shiro拦截器工厂类注入成功");

        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager权限管理:
     * 该类组合了登陆/登出/权限/session的处理
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getdefaultDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm); //关联Realm

        return securityManager;
    }

    /**
     * HashedCredentialsMatcher凭证匹配器:
     * Shiro的密码校验是交给SimpleAuthenticationInfo进行处理的,所以这里要指定算法规则,以便与数据库密码加密规则保持一致
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtil.ALGORITHM_NAME); //散列算法
        hashedCredentialsMatcher.setHashIterations(ShiroUtil.HASH_ITERATIONS); //散列次数
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return hashedCredentialsMatcher;
    }

    /**
     * UserRealm自定义的认证类:
     * 该类继承自AuthorizingRealm,主要负责用户的认证/权限的处理(可以参考JdbcRealm的实现)
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());  //指定凭证匹配器
        
        return userRealm;
    }
}

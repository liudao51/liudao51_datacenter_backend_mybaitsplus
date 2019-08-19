package com.liudao51.datacenter.core.config;

import com.liudao51.datacenter.core.shiro.ShiroRealm;
import com.liudao51.datacenter.core.util.ShiroUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * class: shiro配置类
 * <p>
 * 描述: 用于配置Shiro的信息
 */
@Configuration
public class ShiroConfig {

    private static final int SESSION_EXPIRE_SECOND = 1800; //秒
    private static final long SESSION_EXPIRE_MILLIS_SECOND = 1800000L;  //毫秒

    /**
     * ShiroFilterFactoryBean过滤器:
     * 主要设置过滤规则,权限规则
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());

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

        //开放权限: 登录
        filterChainDefinitionMap.put("/account/login", "anon");
        filterChainDefinitionMap.put("/account/login/index", "anon");
        filterChainDefinitionMap.put("/account/unauthorized", "anon");

        //开放权限: 游客
        filterChainDefinitionMap.put("/guest/**", "anon");

        //开放权限: swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        //开放权限: 用户(需要角色权限“user”)
        filterChainDefinitionMap.put("/user/**", "roles[user]");

        //开放权限: 管理员(需要角色权限“admin”)
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");

        //开放权限: 其余接口一律拦截（该规则必须要放在所有权限设置的最后,不然会导致所有url都会被拦截）
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager安全管理器:
     * 该类组合了登陆/登出/权限/session的处理
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //指定缓存管理器(使用redis)
        securityManager.setCacheManager(cacheManager());

        //指定session管理器(使用redis)
        securityManager.setSessionManager(sessionManager());

        //指定记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());

        //关联ShiroRealm自定义的认证类
        securityManager.setRealm(shiroRealm());

        return securityManager;
    }

    /**
     * ShiroRealm自定义的认证类:
     * 该类继承自AuthorizingRealm,主要负责用户的认证/权限的处理(可以参考JdbcRealm的实现)
     */
    @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  //指定凭证匹配器
        //shiroRealm.setCachingEnabled(true);
        //shiroRealm.setCacheManager(redisCacheManager());

        return shiroRealm;
    }


    /**
     * HashedCredentialsMatcher凭证匹配器:
     * 由于Shiro的密码校验是交给SimpleAuthenticationInfo进行处理的,所以这里要指定算法规则,以便与数据库密码加密规则保持一致
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
     * RedisCacheManager缓存管理器:
     * 需要添加到securityManager中
     */
    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());

        //redis缓存有效时间(单位秒,默认60分钟,即1800),似乎没起作用
        //redisCacheManager.setExpire(1800);

        return redisCacheManager;
    }

    /**
     * Session管理器
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setCacheManager(cacheManager());

        //全局会话超时时间(单位毫秒,默认30分钟,即1800000L)
        sessionManager.setGlobalSessionTimeout(SESSION_EXPIRE_MILLIS_SECOND);

        //是否开启删除无效的session对象(默认为true)
        sessionManager.setDeleteInvalidSessions(true);

        //是否开启定时调度器进行检测过期session(默认为true)
        sessionManager.setSessionValidationSchedulerEnabled(true);

        //设置session失效的扫描时间,清理用户直接关闭浏览器造成的孤立会话(单位毫秒,默认为60分钟,即3600000L)
        //设置该属性就不需要设置ExecutorServiceSessionValidationScheduler了,因为setSessionValidationInterval底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        sessionManager.setSessionValidationInterval(SESSION_EXPIRE_MILLIS_SECOND);

        //取消url后面的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    /**
     * rememberMe管理器:
     * cookie管理对象——记住我功能
     */
    @Bean(name = "rememberMeManager")
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());

        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));

        return cookieRememberMeManager;
    }

    /**
     * Redis管理器
     */
    @Bean(name = "redisManager")
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("193.168.1.12");
        redisManager.setPort(6379);
        redisManager.setPassword("123456");

        //设置redis连接超时时间(单位毫秒,默认2秒)
        //redisManager.setTimeout(2000);

        return redisManager;
    }

    /**
     * RedisSessionDAO:
     * shiro的RedisSessionDao层的实现
     */
    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());

        //session在redis中的保存时间(单位秒,默认-2,即取sessionManager.setGlobalSessionTimeout),最好大于session会话超时时间(sessionManager.setGlobalSessionTimeout)
        redisSessionDAO.setExpire(SESSION_EXPIRE_SECOND);

        return redisSessionDAO;
    }

    /**
     * cookie对象的JSESSIONID属性模板：
     * 默认属性名为JSESSIONID, 由于属性名JSESSIONID容易与SERVLET容器名冲突,所以这里需要重新定义为datacenter-sid
     *
     * @return
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        //这个参数是cookie的名称,对应前端的Request-Headers的Cookie:JSESSIONID=xxx
        SimpleCookie simpleCookie = new SimpleCookie("dc-sid");

        //设置Response-Header的Set-Cookie属性中的Httponly
        //httponly属性设为true会增加对xss防护的安全系数(即只能通过http访问,不能javascript无法访问,以防止xss读取cookie)
        simpleCookie.setHttpOnly(true);

        //设置Response-Header的Set-Cookie属性中的Path(相对是根域名的路径)
        simpleCookie.setPath("/");

        //设置Response-Header的Set-Cookie属性中的Max-Age(有效时间,单位为秒)
        //maxAge=-1表示浏览器关闭时失效此Cookie; 如果是正数,则表示从现在开始,即将过期的秒数
        simpleCookie.setMaxAge(2592000); //30天

        return simpleCookie;
    }

    /**
     * cookie对象的rememberMe属性模板：
     * 默认属性名为rememberMe, 由于属性名rememberMe容易与SERVLET容器名冲突,所以这里需要重新定义为datacenter-rememberMe
     */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称, 对应前端的Request-Headers的Cookie:rememberMe=xxx(或<checkbox name='rememberMe' />控件)
        SimpleCookie simpleCookie = new SimpleCookie("dc-rememberMe");

        //设置Response-Header的Set-Cookie属性中的Httponly
        //httponly属性设为true会增加对xss防护的安全系数(即只能通过http访问,不能javascript无法访问,以防止xss读取cookie)
        simpleCookie.setHttpOnly(true);

        //设置Response-Header的Set-Cookie属性中的Path(相对是根域名的路径)
        simpleCookie.setPath("/");

        //设置Response-Header的Set-Cookie属性中的Max-Age(有效时间,单位为秒)
        //maxAge=-1表示浏览器关闭时失效此Cookie; 如果是正数,则表示从现在开始,即将过期的秒数
        simpleCookie.setMaxAge(2592000); //30天

        return simpleCookie;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


//    /**
//     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
//        defaultAAP.setProxyTargetClass(true);
//        // defaultAAP.setUsePrefix(true);
//        return defaultAAP;
//    }
//
//    /**
//     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
//     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
//        aASA.setSecurityManager(securityManager());
//        return aASA;
//    }
//
//    /**
//     * 限制同一账号登录同时登录人数控制
//     *
//     * @return
//     */
//    @Bean
//    public KickoutSessionControlFilter kickoutSessionControlFilter() {
//        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//        kickoutSessionControlFilter.setCacheManager(cacheManager());
//        kickoutSessionControlFilter.setSessionManager(sessionManager());
//        kickoutSessionControlFilter.setKickoutAfter(false);
//        kickoutSessionControlFilter.setMaxSession(1);
//        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
//        return kickoutSessionControlFilter;
//    }
}

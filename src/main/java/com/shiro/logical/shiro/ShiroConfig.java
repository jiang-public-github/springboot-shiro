package com.shiro.logical.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @Author jianglanglang
 * @Date 2020/12/17 16:01
 * @Description shiro配置类
 */
@Configuration
public class ShiroConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注入 Shiro 过滤器
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        // 所有的认证和授权判断都是由这个 bean 生成的 Filter 对象来完成的
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置Shiro过滤器
        Map<String, String> filterMap  = new LinkedHashMap<>();
        // authc: 所有url都必须认证通过才可以访问; anon: 所有url都都可以匿名访问;
        // user: 如果使用rememberMe的功能可以直接访问; perms: 该资源必须得到资源访问权限才可以使用; role: 该资源必须得到角色授权才可以使用
        filterMap.put("/swagger**/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/doc.html", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/a/sys/user/login", "anon");
        filterMap.put("/static/**", "anon");
        // 所有url都必须认证通过才可以访问
        filterMap.put("/**", "authc");
        // 配置shiro默认的登录地址，前后端分离的模式中，跳转由前端控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/a/sys/url/unauth");
        // 登录成功后要跳转的链接, 此项目是前后端分离，故此行注释掉。
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面 ,此处不生效,一般自定义异常
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 注入安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        // SystemRealm 注入到 DefaultWebSecurityManager bean 中，完成注册。
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(SystemRealm());
        //注入缓存对象
        defaultSecurityManager.setCacheManager(ehCacheManager());
        return defaultSecurityManager;
    }

    /**
     * 注入自定义的 Realm
     * @return
     */
    @Bean
    public SystemRealm SystemRealm(){
        // 自定义过滤器SystemRealm， 业务逻辑全部定义在这个 bean 中
        SystemRealm systemRealm = new SystemRealm();
        // 加入凭证匹配器
        systemRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return systemRealm;
    }

    /**
     * 凭证匹配器
     * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了，所以我们需要修改下doGetAuthenticationInfo中的代码
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro aop注解支持.  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        SessionManager mySessionManager = new SessionManager();
        // 设置session过期时间3600s
        mySessionManager.setGlobalSessionTimeout(3600000L);
        return mySessionManager;
    }

    /**
     * Shiro生命周期处理器:
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 配置ehcache
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }
}
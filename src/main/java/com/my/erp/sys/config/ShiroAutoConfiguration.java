package com.my.erp.sys.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.my.erp.sys.realm.UserRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass()
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroAutoConfiguration {
    //没有的设置值的在yml中读取
    private static final String SHIRO_FILTER = "shiroFilter";//变量
    private String hashAlgorithmName = "md5";//加密方式
    private int hashIteration = 2;//散列次数
    private String loginUrl = "/index.html";//默认登录页
    private String[] annoUrls;//放行路径
    private String loginUrls;//登出路径
    private String[] authcUrls;//拦截路径

    /**
     *声明凭证匹配器
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(hashIteration);
        return hashedCredentialsMatcher;
    }
    /**
     *声明UserRealm
     */
    @Bean("userRealm")
    public UserRealm userRealm(CredentialsMatcher credentialsMatcher){
       UserRealm userRealm = new UserRealm();
       userRealm.setCredentialsMatcher(credentialsMatcher);
       return userRealm;
    }
    /**
     * 配置SecurityManager
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager SecurityManager =new DefaultWebSecurityManager();
        //注入UserReam
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }
    /**
     * 配置shiro过滤器
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean =  new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //设置未登录时要跳转的页面
        factoryBean.setLoginUrl(loginUrl);


        Map<String,String> filterMap = new LinkedHashMap<>();
        //设置放行的路径
        if(annoUrls!=null && annoUrls.length>0){
            for (String annoUrl : annoUrls) {
                filterMap.put(annoUrl,"anon");
            }
        }
        //设置等出路径
        if(null!=loginUrl){
            filterMap.put(loginUrl,"logout");
        }

        //设置拦截路径
        if(authcUrls!=null&&authcUrls.length>0){
            for (String authcUrl : authcUrls) {
                filterMap.put(authcUrl,"authc");
            }
        }
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }
    /**
     * 注册过滤器
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy(){
        FilterRegistrationBean<DelegatingFilterProxy> filterProxyFilterRegistBean =
                new FilterRegistrationBean<>();
        //创建一个代理对象
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        //设置生命周期
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName(SHIRO_FILTER);
        filterProxyFilterRegistBean.setFilter(proxy);
        return filterProxyFilterRegistBean;
    }
    /* 加入注解的使用，不加入这个注解不生效--开始 */
    /**
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /* 加入注解的使用，不加入这个注解不生效--结束 */
    
    /**
     * 这里是为了在html页面能应用shiro标签
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}

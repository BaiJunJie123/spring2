package com.ln.configUtils;

import com.ln.utils.Myrealm;
import com.ln.utils.RedisCache;
import com.ln.utils.RedisCacheManager;
import com.ln.utils.RedisSessionDAO;
import com.netflix.ribbon.proxy.annotation.Http;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.net.idn.Punycode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
@Configuration
public class ShiroConfig {
    @Autowired
    private Myrealm myrealm;
    @Autowired
    private  RedisCacheManager redisCacheManager;
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiro = new ShiroFilterFactoryBean();
        shiro.setSecurityManager(defaultWebSecurityManager);
        shiro.setLoginUrl("/login");
        shiro.setSuccessUrl("/sucess");
        Map<String, Filter> map = new HashMap<>();
        CasFilter casFilter = new CasFilter();
        casFilter.setFailureUrl("/message.jsp"); //配置验证错误时的失败页面 /main 为系统登录页面
        map.put("casFilter",casFilter );
        shiro.setFilters(map);
        //过滤器链，请求url对应的过滤器
        Map<String, String> maps = new HashMap<>();
        //maps.put("/findList", "roles[admin]"); //authc,roles[admin]
        maps.put("/yidenglu", "user");
        maps.put("/**", "anon");
        shiro.setFilterChainDefinitionMap(maps);
        return  shiro;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CasSubjectFactory casSubjectFactory,DefaultWebSessionManager defaultWebSessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSessionMode("Http");
        manager.setSubjectFactory(casSubjectFactory);
        manager.setCacheManager(redisCacheManager);
        manager.setSessionManager(defaultWebSessionManager);
        manager.setAuthenticator(authenticator(myrealm));
        manager.setAuthorizer(authorizer(myrealm));
        manager.setRealm(myrealm);
        return  manager;


    }
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(RedisSessionDAO redisSessionDAO,SimpleCookie simpleCookie){
        DefaultWebSessionManager de = new DefaultWebSessionManager();
        de.setGlobalSessionTimeout(1800000); //设置全局会话超时时间，默认30分钟(1800000)
        de.setDeleteInvalidSessions(true); //是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true
        de.setSessionValidationInterval(1800000); //会话验证器调度时间
        de.setSessionDAO(redisSessionDAO);
        de.setSessionIdCookie(simpleCookie);
        //定时检查失效的session
        de.setSessionValidationSchedulerEnabled(true);
        return de;


    }
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie sim = new SimpleCookie();
        //cookie的name,对应的默认是 JSESSIONID
        sim.setName("SHAREJSESSIONID");
        //jsessionId的path为 / 用于多个系统共享jsessionId
        sim.setPath("/");
        sim.setHttpOnly(true);
        return sim;
    }
    //单点登陆
    @Bean
    public CasSubjectFactory casSubjectFactory(){
        CasSubjectFactory cas = new CasSubjectFactory();
        return  cas;
    }
    //开启注解功能
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public Authorizer authorizer(Myrealm myrealm){
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        Collection<Realm> crealms = new ArrayList<>();
        crealms.add(myrealm);
        authorizer.setRealms(crealms);
        return authorizer;
    }

    @Bean
    public Authenticator authenticator(Myrealm myrealm){
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        Collection<Realm> crealms = new ArrayList<>();
        crealms.add(myrealm);
        authenticator.setRealms(crealms);
        return authenticator;
    }
}

package cn.ntshare.laboratory.config;

import cn.ntshare.laboratory.realm.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        // 开启缓存
        userRealm.setCachingEnabled(true);
        // 开启身份验证缓存，即缓存AuthenticationInfo信息
        userRealm.setAuthenticationCachingEnabled(true);
        // 设置身份缓存名称前缀
        userRealm.setAuthenticationCacheName("authenticationCache");
        // 开启授权缓存
        userRealm.setAuthorizationCachingEnabled(true);
        // 这是权限缓存名称前缀
        userRealm.setAuthorizationCacheName("authorizationCache");

        return userRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        // 使用Redis作为缓存
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 路径过滤规则
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 登录路由
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功的路由
        shiroFilterFactoryBean.setSuccessUrl("/");
        Map<String, String> map = new LinkedHashMap<>();
        // 有先后顺序
        map.put("/login", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解模式，可以在Controller中的方法上添加注解
     * 如：@
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SessionManager sessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        if (redisPassword != null && !("").equals(redisPassword)) {
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        // 设置缓存名前缀
        redisSessionDAO.setKeyPrefix("shiro:session:");
        return redisSessionDAO;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 选择属性字段作为缓存标识，这里选择account字段
        redisCacheManager.setPrincipalIdFieldName("account");
        // 设置信息缓存时间
        redisCacheManager.setExpire(86400);
        return redisCacheManager;
    }
}

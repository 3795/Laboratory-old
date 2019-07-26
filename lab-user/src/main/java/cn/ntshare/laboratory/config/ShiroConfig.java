package cn.ntshare.laboratory.config;

import cn.ntshare.laboratory.filter.JWTFilter;
import cn.ntshare.laboratory.realm.JWTRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
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

import javax.servlet.Filter;
import java.util.HashMap;
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
    public JWTRealm jwtRealm() {
        JWTRealm jwtRealm = new JWTRealm();
        jwtRealm.setCachingEnabled(true);
        // 开启身份认证缓存
        jwtRealm.setAuthenticationCachingEnabled(true);
        // 设置身份缓存名称前缀
        jwtRealm.setAuthenticationCacheName("authenticationCache");
        // 开启授权缓存
        jwtRealm.setAuthorizationCachingEnabled(true);
        // 这是权限缓存名称前缀
        jwtRealm.setAuthorizationCacheName("authorizationCache");
        return jwtRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm());

        // 关闭Shiro自带的Session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        // 使用Redis作为缓存
        securityManager.setCacheManager(redisCacheManager());

        return securityManager;
    }

    /**
     * 路径过滤规则
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加JWT过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/login");       // 登录路由
        shiroFilterFactoryBean.setSuccessUrl("/");          // 登录成功的路由
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login", "anon");      // 登录路由匿名访问
        map.put("/401", "anon");
        map.put("/**", "jwt");      // 其他的路由请求走jwt过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解模式，可以在Controller中的方法上添加注解
     * 如：@
     *
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

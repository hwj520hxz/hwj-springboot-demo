package com.hwj.demo.user.authorize.integrated;

import com.hwj.demo.user.authorize.integrated.shiro.JWTFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Configuration
/*该注解可以将配置文件的属性加载进来 prefix参数表示前缀，如spring 就可以将spring.XXX的各个子级属性加载进来*/
/*@ConfigurationProperties(prefix = "authentication")*/
public class ShiroFilterConfiguration {

    // 该地址就是请求未经授权的接口后返回的页面，可以设置为首页
    private String loginUrl = "www.baidu.com";

    private List<String> exclude;

    public List<String> getExclude() {
        return exclude;
    }

    public void setExclude(List<String> exclude) {
        this.exclude = exclude;
    }

    /**
     * authc:表单拦截器,需要认证即可访问
     * anon: 匿名拦截器,无需认证即可访问,一般用于静态资源过滤
     * user: 用户拦截器,点击“记住我”功能可访问
     * perms: 拥有权限才可以访问
     * role: 角色授权拦截器,拥有某个角色权限才能访问
     * authcBasic:Basic HTTP身份验证拦截器
     * logout:退出拦截器
     * Filter工厂，设置对应的过滤条件和跳转条件
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        // shiroFilter的过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter(loginUrl));
        shiroFilterFactoryBean.setFilters(filterMap);
        // 安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterRuleMap = new HashMap<>();
        // 用户的请求通过我们自己的JWT Filter
        //filterRuleMap.put("/hwj/user/**", "jwt");
        // 权限动态注入
        /*if(CollectionUtils.isNotEmpty(exclude)){
            exclude.forEach(url -> filterRuleMap.put(url, "anon"));
        }*/
        //shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }
}

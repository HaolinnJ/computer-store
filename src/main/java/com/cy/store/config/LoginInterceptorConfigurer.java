package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**处理器拦截器的注册*/
@Configuration // 加载当前的拦截器
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    /**配置拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单，存放在一个list集合里
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        //拦截器注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**") // 要拦截的url是什么，"/**" 代表所有url
                .excludePathPatterns(patterns);
    }
}

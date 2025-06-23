package com.cy.store.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有则重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return 如果返回值为true则表示放行当前请求，如果返回值为false则表示拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 用HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null){ // 说明用户没有登录过系统，则重定向到login.html页面
            response.sendRedirect("/web/login.html"); //不能使用相对路径
            // 结束后续的调用
            return false;
        }
        // 请求放行
        return true;
    }
}
//在SpringMVC的请求处理流程中，在调用控制器之前会先依次调用所有注册的拦截器的prehandle()方法
//如果任何一个拦截器的preHandle()返回false, SpringMVC会中断整个请求流程，后续的拦截器/控制器方法都不会执行

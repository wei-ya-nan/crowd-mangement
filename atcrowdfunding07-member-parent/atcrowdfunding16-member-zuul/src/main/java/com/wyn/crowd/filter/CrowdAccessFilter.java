package com.wyn.crowd.filter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyn.crowd.constant.AccessPassResources;
import com.wyn.crowd.constant.CrowdConstant;
import org.springframework.stereotype.Component;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/7
 */
@Component
public class CrowdAccessFilter extends ZuulFilter{
    public String filterType() {
        // 这里返回“pre” 意思是在目标微服务前执行过滤
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        // 获取RequestContext对象
        RequestContext context = RequestContext.getCurrentContext();
        /* 2.通过 RequestContext 对象获取当前请求对象（ 框架底层是借助 ThreadLocal 从当前
        线程上获取事先绑定的 Request 对象）*/
        HttpServletRequest request = context.getRequest();

        // 获取servletPath的值
        String servletPath = request.getServletPath();

        // 4.根据 servletPath 判断当前请求是否对应可以直接放行的特定功能
        boolean b = AccessPassResources.judgeCurrentServletPathWetherStaticResource(servletPath);
        if(b){
            // 5.如果当前请求是可以直接放行的特定功能请求则返回 false 放行
            return false;
        }

        // 5.判断当前请求是否为静态资源
        // 工具方法返回true：说明当前请求是静态资源请求，取反为false表示放行不做登录检查
        // 工具方法返回false：说明当前请求不是可以放行的特定请求也不是静态资源，取反为true表示需要做登录检查
        return !AccessPassResources.judgeCurrentServletPathWetherStaticResource(servletPath);

    }

    public Object run() throws ZuulException {
        // 获取当前的请求对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        // 获取当前的session对象
        HttpSession session = request.getSession();

        // 从session中获取user对象
        Object loginMember = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        // 判断是否为空
        if(loginMember == null){
            // 获取response对象
            HttpServletResponse response = context.getResponse();
            // 提示请登录在访问
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
            // 重定向到登录页面
            try {
                response.sendRedirect("/auth/member/to/login/page");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

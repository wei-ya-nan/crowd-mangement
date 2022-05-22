package com.wyn.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@Configuration
public class CrowdMvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问的地址
        String path = "/auth/member/to/reg/page.html";

        // 目标视图的名称
        String viewName ="member-reg";

        registry.addViewController(path).setViewName(viewName);
        registry.addViewController("/auth/member/to/login/page").setViewName("member-login");
        registry.addViewController("/auth/member/to/center/page").setViewName("member-center");
        registry.addViewController("/member/my/crowd").setViewName("member-crowd");
        // TODO: 2022/5/8 尝试在member-crowd的点击事件的超链接前面加zuul的网关名字
    }
}

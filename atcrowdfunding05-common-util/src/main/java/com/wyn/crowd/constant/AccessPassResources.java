package com.wyn.crowd.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/7
 */
public class AccessPassResources {
    public static final Set<String> PASS_RES_SET = new HashSet<String>();
    static {
        PASS_RES_SET.add("/");
        PASS_RES_SET.add("/auth/member/to/reg/page.html");
        PASS_RES_SET.add("/auth/member/to/login/page");
        PASS_RES_SET.add("/auth/member/logout");
        PASS_RES_SET.add("/auth/member/do/login");
        PASS_RES_SET.add("/auth/do/member/register");
        PASS_RES_SET.add("/auth/member/send/short/message.json");
        PASS_RES_SET.add("/app");
    }

    public static final Set<String> STATIC_RES_SET = new HashSet<String>();
    static{
        STATIC_RES_SET.add("bootstrap");
        STATIC_RES_SET.add("css");
        STATIC_RES_SET.add("fonts");
        STATIC_RES_SET.add("img");
        STATIC_RES_SET.add("jquery");
        STATIC_RES_SET.add("layer");
        STATIC_RES_SET.add("script");
        STATIC_RES_SET.add("ztree");
//        STATIC_RES_SET.add("app");
    }

    /** 用于判断某个ServletPath值是否对应一个静态资源
     *
     * @param servletPath
     * @return true 静态资源，false 非静态资源
     *
     */
    public static boolean judgeCurrentServletPathWetherStaticResource(String servletPath) {

        // 排除字符串无效的情况
        if(servletPath == null || servletPath.length() == 0){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        // 根据"/"拆分servletPath
        String[] spilt = servletPath.split("/");
        // 3.考虑到第一个斜杠左边经过拆分后得到一个空字符串是数组的第一个元素，所以需要使用下标1取第二个元素
        if(spilt.length<1){
            return false;
        }
        String firstLevelPath = spilt[1];

        // 4.判断是否在集合中
        return STATIC_RES_SET.contains(firstLevelPath);

    }
}

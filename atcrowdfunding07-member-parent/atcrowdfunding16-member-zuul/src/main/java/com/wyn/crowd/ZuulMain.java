package com.wyn.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulMain {
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain.class,args);
    }
}

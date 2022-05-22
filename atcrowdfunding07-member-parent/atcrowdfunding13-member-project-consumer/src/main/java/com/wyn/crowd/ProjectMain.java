package com.wyn.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/7
 */
// 启用Feign客户端功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ProjectMain {
    public static void main(String[] args) {
        SpringApplication.run(ProjectMain.class,args);
    }
}

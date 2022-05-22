package com.wyn.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/4/4
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaCrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCrowdMainClass.class, args);
    }
}

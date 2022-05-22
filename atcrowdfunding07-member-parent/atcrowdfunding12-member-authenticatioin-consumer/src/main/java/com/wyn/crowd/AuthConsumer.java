package com.wyn.crowd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthConsumer {
    public static void main(String[] args) {
        SpringApplication.run(AuthConsumer.class,args);
    }
}

package com.wyn.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/4/4
 */
// 扫描MyBatis的mapper接口所在的包
@MapperScan("com.wyn.crowd.mapper")
@SpringBootApplication
public class MySqlProviderCrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(MySqlProviderCrowdMainClass.class, args);
    }
}

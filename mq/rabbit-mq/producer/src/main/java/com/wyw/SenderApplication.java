package com.wyw;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/21 11:21
 */
@SpringBootApplication()
public class SenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class);
    }
}

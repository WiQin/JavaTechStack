package com.wyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/21 11:36
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RecverApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecverApplication.class);
    }
}

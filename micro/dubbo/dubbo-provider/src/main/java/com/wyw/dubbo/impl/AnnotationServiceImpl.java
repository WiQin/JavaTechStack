package com.wyw.dubbo.impl;

import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.wyw.micro.api.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于注解配置
 */
@Service // 用来配置dubbo的服务提供方
public class AnnotationServiceImpl implements UserService {


    public String getUserInfo(Long userId) {
        return String.format("user:%s",userId);
    }
}

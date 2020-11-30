package com.wyw.dubbo.configuration;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyw.micro.api.UserService;
import org.springframework.stereotype.Component;

@Component("annotationConsumer")
public class ConsumerConfiguration {

    @Reference
    private UserService userService;

    public String getUserInfo() {
        return userService.getUserInfo(111L);
    }
}

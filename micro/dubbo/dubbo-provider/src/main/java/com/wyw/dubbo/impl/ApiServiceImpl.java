package com.wyw.dubbo.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.wyw.micro.api.UserService;

/**
 * 使用api方式声明dubo服务
 */
public class ApiServiceImpl {
    public static void main(String[] args) {
        //服务的实现者
        XmlServiceImpl xmlService = new XmlServiceImpl();

        //当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-provider");
        applicationConfig.setOwner("wangyw");

        //连接注册中心
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper:localhost:2181");
        registryConfig.setUsername("root");
        registryConfig.setPassword("root");

        //服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20080);
        protocolConfig.setThreads(200);

        //服务提供者暴露服务配置
        ServiceConfig<UserService> service = new ServiceConfig<UserService>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(UserService.class);
        service.setRef(xmlService);
        service.setVersion("1.0.0");

        //暴露及注册服务
        service.export();
    }
}

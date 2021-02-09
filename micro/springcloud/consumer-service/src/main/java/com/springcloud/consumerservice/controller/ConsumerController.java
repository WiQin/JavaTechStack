package com.springcloud.consumerservice.controller;

import com.springcloud.consumerservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping
public class ConsumerController {

    @Autowired
    RestTemplate template;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) {
        //根据服务id获取实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance serviceInstance = instances.get(0);
        //从实例中取出ip和端口
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" +id;
//        String url = "http://localhost:8081/user/" + id;
        User user = template.getForObject(url, User.class);
        return user;
    }
}

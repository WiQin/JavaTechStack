package com.wyw.dubbo.impl;

import com.wyw.micro.api.UserService;

public class XmlServiceImpl implements UserService {

    public String getUserInfo(Long userId) {
        return String.format("user:%s",userId);
    }
}

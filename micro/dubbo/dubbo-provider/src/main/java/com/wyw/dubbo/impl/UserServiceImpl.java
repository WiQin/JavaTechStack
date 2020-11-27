package com.wyw.dubbo.impl;

import com.wyw.micro.api.UserService;

public class UserServiceImpl implements UserService {

    public String getUserInfo(Long userId) {
        return String.format("user:%s",userId);
    }
}

package com.springcloud.userservice.service;

import com.springcloud.userservice.entity.User;
import com.springcloud.userservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/29
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }


}

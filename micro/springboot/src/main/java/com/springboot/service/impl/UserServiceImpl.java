package com.springboot.service.impl;

import com.springboot.dao.UserDao;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/09
 */
@Service
public class UserServiceImpl {

    @Autowired
    UserDao userDao;

    @Transactional
    public void addUser(User user) {
        userDao.insertUser(user);
    }
}

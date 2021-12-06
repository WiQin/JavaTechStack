package com.springboot.controller;

import com.springboot.conf.JdbcConfig;
import com.springboot.pojo.User;
import com.springboot.property.JdbcProperties;
import com.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/17
 */
@RestController
public class DemoController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("do")
    public String doSth() {
        User user = new User();
        user.setUsername("username8");
        user.setPassword("3322r2r322");
        user.setGender(1);
        user.setName("test");
        user.setAge(12);
        userService.addUser(user);
        return "success";
    }


}

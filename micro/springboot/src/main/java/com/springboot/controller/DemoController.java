package com.springboot.controller;

import com.springboot.conf.JdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/17
 */
@RestController
public class DemoController {

    @Autowired
    JdbcConfig jdbcConfig;

    @GetMapping("do")
    public String doSth() {
        return "spring boot"+jdbcConfig;
    }


}

package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/09
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String doWork() {
        return "Spring Boot";
    }
}

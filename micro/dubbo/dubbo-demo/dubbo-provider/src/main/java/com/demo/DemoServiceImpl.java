package com.demo;

import org.springframework.stereotype.Service;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/13
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService{

    public String doSomething(String param) {
        return param;
    }
}

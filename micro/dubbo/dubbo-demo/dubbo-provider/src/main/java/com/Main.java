package com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/13
 */
public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("init--");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
        context.start();
        System.out.println("provider start");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

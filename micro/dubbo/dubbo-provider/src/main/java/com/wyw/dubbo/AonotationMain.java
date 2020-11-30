package com.wyw.dubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class AonotationMain {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.start();
        System.out.println("dubbo-annotation-provider start");
        System.in.read();
    }
}

package com.java.advance.designpattern.obsever.service.impl;

import com.java.advance.designpattern.obsever.service.DisplayElement;
import com.java.advance.designpattern.obsever.service.Obsever;
import com.java.advance.designpattern.obsever.service.Subject;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/7 17:21
 */
public class CurrentConditionsDisplay implements Obsever, DisplayElement {

    private Double temperature;
    private Double humidity;
    private Double pressure;

    /**
     * 构造器需要对应的主题对象来把自身注册到该主题上去
     * @param weatherSubject
     */
    public CurrentConditionsDisplay(Subject weatherSubject) {
        weatherSubject.registerObsever(this);
    }

    /**
     * 展示就简单的打印吧
     */
    @Override
    public void display() {
        System.out.println("CurrentConditions-temperature:"+temperature+"humidity:"+humidity+"pressure:"+pressure);
    }

    /**
     * 通知观察者后调用update()方法
     * 更新完后直接展示，调用display()方法
     * @param temperature
     * @param humidity
     * @param pressure
     */
    @Override
    public void update(Double temperature, Double humidity, Double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}

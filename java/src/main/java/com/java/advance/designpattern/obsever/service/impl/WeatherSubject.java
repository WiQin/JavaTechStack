package com.java.advance.designpattern.obsever.service.impl;

import com.java.advance.designpattern.obsever.service.Obsever;
import com.java.advance.designpattern.obsever.service.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/7 17:11
 */
public class WeatherSubject implements Subject {
    /**
     * 定义依赖该主题的所有观察者列表
     */
    private List<Obsever> obseverList;
    //气象数据  温度，湿度，气压
    private Double temperature;
    private Double humidity;
    private Double pressure;

    public WeatherSubject() {
        //初始化观察者列表
        this.obseverList = new ArrayList<>();
    }

    /**
     * 注册观察者信息
     * @param obsever
     */
    @Override
    public void registerObsever(Obsever obsever) {
        this.obseverList.add(obsever);
    }

    /**
     * 移除观察者信息
     * @param obsever
     */
    @Override
    public void removeObsever(Obsever obsever) {
        this.obseverList.remove(obsever);
    }

    /**
     * 通知贯观察者数据更新
     * ps:我只负责通知，具体你拿到数据要干什么就与我无关了
     */
    @Override
    public void notifyObsever() {
        obseverList.forEach(obsever -> obsever.update (temperature,humidity,pressure));
    }

    /**
     * 对外暴露方法
     */
    public void measurementsChanged() {
        notifyObsever();
    }

    /**
     * 模拟获取气象信息方法
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(Double temperature,Double humidity,Double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

package com.java.advance.designpattern.obsever.built_in;

import java.util.Observable;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/7 21:11
 */
public class WeatherObservable extends Observable {
    //气象数据  温度，湿度，气压
    private Double temperature;
    private Double humidity;
    private Double pressure;

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
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

    //当使用”拉“的方式更新数据时，需要用到get方法
    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }
}

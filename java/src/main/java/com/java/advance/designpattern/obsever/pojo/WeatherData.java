package com.java.advance.designpattern.obsever.pojo;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/6 16:03
 */
public class WeatherData {

    /**下面方法获取最新的气象数据*/
    public void getTemperature() {}

    public void getHumidity() {}

    public void getPressure() {}

    /**气象数据被更新时，调用此方法*/
    public void measurementsChanged() {}
}

package com.java.advance.designpattern.obsever;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/6 16:03
 */
public class WeatherData {

    /**下面方法获取最新的气象数据，具体实现我们不关心*/
    public Double getTemperature() {return 0.0D;}

    public Double getHumidity() {return 0.0D;}

    public Double getPressure() {return 0.0D;}

    /**气象数据被更新时，调用此方法*/
    public void measurementsChanged() {
        Double temperature = getTemperature();
        Double humidity = getHumidity();
        Double pressure = getPressure();

        //调用相应方法更新当前状况,天气统计，天气预报  很low
        currcuentConditionsDisplay.update(temperature,humidity,pressure);
        statisticsDisplay.update(temperature,humidity,pressure);
        forecastDisplay.update(temperature,humidity,pressure);
    }
}

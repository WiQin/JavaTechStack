package com.java.advance.designpattern.obsever.service.impl;

import org.junit.Test;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/7 17:37
 */
public class WeatherSubjectTest {
    @Test
    public void testWeather() {
        WeatherSubject weatherSubject = new WeatherSubject();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherSubject);
        weatherSubject.setMeasurements(28.5D,13.2D,77.8D);
    }
}
package com.java.advance.designpattern.obsever.built_in;

import com.java.advance.designpattern.obsever.service.impl.CurrentConditionsDisplay;
import com.java.advance.designpattern.obsever.service.impl.WeatherSubject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/7 21:26
 */
public class WeatherObservableTest {
    @Test
    public void testWeather() {
        WeatherObservable weatherObservable = new WeatherObservable();
        CurrentConditionsObserver currentConditionsDisplay = new CurrentConditionsObserver(weatherObservable);
        weatherObservable.setMeasurements(28.5D,13.2D,77.8D);
    }

}
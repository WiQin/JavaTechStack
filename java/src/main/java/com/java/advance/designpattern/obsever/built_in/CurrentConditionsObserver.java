package com.java.advance.designpattern.obsever.built_in;

import com.java.advance.designpattern.obsever.service.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/8/7 21:14
 */
public class CurrentConditionsObserver implements Observer, DisplayElement {
    private Double temperature;
    private Double humidity;
    private Double pressure;

    public CurrentConditionsObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherObservable) {
            WeatherObservable weatherObservable = (WeatherObservable) o;
            this.temperature = weatherObservable.getTemperature();
            this.humidity = weatherObservable.getHumidity();
            this.pressure = weatherObservable.getPressure();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("CurrentConditions-temperature:"+temperature+"humidity:"+humidity+"pressure:"+pressure);
    }
}

package com.java.advance.designpattern.obsever.service;

/**
 * @Descroption:主题，为观察者提供相应注册，取消注册服务，并在数据发生变化时通知到观察者
 * @Author: wangyw
 * @Date: 2021/8/7 16:56
 */
public interface Subject {

    //注册
    void registerObsever(Obsever obsever);
    //注销
    void removeObsever(Obsever obsever);
    //通知
    void notifyObsever();

}

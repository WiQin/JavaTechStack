package com.java.advance.designpattern.obsever.service;

/**
 * @Descroption: 观察者，在主题内容发生变化时更新相应信息
 * @Author: wangyw
 * @Date: 2021/8/7 16:56
 */
public interface Obsever {
    void update(Double temperature, Double humidity, Double pressure);
}

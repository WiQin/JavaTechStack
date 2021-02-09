package com.springboot.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 新的属性注入方式，支持各种java基本数据类型及复杂类型的注入。
 *
 * @ConfigurationProperties 声明当前类为属性读取类
 *
 * prefix="jdbc"读取属性文件中，前缀为jdbc的值。
 *
 * 在类上定义各个属性，名称必须与属性文件中jdbc.后面部分一致
 *
 * 没有指定属性文件的地址，所以我们需要把jdbc.properties名称改为application.properties，这是SpringBoot默认读取的属性文件名
 */
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.springboot.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.property.JdbcProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


/**
 * Jdbc配置类
 *
 * 1.使用@Value为属性注入值
 * @Configuration  声明该类为一个配置类
 * @PropertySource 指定配置文件的路径
 * @Value 为属性注入值
 * @Bean  将dataSource()方法声明为一个注册Bean的方法，Spring会自动调用该方法，将方法的返回值加入Spring容器中。
 *
 * 2.使用@EnableConfigurationProperties
 * @EnableConfigurationProperties(JdbcProperties.class) 来声明要使用JdbcProperties这个类的对象
 *
 * 3.如果一段属性只有一个Bean需要使用，我们无需将其注入到一个类（JdbcProperties）中。而是直接在需要的地方声明即可
 * @author wyw
 * @date 2020/07/21
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {

//    @Value("${jdbc.url}")
//    String url;
//    @Value("${jdbc.driverClassName}")
//    String driverClassName;
//    @Value("${jdbc.username}")
//    String username;
//    @Value("${jdbc.password}")
//    String password;
//
//    @Bean
//    public DataSource dataSource1() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }

    @Bean
    public DataSource dataSource2(JdbcProperties jdbc) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbc.getUrl());
        dataSource.setDriverClassName(jdbc.getDriverClassName());
        dataSource.setUsername(jdbc.getUsername());
        dataSource.setPassword(jdbc.getPassword());
        return dataSource;
    }

//    @Bean
//    // 声明要注入的属性前缀，SpringBoot会自动把相关属性通过set方法注入到DataSource中
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource3() {
//        DruidDataSource dataSource = new DruidDataSource();
//        return dataSource;
//    }
}

# MyBatis <!-- {docsify-ignore} -->
## 核心组件
核心组件：
1.SqlSessionFactoryBuilder(构造器)，根据配置或代码生成SqlSessionFactory  
2.SqlSessionFactory(工厂接口)，用于生成SqlSession
3.SqlSession(会话)：一个既可以发送SQL执行返回结果，也可以获取Mapper的接口  
4.SqlMapper(映射器)：有一个java接口和XML文件构成，需要给出对应的SQL和映射规则，负责发送SQL去执行并返回结果  

## 配置
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration> <!--配置-->
    <properties/> <!--属性-->
    <settings/> <!--设置-->
    <typeAliases/> <!--类型命名-->
    <typeHandlers/> <!--类型处理器-->
    <objectFactory/> <!--对象工厂-->
    <plugins/> <!--插件-->
    <environments> <!--配置环境-->
        <environment> <!--环境变量-->
            <transactonManager/> <!--事务管理器-->
            <dataSource/> <!--数据源-->
        </environment>
    </environments> 
    <databaseidProvider/> <!--数据库厂商标识-->
    <mappers/> <!--映射器-->
</configuration>
~~~
1.properties  为系统配置运行参数
    1.1 通过property子元素配置 
   ~~~xml
    <property name = "" value = "" />
   ~~~  
    1.2 使用properties文件
   ~~~xml
    <property resources = "jdbc.properties" />
   ~~~
    1.3 通过程序传递参数 
2.settings 常用的有自动映射，驼峰命名，级联规格，是否启动缓存等
   ~~~xml
    <settings>
    <!--驼峰命名-->
    <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
   ~~~
3.typeAliases 别名，简化类全限定名
   ~~~xml
    <typeAliases>
            <!--别名-->
            <typeAlias alias="role" type="com.wyw.mybatis.entity.Role"/>
    </typeAliases>
   ~~~
3.typeHandler 类型转换器   
4.ObjectFactory 对象工厂  
5.环境配置  
6.映射器  

## 映射器
接口+xml文件组成，通过简易的映射规则实现sql语句和java对象的映射  
配置元素：select update delete insert sql resultMap cache cache-ref  
### select  
配置： id parameterType resultType resultMap flushCache useCache  timeout  
多参传递：使用@Param注解（此时不需指定parameterType）  
resultMap  

### insert 
配置：id parameterType flushCache timeout(超时时间) useGeneratedKeys（是否获取自动生成的主键） keyProperty  keyColumn  
useGeneratedKeys:插头数据后是否将数据库自动生成的主键id返回给java对象，默认false

### update&delete
### sql
定义一条sql的一部分，后面其他sql引用

### resultMap

### 级联


    

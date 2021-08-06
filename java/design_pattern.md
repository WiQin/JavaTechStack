# 设计模式

## 策略模式（Strategy Pattern）
>定义：
>在策略模式中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。  
>我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的context对象。策略对象改变context对象的执行算法。

案例：
- 现有一个父类鸭子[AbstractDuck]，有公共的swim()和quack()方法，同时也有抽象方法display()交由子类实现，现在继承自他的子类就有了两个父类方法和一个待实现的抽象方法
- 问题：现在如果需要鸭子类实现fly()的功能，该怎么实现呢？
- 思路：1. 父类加一个fly()方法，如果子类有不同实现则重写 --  不是所有鸭子类都有fly()方法，写在父类里不够合理  
  2. 定义一个fly接口，只有会fly的鸭子才实现，而且每个子类的fly()动作可能还不一致 -- 无法解决代码复用的问题，每次需要修改逻辑都要找到接口的实现类去检查，要是需要重写的方法多了的话，呵呵。。
  3. 分离程序中经常变化的部分，定义成相应的接口，然后交由主类去调用。怎么去实现呢？
      - 首先在父类[SuperDuck]中定义两个实例变量[FlyBehavior,QuackBehavior]，声明为接口类型，每个子类可以动态地设置这些变量以在运行时引用正确的行为类型（接口的不同实现类）
      - 定义相应的[performFly()]方法和[performQuack()]方法，实现的话直接调用接口变量相应的方法
      - 父类定义[setFlyBehavior()]方法,[setQuackBehavior()]方法，这样可以随时给其子类添加相应的方法和行为

~~~java 
public abstract class AbstractDuck {
    //抽象方法，交由子类去实现
    protected abstract void display();
    protected void swim() {
        System.out.println("hua la la");
    }
    /*-------下面的方法不是所有子类实现都一样，可以想办法优化-------------*/
    protected void quack() {
        System.out.println("gua gua");
    }
    protected void fly() {
        //emm..好像有什么问题，不是所有的鸭子都会飞
        System.out.println("I believe I can fly");
    }
}
~~~

~~~java
public abstract class SuperDuck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;
    
    //抽象方法，交由子类去实现
    protected abstract void display();
    protected void swim() {
        System.out.println("hua la la");
    }
    public void performFly() {
        flyBehavior.fly();
    }
    public void performQuack() {
        quackBehavior.quack();
    }
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}

public interface FlyBehavior {
    void fly();
}

public interface QuackBehavior {
    void quack();
}

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I believe I can fly");
    }
}

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("No way,you can't fly");
    }
}

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("gua gua");
    }
}

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("mute");
    }
}

public class MallarDuck extends SuperDuck {
    public MallarDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Spueak();
    }

    @Override
    protected void display() {
    }
}

public class MiniDuckSimular {
    public static void main(String[] args) {
        SuperDuck mallarDuck = new MallarDuck();

        mallarDuck.performFly();
        mallarDuck.performQuack();
    }
}
~~~

>我们学到了哪些设计原则：
>1. 找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起
>>   * 如果每次需求一来，就会使某方面代码发生变化，那么就需要抽离了
>>   * 当涉及"维护"时，为了服用而使用继承可能结局并不完美
>2. 针对接口编程，而不是针对实现编程  
>3. 多用组合，少用继承（IS-A 比 HAS-A 更好）

## 观察者模式（Obsever Pattern）

案例：
- 建立一个应用，通过已有的WeatherData对象取得数据，并且更新到相应布告板:当前状态，气象统计和天气预报
- 当WeatherData获取到新的数据时能实时更新到布告板
- 可扩展性，支持添加新的布告板

~~~java
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
~~~

## 装饰模式

## 工厂模式

## 单例模式

## 命令模式

## 适配器模式

## 外观模式

## 模板方法模式

## 迭代器模式

## 组合模式

## 状态模式

## 代理模式

## 复合模式


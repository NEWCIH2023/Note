
# Spring面试

### 用了什么设计模式？

+ 工厂模式
    BeanFactory就是简单工厂模式的体现，用来创建对象的实例
+ 单例模式
    Bean默认为单例
+ 代理模式
    Spring的AOP功能用到了JDK的动态代理和CGLIB的字节码生成技术
+ 模板方法
    用来解决代码重复问题。比如，RestTemplate，JmsTemplate，JpaTemplate
+ 观察者模式
    定义对象建一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都会得到通过被制动更新，如Spring种Listern实现的ApplicationListener

### Spring框架中有哪些不同类型的事件？

+ 上下文更新事件 (ContextRefreshedEvent)
    在调用ConfigurableApplicationContext接口中的refresh()方法时被触发

+ 上下文开始事件 (ContextStartedEvent)
    当容器调用ConfigurableApplicationContext的start()方法开始/重新开始容器时触发该事件

+ 上下文停止事件 (ContextStoppedEvent)
    当容器调用ConfigurableApplicationContext的stop()方法停止容器时触发该事件

+ 上下文关闭事件 (ContextClosedEvent)
    当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁

+ 请求处理事件 （RequestHandledEvent)
    在Web应用中，当一个http请求(request)结束触发该事件。如果一个Bean实现了ApplicationListener接口，当一个ApplicationEvent被发布以后，bean会自动被通知

### 什么是Spring？

为了降低Java开发的复杂性，Spring采取了以下4种关键策略

+ 基于POJO的轻量级和最小侵入性编程
+ 通过依赖注入和面向接口实现松耦合
+ 基于切面和惯例进行声明式编程
+ 通过切面和模板减少样板式代码


### 什么是IOC？

由程序代码直接操控的对象的调用权交给容器，通过容器来实现对象组件的装配和管理。(通过工厂模式和反射机制实现)

### BeanFactory 与 ApplicationContext 有什么区别？

BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当作Spring的容器。其中ApplicationContext是BeanFactory的子接口。

BeanFactory： 是Spring里面最底层的接口，包含了各种Bean的定义，读取bean配置文件，管理bean的加载，实例化，控制bean的生命周期，维护bean之间的依赖关系。

ApplicationContext： 作为BeanFactory的派生，除了提供BeanFactory所具有的功能外，还提供了更完整的框架功能。


## Spring


## SpringMVC



## SpringBoot



## SpringCloud







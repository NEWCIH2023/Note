[TOC]

# Spring面试

## Spring框架

为了降低Java开发的复杂性，Spring采取了以下4种关键策略

+ 基于POJO的轻量级和最小侵入性编程
+ 通过依赖注入和面向接口实现松耦合
+ 基于切面和惯例进行声明式编程
+ 通过切面和模板减少样板式代码

### 用了什么设计模式？

+ 工厂模式
    BeanFactory就是简单工厂模式的体现，用来创建对象的实例
+ 单例模式
    Bean默认为单例
+ 代理模式
    Spring的*AOP*功能用到了JDK的动态代理和CGLIB的字节码生成技术
+ 模板方法
    用来解决代码重复问题。比如，RestTemplate，JmsTemplate，JpaTemplate
+ 观察者模式
    定义对象建一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都会得到通过被制动更新，如Spring种Listern实现的ApplicationListener

### Spring Bean的作用域
+ *singleton* 
  + **默认**作用域范围，确保不管接收到多少个请求，每个容器中只有一个bean的实例，单例的模式由bean factory自身来维护
+ *prototype*
  + 原型范围与单例范围相反，为每一个bean请求提供一个实例
+ *request*
  + 在请求bean范围内会为每一个来自客户端的网络请求创建一个实例，在session过期后，bean会随之失效
+ *session*
  + 与请求范围类似，确保每个session中有一个bean的实例，在session过期后，bean会随之失效
  + 与*request*区别：request生命周期从*HTTP请求发起*开始，到*请求处理结束*结束。而session生命周期通常与用户的*会话生命周期*相同，从用户*打开浏览器访问网站*开始，到用户*关闭浏览器*结束

### Spring如何解决循环依赖
    
+ 首先从*一级缓存 singletonObjects*中获取
+ 如果获取不到，并且对象正在创建中，就再从*二级缓存 earlySingletonObjects*中获取
+ 如果获取不到，且允许singletonFactories通过getObject()获取，就从*三级缓存 singleFactory.getObject()* 获取
+ 如果从*三级缓存*中获取到就从*singletonFactories*中移除，并放入*earlySingletonObjects*中。也就是从*三级缓存*移动到*二级缓存*
  + Spring解决循环依赖的诀窍就在于*三级缓存 singletonFactories*，它是个接口，该接口在*doCreateBean*方法中调用了实例化步骤，即在*createBeanInstance*之后调用，也就是说*单例对象此时已经被创建出来（调用了构造器）*。虽然还不完美，还没有进行后续步骤，但是已经能被认出来了，所以Spring将这个对象提前曝光出来让大家使用

#### 什么样的循环依赖无法处理
    
+ 因为加入*singletonFactories 三级缓存*的前提是*执行了构造器*来创建半成品对象，所以*构造器的循环依赖*无法解决。因此，Spring不能解决*A的构造方法中依赖了B的实例对象，同时B的构造方法中依赖了A
  的实例对象*这类问题
+ Spring不支持*原型(prototype)Bean属性注入循环依赖*，不同于构造器注入循环依赖会在创建Spring容器Context时报错，它会在用户执行代码如context.getBean()
  时抛出异常，因为对于*原型Bean，Spring容器只有在需要时才会实例化、初始化它*。因为*Spring容器不缓存prototype类型的Bean，使得无法提前暴露出一个创建中的Bean*。Spring
  容器在获取prototype类型的Bean时，如果因为环的存在，检测到当前线程已经正在处理该Bean时，就会抛出异常
  ```java
  public abstract class AbstractBeanFactoy {
         private final ThreadLocal<Object> prototypesCurrentlyInCreation = new NamedThreadLocal<>();
  
        protected boolean isPrototypeCurrentlyInCreation(String beanName) {
            Object curVal = this.prototypesCurrentlyInCreation.get();
            return (curVal != null && (curVal.equals(beanName) || (curVal instanceof Set && ((Set<?>)curVal).contains(beanName))));
        } 
  }
  ```
  
#### 构造方法注入 VS setter注入

+ *setter注入*不会重写构造方法的值。如果对同一个变量同时使用了*构造方法注入*和*setter注入*，那么构造方法将不能覆盖由*setter注入*的值。很明显，因为构造方法仅在对象被创建时调用
+ *setter注入*有可能还不能保证某种依赖是否已经被注入，也就是说这时对象的依赖关系有可能是*不完整*的。而在另一种情况下，*构造器注入不允许生成依赖关系不完整的对象*

### Spring框架中有哪些不同类型的事件？

+ 上下文更新事件 (ContextRefreshedEvent)
    + 会在*ApplicationContext*被初始化或者更新时发布。也可以在调用*ConfigurableApplicationContext*接口中的refresh()方法时被触发

+ 上下文开始事件 (ContextStartedEvent)
    + 当容器调用*ConfigurableApplicationContext*的start()方法开始/重新开始容器时触发该事件

+ 上下文停止事件 (ContextStoppedEvent)
    + 当容器调用*ConfigurableApplicationContext*的stop()方法停止容器时触发该事件

+ 上下文关闭事件 (ContextClosedEvent)
    + 当*ApplicationContext*被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁

+ 请求处理事件 （RequestHandledEvent)
    + 在Web应用中，当一个http请求(request)结束触发该事件。如果一个Bean实现了*ApplicationListener*接口，当一个ApplicationEvent被发布以后，bean会自动被通知
+ 自定义事件
    ```java
    public class CustomApplicationEvent extends ApplicationEvent {
        public CustomApplicationEvent(Object source, final String msg) {
            super(source);
            System.out.println("Created a custom event.");
        }
    }
    // 为了监听这个事件，还需要创建一个监听器
    public class CustomEventListener implements ApplicationListener<CustomApplicationEvent> {
        @Override
        public void onApplicationEvent(CustomApplicationEvent applicationEvent) {
            // handle event
        } 
    }
    // 之后通过 applicationContext 接口的publishEvent() 方法来发布自定义事件
    CustomApplicationEvent customEvent = new CustomApplicationEvent(applicationContext, "Test Message");
    applicationContext.pulishEvent(customEvent);
    ```

### AOP (Aspect Oriented Program) 切面编程

> `AOP编程`思想就是把这些`横向性`的问题和业务逻辑进行分离，从而起到解耦的目的。

+ 使用`@Aspect` 定义切面类
+ 使用`@PointCut` 定义切点

AOP通过两种方式实现 (`没有默认实现的说法`)
+ java动态代理 (`被代理的类必须是接口`)
    + 生成的代理类的toString前缀是`$Proxy`
    + 生成字节码文件的字节数组
+ cglib动态代理
    + 生成的代理类的toString前缀是`CGLIB$`

> java动态代理为什么必须是接口？因为`代理类必须继承Proxy类`，所以不能继承用户类，而只能是实现用户的接口

```java
// AOP使用的代理实现，根据判断条件而来，没有默认使用其中一种的说法
public class DefaultAopProxyFactory implements AopProxyFactory, Serializable {

    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {

        if (config.isOptimized() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {

            Class<?> targetClass = config.getTargetClass();

            if (targetClass == null) {
                throw new AopConfigException("TargetSource cannot determine target class: Either an interface or a target is required for proxy creation")
            }

            if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
                return new JdkDynamicAopProxy(config);
            }

            return new ObjenesisCglibAopProxy(config);
        } else {
            return new JdkDynamicAopProxy(config);
        }
    }
}
```

```java
// 创建上下文，这里面通过后置处理器，已经生成了代理对象
AnnotationConfigApplicationContext context = new AnnotationConfigApplicatonContext(...);

// 获取对象
UserService service = context.getBean(UserSerivce.class);

service.doSomething();
// 获取到的service不是原生对象，是代理对象
```


### IOC容器

+ 由程序代码直接操控的对象的调用权交给容器，通过容器来实现对象组件的装配和管理。(通过工厂模式和反射机制实现)
  。其思想是*反转资源获取的方向*。传统的资源查找方式要求组件向容器发起请求查找资源。作为回应，容器适时的返回资源，而应用了IOC
  之后，则是容器主动地将资源推送给它所管理的组件，组件所要做的仅是选择一种合适的方式来接受资源，这种行为也被称为查找的被动形式

> IOC容器把对象注入进去的时候，已经不是原生对象，而是代理对象了。

IOC获取到实例对象的方式
+ XML注入
+ 注解注入

### BeanFactory 与 ApplicationContext 有什么区别？

+ BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当作Spring的容器。其中ApplicationContext是BeanFactory的子接口。

+ BeanFactory
  + 是Spring里面最底层的接口，包含了各种Bean的定义，读取bean配置文件，管理bean的加载，实例化，控制bean的生命周期，维护bean之间的依赖关系。

+ ApplicationContext
  + 作为BeanFactory的派生，除了提供BeanFactory所具有的功能外，还提供了更完整的框架功能。
  + 以下是三种常见的实现方式
    + *ClassPathXmlAppliationContext*：从classpath的XML配置文件中读取上下文，并生成上下文定义
    + *FileSystemXmlApplicationContext*：由文件系统中的XML配置文件读取上下文
    + *XmlWebApplicationContext*：由Web应用的XML文件读取上下文
    + *AnnotationConfigApplicationContext*：基于Java配置启动容器

### Spring Bean的生命周期

+ 实例化 **Instantiation**
+ 属性赋值 **Populate**
+ 初始化 **Initialization**
+ 销毁 **Destruction**

+ Spring提供了以下四种方式来管理Bean的生命周期事件
  + *InitializingBean* 和 *DisposableBean* 回调接口
  + 针对特殊行为的其它 *Aware* 接口
  + Bean配置文件中的Custom *init()* 方法和 *destroy()* 方法
    ```xml
    <beans>
        <bean id="demoBean" class="org.newcih" init-method="customInit" destroy-method="customDestroy"/>
    </beans>
    ```
  + *@PostConstruct* 和 *@PreDestroy* 注解方式


## SpringMVC
MVC是软件架构模式，是一种分离业务逻辑与显示页面的设计方法，他把软件系统分为三个基本部分：`模型(Model)`，`视图(View)`，`控制器(Controller)`。

+ 控制器
	对请求进行处理，负责选择视图
+ 视图
	用户与程序交互的界面
+ 模型
	用于业务处理

### 原理

#### 启动流程

+ 如果在web.xml中配置了ContextLoaderListener，那么Tomcat在启动的时候会先加载父容器，并将其放到ServletContext中
+ 然后加载DispatcherServlet，因为DispatcherServlet实质是一个Servlet，所以会先执行它的init方法。这个init方法在HttpServletBean这个类中实现，其主要工作是做一些初始化工作，将我们在web.xml中配置的参数书设置到Servlet中，然后再触发FrameWorkServlet的initServletBean方法
+ FrameworkServlet主要作用是初始化Spring子上下文，设置其父上下文，并将其放入ServletContext中
+ FrameworkServlet在调用initServletBean()的过程中，同时会触发DispatcherServlet的onRefresh()方法，这个方法会初始化SpringMVC的各个功能组件。比如异常处理器、视图处理器、请求映射处理

#### 执行流程

+ 发起请求到`前端控制器 DispatcherServlet`，该控制器就会过滤出你哪些请求可以访问该servlet
+ 前端控制器会找到`处理器映射器 HandlerMapping`，通过处理器映射器完成`url`到`controller`的映射
+ 通过`映射器`找到对应的`处理器 Handler` ，并将`处理器`返回给`前端控制器`。(`返回的处理器前有拦截器`)
+ `前端控制器`拿到`处理器`之后，找到`处理器适配器 HandlerAdapter`，通过适配器来访问和执行处理器。(`不能直接访问处理器，因为Spring的设计，不知道调用哪个方法，不知道处理器类如何创建出来的，如注解等等`)
+  执行处理器
+ 处理器返回`ModelAndView`对象给`处理器适配器 HandlerAdapter`
+ 通过`处理器适配器 HandlerAdapter`将`ModelAndView`对象返回给`前端控制器(DispatcherServlet)`
+ `前端控制器`请求`视图解析器 ViewResolver`进行解析，将`ModelAndView`的数据进行视图渲染。
+ 最后返回结果。

#### Spring常用注解

+ @Controller/@RestController 
  + 标注一个控制器类
+ @RequestMapping 
  + 用来处理请求地址映射的注解
+ @Resource 
  + 非Spring注解，做Bean的注入，默认按照`名称`注入，也可指定类型
+ @Autowired 
  + 自动注入，按照类型装配依赖对象。如果想用按照`名称`，搭配`@Qualifier`
+ @Required
  + 应用于设值方法，它表明受影响的bean属性在配置时必须放在XML配置文件中，否则容器就会抛出一个 BeanInitializationExeption 异常
+ @ModelAttribute 
  + 该Controller所有方法调用前，先执行此@ModelAttribute方法，可用于注解和方法参数中
+ @SessionAttributes 
  + 把值放到session作用域中
+ @PathVariable 
  + 用于将请求URL中的模板变量映射到功能处理方法的参数上
+ @RequestParam 
  + 控制层获取参数
+ @RequsetBody, @ResponseBody 
  + 用于将Controller方法获取或返回的对象，通过适当的`HttpMessageConverter`转换为指定格式
+ @Componet 
  + 通用的注解，标注组件
+ @Repository 
  + 注解Dao层
+ @Service 
  + 标注业务层组件类

#### SpringMVC设置*重定向*和*转发*

+ *转发*：在返回值前面加*forward:*
+ *重定向*：在返回值前面加*redirect:*



#### SpringMVC的拦截器


## SpringBoot



## SpringCloud






[TOC]

# 常见问答


### 阻塞队列的实现

阻塞队列 (`BlockingQueue`)是Java `util.concurrent`包下重要的数据结构，`BlockingQueue`提供了线程安全的队列访问方式：当阻塞队列进行插入数据时，如果队列已满，线程将会阻塞等待直到队列非满；从阻塞队列取数据时，如果队列已空，线程将会阻塞等待直到队列非空。并发包下很多高级同步类的实现都是基于`BlockingQueue`实现的。

### await方法是怎么实现的？

> 当前线程进入等待状态直到被通知(`signal`)或中断，当前线程进入后台运行状态且从`await()`方法返回其他线程调用该`Condition`的`signal`或者`signalAll`方法，而当前线程被选中唤醒

1. 其他线程(`interrupt`)中断当前线程
2. 如果当前等待线程从`await`方法返回，那么表明当前线程已经获取了`Condition`对象的锁


### wait方法调用

`wait()`需要和`synchronized`搭配使用，用于线程同步。`wait()`总是在一个循环中被调用，挂起来当前线程来等待一个条件的成立。`wait`调用会一直等到其他线程调用`notifyAll()`时才返回。

当一个线程在执行`synchronized`的方法内部，调用了`wait()`后，该线程会释放该对象的锁，然后该线程会被添加到该对象的等待队列中，只要该线程在等待队列中，就会一直处于闲置状态，不会被调度执行。要注意`wait()`方法会强迫线程先进行释放锁操作，所以在调用`wait()`时，该线程必须已经获得锁，否则会抛出异常。由于`wait()`在`synchronized`的方法内部被执行，锁一定已经获得，就不会抛出异常了。

### 线程池参数说明

+ corePoolSize

创建了线程池后，默认初始没有任何线程，等待任务来后才创建线程。超过核心线程数后，放入阻塞队列。

+ maxPoolSize

当线程数大于等于`corePoolSize`，并且任务阻塞队列已满时，线程池会创建新的线程，直到线程数达到`maxPoolSize`。当线程数达到`maxPoolSize`，且任务阻塞队列已满，这时候线程池会拒绝处理任务而抛出异常。

+ keepAliveTime

当线程空闲时间达到`keepAliveTime`，该线程会退出，直到线程数等于`corePoolSize`。如果设置了`allowCoreThreadTimeout`为true，则所有线程都会退出。

+ 参数设置说明
    + tasks 每秒的任务数，假设为500-1000
    + taskcost 每个任务花费的时间，假设为0.1s
    + responsetime 系统允许容忍的最大响应时间，假设为1s


`corePoolSize`：每秒需要处理多少个任务 => tasks * taskcost
`queueCapaticy`: 每秒可以缓存多少个任务 => 
`maxPoolSize`: 每秒可以缓存多少个任务 => tasks-corePoolSize

> 网上的例子没有权威性，全TM是国内的码农抄来抄去的代码和博客。

这里使用固定线程数线程池的创建，即`corePoolSize`与`maxPoolSize`数一致，该值使用如下获取

```java
Runtime.getRuntime().availableProcessors() * 50;
```

这种设置线程数的方式，在`StackOverFlow`看得有点多，而且`hsweb`开源项目也是使用这种方法的

### 死锁如何产生？如何防范死锁

+ 死锁发生的情形

    + 一个线程两次申请锁 （`第一次申请成功，第二次重复申请，这时候无法获取到被第一次时申请到的锁，造成拥有锁的线程挂起`）

    + 两个线程互相申请对方的锁，但是对方都不释放锁

+ 死锁产生的必要条件

    + 互斥：一次只有一个进程可以使用一个资源。其他进程不能访问已分配给其他进程的资源。
    + 占有且等待：当一个线程在等待分配得到其他资源时其继续占有已分配得到的资源。
    + 非抢占：不能强行抢占进程中已占有的资源。
    + 循环等待：存在一个封闭的进程链，使得每个资源至少占有此链中下一个进程所需要的一个资源。

+ 处理死锁的四种方法

    + 死锁预防：通过确保死锁的一个必要条件不会满足，保证不会发生死锁
    + 死锁检测：允许死锁的发生，但是可以通过系统设置的检测结构及时的检测出死锁的发生，采取一些措施，将死锁清除掉
    + 死锁避免：在资源分配过程中，使用某种方法避免系统进入不安全的状态，从而避免发生死锁
    + 死锁解除：与死锁检测相配套的一种措施。当检测到系统中已发生死锁，需将进程从死锁状态中解脱出来。

+ 避免死锁的方法

    + 银行家算法：每一个线程进入系统时，它必须声明在运行过程中，所需的每种资源类型的最大数目，其数目不应超过系统所拥有每种资源总量，当线程请求一组资源，系统必须确定有足够资源分配给该线程，若有在进一步计算这些资源分配给进程后，是否会使系统处于不安全状态，不会的话则分配资源，否则等待。


### volatile的作用，与锁的区别，会有线程不安全的情况吗？

线程安全的两个方面：`执行控制`和`内存可见`

+ 执行控制：控制代码执行顺序及是否可以并发执行
+ 内存可见：控制线程执行结果在内存中对其他线程的可见性。根据`Java内存模型`的实现，线程在具体执行时，会先拷贝主存数据到线程本地，操作完成后再把结果从线程本地刷新到主存。


`synchronized` 解决的是执行控制的问题，他会阻止其他线程获取当前对象的监控锁，这样就使得当前对象中被`synchronized`关键字保护的代码块无法被其他线程访问，也就无法并发执行。更重要的是，`synchronized`还会创建一个`内存屏障`，内存屏障指令保证了所有CPU操作结果都会直接刷到主存中，从而保证了操作的内存可见性，同时也使得先获得这个锁的线程的所有操作，都`happens-before`于随后获得这个锁的线程的操作。

> 在JMM中，如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须存在 `happens-before` 关系

`happens-before`原则非常重要，它是判断数据是否存在竞争、线程是否安全的主要依据，依靠这个原则，我们解决在并发环境下两操作之间是否可能存在冲突的所有问题。下面就一个简单的例子稍微了解

```java

i = 1;  // 线程A执行
j = i;  // 线程B执行

```

j是否等于1呢？假定线程A的操作`happens-before`于线程B的操作，那么可以确定线程B执行后`j=1`一定成立。

+ 如果一个操作`happens-before`另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前。
+ 两个操作之间存在`happens-before`关系，并不意味着一定要按照`happens-before`原则制定的顺序来执行。如果重排序之后的执行结果与按照`happens-before`关系来执行的结果一致，那么这种重排序并不非法。

`volatile`解决的是内存可见性的问题，会使得所有对`volatile`变量的读写都会直接刷到主存，即保证了变量的可见性。

> 使用`volatile`关键字仅能实现对原始变量(`如boolean、short、int、long等`)操作的原子性，但需要特别注意，`volatile`不能保证复合操作的原子性，即使只是`i++`，实际上也是由多个原子性操作组成。

对于`volatile`关键字，当且仅当满足以下所有条件时可使用：

+ 对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值
+ 该变量没有包含在具有其他变量的不变式中


`volatile`和`synchronized`的区别

+ `volatile`本质是在告诉jvm当前变量在寄存器中的值是不确定的，需要从主存中读取；`synchronized`则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。

+ `volatile`仅能使用在变量级别；`synchronized`则可以使用在变量、方法和类级别的
+ `volatile`仅能实现变量的修改可见性，不能保证原子性；而`synchronized`则可以保证变量的修改可见性和原子性
+ `volatile`不会造成线程的阻塞，`synchronized`可能会造成线程的阻塞
+ `volatile`标记的变量不会被编译器优化，`synchronized`标记的变量可以被编译器优化。


### ThreadLocal是怎么实现的？

理论上，用`map`存放线程对象和值的键值对就可以实现`ThreadLocal`的功能，但是性能上不是最优的，多线程访问`ThreadLocal`的`map`对象会导致并发冲突，用`synchronized`加锁会导致性能上的损失。因此，`JDK7`里是将map对象保存在线程里，这样每个线程去取自己的数据，就不需要加锁保护的。


### 熔断、降级、限流、负载均衡

+ 熔断：熔断模式可以防止应用程序不断地尝试可能超时和失败的服务，能达到应用程序执行而不必等待下游服务修正错误服务

+ 降级：降级就是为了解决资源不足和访问量增加的矛盾。在有限的资源情况下，为了能抗住大量的请求，就需要对系统做出一些牺牲，有点弃卒保帅的意思。放弃一些功能，保证整个系统能平稳运行。

+ 限流：通过对并发访问进行限速。

限流的实现方式

+ 计数器

最简单的实现方式，维护一个计数器，来一个请求数加一，达到阈值时，直接拒绝请求。一般使用`nginx + lua + redis`这种方式，`redis`存计数值。

+ 漏斗模式

漏斗很多是用一个队列实现的，当流量过多时，队列会出现积压，队列满了，则开始拒绝请求。

+ 令牌桶

处理请求时，先拿到token才处理，当令牌桶没有令牌时，不处理请求。

### 服务端负载均衡与客户端负载均衡的区别？


+ 服务端负载均衡：通过`nginx`负载均衡服务器发送到不同的上游服务器去处理。

+ 客户端负载均衡：类似微服务的服务发现这个场景。`ribbon`是基于HTTP的客户端负载均衡器，从`eureka`服务注册中心获取服务端列表，然后进行轮询访问以达到负载均衡的作用。

### 消息队列使用场景

+ 异步处理

+ 流量削峰

+ 应用解耦

### 消息队列的区别对比，消息丢失的情况有哪些？如何确保消息不丢失。

| 对比项 | RabbitMQ | RocketMQ | ActiveMQ | Kafka |
|:---:|:---:|:---:|:---:|:---:|
|消息推拉模式|多协议，Pull/Push均有支持|多协议，Pull/Push均有支持|多协议，Pull/Push均有支持|Pull|
|客户端支持语言|几乎支持所有常用语言|Java、C、C++、Python、PHP、Perl等大部分|Java、C++|支持大部分|
|消息批量操作|不支持|支持|支持|支持|
|单机吞吐量|万级|万级|十万级|十万级|
|事务|不支持|支持|支持|不支持，但可以通过`Low Level Api`保证仅消费一次|

+ 消息丢失的情况

    + 生产者往MQ写数据丢失

        可能因为网络原因，数据传输丢失

    + MQ本身数据丢失了

        数据放在内存中，MQ机器宕机了，那么内存中的数据丢失了

    + 消费者消费消息时丢失了

       消费者接受到消息，马上给`MQ`返回已经接受到消息的回复(`自动ACK`)，消费者接着处理逻辑，在处理过程中，消费者宕机了；下次重启的时候，消费者会获取到下一条数据消费，这样数据就丢失了。 

+ 解决消息丢失的问题

    + 解决生产者丢失数据

        1. 在生产者发送消息的时候，加上事务。缺点是，加上事务之后，消息变成同步了，影响吞吐量，比较耗性能。
        2. `confirm机制`，在生产者开启`confirm机制`，每个消息分配唯一`id`，生产者发送完消息之后就不用管了，`rabbitmq`如果成功接收到消息，那么就会调用生产者的`ack`方法，`rabbitmq`如果接收失败，那么会调用生产者的`nack`方法，生产者根据情况判断是否要重试。

        > 一般情况下，使用`confirm模式`，性能、吞吐量都要比事务机制高。`confirm`是异步的，不会阻塞。

    + 解决`MQ`丢失数据

        1. 开启持久化，把内存中的数据刷新到磁盘中。如果在写入磁盘的时候挂了，这样的话还是有数据丢失，但是可以等数据持久化到磁盘，再通知生产者`ack`方法，如果没有持久化成功，生产者长时间没有收到消息可以尝试再重试一次。

    + 解决消费者丢失数据

        1. 关闭`自动ack`，等自己处理完之后再发送`ack`给`MQ`。

### redis的缓存策略

`redis`删除过期键的策略如下

+ 定时删除

    给key设置过期时间

    > 缺点是为每一个键实现一个定时器，会耗费较多的资源

+ 定期删除

    定期扫描一遍`expires`字典，将已过期的键删除

    > 缺点是不能保证所有的键过期时被及时删除，需在下一个定期删除区间内被扫描并删除

+ 惰性删除

    获取每一个`key`的时候，判断一下该`key`是否已经过期，如果已经过期则删除。
    
    > 如果一个key一直不使用，即使过了过期时间也会一直占用内存，大量的不使用的`key`会使得内存暴增。

`redis`采用了`定期删除`和`惰性删除`两种策略

### awk使用

`awk`处理列，`sed`处理行。

+ 打印某列数据

```shell
awk '{[pattern] action}' {filename}

awk '{print $1,$4}' log.txt
```

+ 使用分割符

```shell
awk -F, '{print $1,$2}' log.txt
```

+ 设置变量

```shell
awk -v
awk -va=1 '{print $1,$1+a}' log.txt
```

+ 执行awk脚本

```shell
awk -f {awk脚本} {文件名}
awk -f cal.awk log.txt
```

### 查看linux某个端口是否被占用

+ 使用lsof

```shell
lsof -i:80
```

+ 使用netstat

```shell
netstat | grep 80
```

### RabbitMQ高可用

RabbitMQ运行模式大致有3种

+ 单一模式

非集群模式，单机运行


+ 普通模式

RabbitMQ默认的集群模式。对于`Queue`来说，消息实体只存在于一个节点，A、B两个节点仅有相同的队列结构。当消息进入A节点的`Queue`，消费者从B节点拉取时，RabbitMQ会临时在A、B间进行消息传输，把A中的消息实体取出并经过B发送给消费者。

> 缺点是当A节点故障时，B节点无法取到A节点中还未消费的消息实体。

+ 镜像模式

`Queue`同时存在于多个节点，消息主体会主动在镜像节点之间同步，而不是在消费者拉取数据时临时出传输。

> 缺点是会降低系统性能。同时如果镜像队列过多，加之大量的消息进入，集群内部的网络带宽将会被这种同步通讯大大消耗掉。

### BIO、AIO和NIO区别

+ BIO

一个连接一个线程。同步并阻塞。客户端有连接请求时，服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销。

数据的读取写入必须阻塞在一个线程内等待其完成。

> 可以通过线程池机制改善

+ NIO

一个请求一个线程。同步非阻塞。客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。


+ AIO

一个有效请求一个线程。异步非阻塞。客户端的I/O请求都是由OS先完成了再通知服务器应用去启动线程进行处理。


> AIO VS NIO ：NIO需要使用者线程不停的轮询IO对象，来确定是否有数据准备好可以读了，而AIO则是在数据准备好了之后，才会通知数据使用者，这样使用者就不需要不停地轮询了。

### Dubbo使用

> Dubbo是解决分布式服务问题的框架，是一个基于SOA面向服务体系结构的基础设施，提供了诸如服务发布注册、容错调用、部署、调用次数监控、每个服务的性能监控等很多功能。

核心部分包括


+ 远程通讯

提供多种基于长连接的NIO框架抽象封装，包括多种线程模型，序列化，以及“请求-响应“模式的信息交换方式

+ 集群容错

提供基于接口方法的透明远程过程调用，包括多协议支持，以及软负载均衡，失败容错，地址路由，动态配置等集群支持。

+ 自动发现

基于注册中心目录服务，使服务消费方能动态的查找服务提供方，使地址透明，使服务提供方可以平滑增加或减少机器。

核心组件包括

+ `Provider`

暴露服务的服务提供方

+ `Consumer`

调用远程服务的服务消费方

+ `Registry`

服务注册与发现的注册中心

+ `Monitor`

统计服务的调用次数和调用时间的监控中心

+ `Container`

服务运行容器

### BeanFactory和ApplicationContext有什么区别？


`ApplicationContext`和`BeanFactory`相比，提供了更多的扩展功能，但是这还不是主要区别，主要区别在于`BeanFactory`是延迟加载，举个例子：如果`Bean`没有完全注入，`BeanFactory`加载后，会在你第一次调用`GetBean`方法才会抛出异常；而`ApplicationContext`会在初始化的时候就加载并且检查，这样的好处是可以及时检查依赖是否完全注入

+ `ApplicationContext`是`BeanFactory`的子接口

### Spring Bean的生命周期

+ Bean自身的方法

包括了`Bean`本身调用的方法和通过配置文件中`<bean>`的`init-method`和`destroy-method`指定的方法。

+ Bean级生命周期接口方法

包括了`BeanNameAware`、`BeanFactoryAware`、`InitiallizingBean`和`DisposableBean`这些接口的方法。

+ 容器级生命周期接口方法

包括`InstantiationAwareBeanPostProcessor`和`BeanPostProcessor`这两个接口实现，一般称它们的实现类为`后处理器`

+ 工厂后处理器接口方法

包括了`AspectJWeavingEnabler`、`ConfigurationClassPostProcessor`，`CustomAutowireConfigurer`等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的，在应用上下文装配配置文件之后立即调用。

示例如下

1. 开始初始化容器
2. 进入`BeanFactoryPostProcessor`实现类的构造方法
3. `BeanFactoryPostProcessor`调用`postProcessBeanFactory`方法
4. 进入`BeanPostProcessor`实现类的构造方法
5. 进入`InstantiationAwareBeanPostProcessorAdapter`实现类的构造方法
6. `InstantiationAwareBeanPostProcessor`调用`postProcessBeforeInstantiation`方法
7. 调用`Person`类的构造方法实例化
8. `InstantiationAwareBeanPostProcessor`调用`postProcessPropertyValues`方法
9. 注入属性`address`
10. 注入属性`name`
11. 注入属性`phone`
12. 调用`BeanNameAware`.`setBeanName`()方法
13. 调用`BeanFactoryAware`.`setBeanFactory`()方法
14. `BeanPostProcessor`接口方法`postProcessBeforeInitialization`对属性进行更改
15. 调用`InitializingBean`.`afterPropertiesSet`()方法
16. 调用<bean>的`init-method`属性指定的初始化方法
17. `BeanPostProcessor`接口方法`postProcessAfterInitialization`对属性进行更改
18. `InstantiationAwareBeanPostProcessor`调用`postProcessAfterInitialization`方法
19. 容器初始化成功
20. Person [address=广州]
21. 开始关闭容器
22. 调用`DiposibleBan`.`destory`()
23. 调用<bean>的`destroy`-`method`属性指定的初始化方法

### Java类加载器

> 双亲委派模型，该机制可以避免重复加载，当父亲已经加载了该类的时候，就没有必要子`ClassLoader`再加载一次。JVM根据`类名+包名+ClassLoader实例ID`来判定两个类是否相同，是否已经加载过。(可以通过创建不同的`ClassLoader`实例来实现类的热部署)

+ `BootStrapClassLoader`

最顶层的类加载器，由C++编写而成，已经内嵌到`JVM`中。在JVM启动时会初始化该`ClassLoader`，它主要用来读取`Java`的核心类库`JRE/lib/rt.jar`中所有的`class`文件，这个`jar`文件中包含了`java`规范定义的所有接口及实现。

+ `ExtensionClassLoader`

用来读取`Java`的一些扩展类库，如读取`JRE/lib/ext/*.jar`中的包

+ `AppClassLoader`

用来读取`classpath`下指定的所有`jar`包或目录的类文件，一般情况下，这个就是程序默认的类加载器

+ `CustomClassLoader`

用户自定义编写的，用来读取指定类文件。基于自定义的`ClassLoader`可用于加载非`classpath`中（如从网络上下载的`jar`或二进制）的`jar`及目录、还可以在加载前对`class`文件优化一些动作，如解密、编码等

> `ExtensionClassLoader`的父类加载器是`BootStrapClassLoader`，其实这里省掉了一句话，容易造成很多新手的迷惑。严格说，`ExtClassLoader`的父类加载器是`null`，只不过是在默认的`ClassLoader
`的`loadClass`方法中，当`parent`为`null`时，是交给`BootStrapClassLoader`来处理的，而且`ExtClassLoader`没有重写默认的`loadClass`方法，所有，`ExtClassLoader`也会调用`BootStrapClassLoader`类加载器来加载。

### Java虚拟机 JVM

JVM主要包括四个部分

+ `类加载器` 在`JVM`启动时或者在类运行时将需要的`class`加载到`JVM`中


+ `执行引擎` 负责执行`class`文件中包含的字节码指令


+ `内存区` 在`JVM`运行时操作所分配的内存区。运行时内存区主要分为5个区域

    + `方法区` 用于存储类结构信息的地方，包括常量池、静态变量、构造方法等
    + `java堆` 存储`java`实例或者对象的地方。这块是`GC`的主要区域。从存储的内容可以知道，方法区和堆是被所有`java`线程共享的
    + `java栈` `java`栈总是和线程关联在一起，每当创建一个线程时，`JVM`就会为这个线程创建一个对应的`java`栈。在这个`java`栈中又会包含多个栈帧，每运行一个方法就创建一个栈帧，用于存储局部变量表、操作栈，方法返回值等。每一个方法从调用直至执行完成的过程，就对应一个栈帧在`java`栈中入栈到出栈的过程。 
    + `程序计数器` 用于保存当前线程执行的内存地址。由于`JVM`程序是多线程执行，所以为了保证线程切换回来后，还能恢复到原先状态，就需要一个独立的计数器，记录之前中断的地方，可见线程计数器也是线程私有的。

+ `本地方法栈` 和`java`栈的作用差不多，只不过是为了`JVM`使用到的`native`方法服务的。


### Java对象的生命周期


+ 创建阶段

    1. 为对象分配存储空间
    2. 开始构造对象
    3. 从超类到子类对`static`成员进行初始化
    4. 超类成员变量按顺序初始化，递归调用超类的构造方法
    5. 子类成员变量按顺序初始化，子类构造方法调用

+ 应用阶段
    对象被至少一个强引用持有者

+ 不可见阶段
    程序本身不再持有该对象的任何强引用，虽然这些引用仍然是存在着的，简单说就是程序的执行已经超出了该对象的作用域了。

```java

boolean bool = false;

if (bool == true) {
    int count = 0;
    count++;
}

System.out.println(count);

```

+ 不可达阶段

对象处于不可达阶段是指该对象不再被任何强引用所持有。与`不可见阶段`相比，该阶段指程序不再持有该对象的任何强引用，这种情况下，该对象仍可能被JVM等系统下的某些已装载的静态变量或线程或`JNI`等强引用持有者，这些特殊的强引用被称为`GC root`。存在这些`GC root` 会导致对象的内存泄漏情况，无法被回收。 

+ 收集阶段

当垃圾回收器发现该对象已经处于不可达阶段并且垃圾回收器已经对该对象的内存空间重新分配做好准备时，则对象进入了收集阶段。如果该对象已经重写了`finalize()`，则会去执行该方法的终端操作。

+ 终结阶段

当对象执行完`finalize()`方法后仍然处于不可达状态时，则该对象进入终结阶段。在该阶段是等待垃圾回收器对该对象空间进行回收。


+ 对象空间重分配阶段

垃圾回收器对该对象的所占用的内存空间进行回收或者再分配了，则该对象彻底消失了，称之为`对象空间重新分配阶段`


### Zookeeper是什么？

Zookeeper可以做什么

+ 分布式服务注册与订阅

在分布式环境中，为了保证高可用性，通常同一个应用或同一个服务的提供方都会部署多份，达到对等服务。而消费者就需要在这些对等的服务器中选择一个来执行相关的业务逻辑，比较典型的服务注册与订阅，代表：`dubbo
`
+ 分布式配置中心

发布与订阅模型，即所谓的配置中心，顾名思义就是发布者将数据发布到`ZK`节点上，供订阅者获取数据，实现配置信息的集中式管理和动态更新。代表：百度的`disconf
`
+ 命名服务

在分布式系统中，通过使用命名服务，客户端应用能够根据指定名字来获取资源或服务的地址，提供者等信息。被命名的实体通常可以是集群中的机器，提供的服务地址，进程对象等等--这些可以称之为名字。其中较为常见的就是一些分布式服务框架中的服务地址列表。通过调用`ZK`提供的创建节点的`API`，能够很容易创建一个全局唯一的`path`，这个`path`就可以作为一个名称。

+ 分布式锁

主要得益于`ZooKeeper`为我们保证了数据的强一致性。锁服务可以分为两类，一个是`保持独占`，另一个是`控制时序`。

> 所谓保持独占，就是所有试图来获取这个锁的客户端，最终只有一个可以成功获得这把锁。通常的做法是把`zk`上的一个`znode`看作是一把锁，通过`create znode`的方式来实现。所有客户端都去创建/`distribute_lock`节点，最终成功创建的那个客户端也即拥有了这把锁。

> 所谓`控制时序`，就是所有试图来获取这个锁的客户端，最终都是会被安排执行，只是有个全局时序了，做法和上面基本类似，只是这里`/distribute_lock`已预先存在，客户端在它下面创建临时`有序节点`(这个可以通过节点的属性控制：`CreateMode.EPHEMERAL_SEQUENTIAL`来指定)。`ZK`的父节点/`distribute_lock`维持一份`sequence`，保证子节点创建的时序性，从而也形成了每个客户端的全局时序。

+ Master选举


### Dubbo是什么？



### 常见HTTP状态码

+ `1XX` **信息**
    
+ 100 Content：表明到目前为止都很正常，客户端可以继续发送请求或者忽略这个响应
    
+ `2XX` **成功**
    + `200` OK：
    + `204` `No Content`：请求已经成功处理，但是返回的响应报文不包含实体的主体部分。一般在只需要从客户端往服务器发送信息，而不需要返回数据时使用。
    + `206` `Partial Content`：表示客户端进行了范围请求，响应报文包含由Content-Range指定范围的实体内容。

+ `3XX` **重定向**
    + `301` `Moved Permanently`：永久性重定向
    + `302` `Found`：临时性重定向
    + `303` `See Other`：和302有着相同的功能，但是303明确要求客户端应该采用GET方法获取资源。
    > 虽然HTTP协议规定`301`、`302`状态下重定向时不允许把`POST`方法改成`GET`方法，但是大多数浏览器都会在`301`、`302`和`303`状态下的重定向把`POST`方法改成`GET`方法
    + `304` `Not Modified`：如果请求报文首部包含一些条件，例如：`If-Match`，`If-Modified-Since`，`If-None-Match`，`If-Range`，`If-Unmodified-Since`，如果不满足条件，则服务器会返回`304`状态码。
    + `307` `Temporary Redirect`：临时重定向，与`302`的含义类似，但是`307`要求浏览器不会把重定向请求的`POST`方法改成`GET`方法。

+ `4XX` **客户端错误**
    + `400` `Bad Request`：请求报文中存在语法错误
    + `401` `Unauthorized`：该状态码表示发送的请求需要有认证信息(`BASIC`认证、`DIGEST`认证)。如果之前已经进行过一次请求，则表示用户认证失败。
    + `403` `Forbidden`：请求被拒绝
    + `404` `Not Found`

+ `5XX` **服务器错误**
    + `500` `Internal Server Error`：服务器正在执行请求时发生错误
    + `503` `Service Unavaliable`：服务器暂时处于超负载或正在停机维护，现在无法处理请求


### 设计模式

#### 创建型

+ `单例模式 Singleton`：**确保一个类只有一个实例，并提供该实例的全局访问点**

使用一个私有构造方法、一个私有静态变量以及一个公有静态方法来实现。私有构造方法确保了不能通过构造方法来创建对象实例，只能通过公有静态方法返回唯一的私有静态变量。

> `懒汉式-线程不安全`：私有静态变量被延迟实例化，在多线程环境下不安全，可能多次实例化

```java

public class Singleton {

    private static Singleton uniqueInstance;

    private Singleton() {}

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

}    

```

> `饿汉式-线程安全`：采取直接实例化的方式就不会产生线程不安全的问题

```java

private static Singleton uniqueInstance = new Singleton();

```

> `懒汉式-线程安全`：只需要对`getUniqueInstance()`方法加锁，那么在一个时间点只能有一个线程能够进入该方法，从而避免了实例化多次`uniqueInstance`。但是当一个线程进入后其他线程必须等待，即使对象已经被实例化，这会让阻塞时间过长，因此该方法有性能问题。

```java

public static synchronized Singleton getUniqueInstance() {

    if (uniqueInstance == null) {
        uniqueInstance = new Singleton();
    }
    return uniqueInstance;
}

```

> `双重校验锁-线程安全`：对象只需要被实例化一次，之后就可以直接使用了。加锁操作只需要对实例化部分的代码进行，只有当`uniqueInstance`没有被实例化时，才需要进行加锁。`双重校验锁`先判断`uniqueInstance`是否已经被实例化，如果没有被实例化，那么才对实例化语句进行加锁。

```java

public static getUniqueInstance() {
    if (null == uniqueInstance) {
        synchronized(Singleton.class) {
            if (null == uniqueInstance) {
                uniqueInstance = new Singleton();
            }
        }
    }
}

```

`uniqueInstance`采用`volatile`关键字修饰也是很有必要的，`uniqueInstance = new Singleton();`这段代码其实是分三步执行

1. 为`uniqueInstance`分配内存空间
2. 初始化`uniqueInstance`
3. 将`uniqueInstance`指向分配的内存地址

> 由于`JVM`具有`指令重排`的特性，执行顺序有可能变成`1>3>2`。`指令重排`在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程`T1`执行了`1`和`3`，此时`T2`调用`getUniqueInstance()`后发现`uniqueInstance`不为空，因此返回`uniqueInstance`，但此时`uniqueInstance`还未被初始化。

**使用volatile可以禁止JVM的指令重排，保证在多线程环境下也能正常运行**

> `静态内部类实现`：当`Singleton`类被加载时，静态内部类`SingletonHolder`没有被加载进内存。只有当调用`getUniqueInstance()`方法从而触发`SingletonHolder`.`INSTANCE`时`SingletonHolder`才会被加载，此时初始化`INSTANCE`实例，并且`JVM`能确保`INSTANCE`只被实例化一次。

```java
public class Singleton() {
    private Singleton(){}

    private static class SingletonHolder() {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance() {
        return SingletonHolder.INSTNACE;
    }
}

```

> `枚举实现`：可以防止反射攻击。在其他实现中，通过`setAccessible()`方法可以将私有构造方法的访问级别设置为`public`，然后调用构造方法从而实例化对象，如果要防止这种攻击，需要在构造方法中添加防止多次实例化的代码。该实现是由`JVM`保证之后实例化一次，因此不会出现上述的反射攻击。该实现在多次`序列化`和`反序列化`之后，不会得到多个实例。而其他实现需要使用`transient`修饰所有字段，并且实现`序列化`和`反序列化`的方法。

```java
public enum Singleton {

    INSTANCE;

    private String objName;

    public String getObjName(){
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}

```

+ `简单工厂` `Simple Factory`：**在创建一个对象时不向客户暴露内部细节，并提供一个创建对象的通用接口**

> 简单工厂把实例化的操作单独放到一个类，这个类就成为`简单工厂类`，让`简单工厂类`来决定应该用哪个具体子类来实例化。这样做能把客户类和具体子类的实现解耦，客户类不再需要知道有哪些子类以及应当实例化哪个子类。客户类往往有多个，如果不使用简单工厂，那么所有的客户类都要知道所有子类的细节。而且一旦子类发生改变，例如增加子类，那么所有的客户类都要进行修改。


```java
public class SimpleFactory {
    public Product createProduct(int type) {
        if (type == 1)
            return new ConcreateProduct1();
        else if (type == 2) 
            return new ConcreateProduct2();
        return new ConcreateProduct3();
    }
}

public class Client {
    public static void main(String[] args) {
        SimpleFactory f = new SimpleFactory();
        Product p = f.createProduct(1);
    }
}

```

+ `工厂方法` `Factory Method`：定义了一个创建对象的接口，但由子类决定要实例化哪个类。工厂方法把实例化操作推迟到子类。

> 在简单工厂中，创建对象的是另一个类，而在工厂方法中，是由子类来创建对象。下图中，Factory有一个doSomething方法，这个方法需要用到一个产品对象，这个产品对象由factoryMethod方法创建。该方法是抽象的，需要由子类去实现。

```java
public abstract class Factory {
    abstract public Product factoryMethod();
    public void doSomething(){
        // 
    }
}

public class ConcreateFactory extend Factory {
    public Product factoryMethod() {
        return new ConcreateProduct();
    }
}    

public class ConcreateFactory1 extends Factory {
    public Product factoryMethod() {
        return new ConcreateProduct1();
    }
}

```

+ `抽象工厂` `Abstract Factory`：**提供一个接口，用于创建相关的对象家族**

> 抽象工厂模式创建的是对象家族，也就是很多对象而不是一个对象，并且这些对象是相关的，也就是说必须一起创建出来。而工厂方法模式只是用于创建一个对象，这和抽象工厂模式有很大不同。抽象工厂模式用到了工厂方法模式来创建单一对象，AbstractFactory中的createProductA()和createProductB()方法都是让子类来实现，这两个方法单独来看就是在创建一个对象，这符合工厂方法模式的定义。

至于创建对象的家族这一概念是在Client体现，Client要通过AbstractFactory同时调用两个方法来创建出两个对象，在这里这两个对象就有很大的相关性，Client需要同时创建出这两个对象。从高层次来看，抽象工厂使用了组合，即Client组合了AbstractFactory，而工厂方法模式使用了继承。

```java

public class AbstractProductA {
}

public class AbstractProductB {
}

public class ProductA1 extends AbstractProductA {
}

public class ProductA2 extends AbstractProductA {
}
    
public class ProductB1 extends AbstractProductB {
}

public class ProductB2 extends AbstractProductB {
}

public abstract class AbstractFactory {
    abstract AbstractProductA createProductA();
    abstract AbstractProductB createProductB();
}

public class ConcreteFactory1 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA();
    }
    AbstractProductB createProductB() {
        return new ProductB();
    }
}

public class ConcreteFactory2 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA();
    }
    AbstractProductB createProductB() {
        return new ProductB();
    }
}

public class Client {
    public static void main(String[] args) {
        AbstractFactory af = new ConcreateFactory1();
        AbstractProductA a = af.createProductA();
        AbstractProductB b = af.createProductB();
    }
}

```

+ `生成器模式` `Builder`：封装一个对象的构造过程，并允许按步骤构造

> 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

与抽象工厂的区别：在生产者模式里，有个指导者，由指导者来管理生产者，用户是与指导者联系的，指导者联系生产者最后得到产品。即生产者模式可以强制实行一种分步骤进行的建造过程。

![生产者模式UML](https://github.com/CyC2018/CS-Notes/blob/master/notes/pics/db5e376d-0b3e-490e-a43a-3231914b6668.png)


以下是简易的StringBuilder实现，参考了JDK8源码

```java

public class AbstractStringBuilder {

    protected char[] value;
    protected int count;

    public AbstractStringBuilder(int capacity) {
        count = 0;
        value = new char[capacity];
    }

    public AbstractStringBuilder append(char c) {
        ensureCapacityInternal(count + 1);
        value[count++] = c;
        return this;
    }

    private void ensureCapacityInternal(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {
            expandCapacity(minimumCapacity);
        }
    }

    void expandCapacity(int minimumCapacity) {
        int newCapacity = value.length * 2 + 2;
        if (newCapacity - minimumCapacity < 0) {
            newCapacity = minimumCapacity;
        }
        if (newCapacity < 0) {
            if (minimumCapacity < 0) {
                throw new OutOfMemoryError();
            }
            newCapacity = Integer.MAX_VALUE;
        }
    }
}

public class StringBuilder extends AbstractStringBuilder {
    public StringBuilder() {
        super(16);
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}

public class Client {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        final int count = 20;
        for (int i = 0; i < count; i++) {
            sb.append((char)('a'+i));
        }
        System.out.println(sb.toString());
    }
}

```

+ `原型模式` `Prototype`：**使用原型实例指定要创建对象的类型，通过复制这个原型来创建新的对象**

> 原型模式主要用于对象的复制，它的核心就是类图中的原型类`Prototype`。`Prototype`类需要具备以下两个条件：

+ 实现`Cloneable`接口。在`java`语言有一个`Cloneable`接口，它的作用只有一个，就是在运行时通知虚拟机可以安全地在实现了此接口的类上使用`clone`方法。在`java`虚拟机中，只有实现了这个接口的类才可以被拷贝，否则在运行时会抛出`CloneNotSupportedException`异常。

+ 重写`Object`类的`clone`方法。`java`中，所有类的父类都是`Object`类，`Object`类有一个`clone`方法，作用是返回对象的一个拷贝，但是其作用域`protected`类型的，一般的类无法调用，因此，`Prototype`类需要将`clone`方法的作用域修改为`public`类型。

```java

public abstract class Prototype {
    abstract Prototype myClone();
}

public class ConcretePrototype extends Prototype {
    private String field;

    public ConcretePrototype(String field) {
        this.field = field;
    }

    @Override
    Prototype myClone() {
        return new ConcretePrototype(field);
    }

    @Override
    public String toString() {
        return field;
    }
}

public class Client {
    public static void main(String[] args) {
        Prototype prototype = new ConcretePrototype("abc");
        Prototype clone = prototype.myClone();
        System.out.println(clone.toString());
    }
}

```


#### 行为型

+ `责任链` `Chain Of Responsibility`：**使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止。**

责任链模式结构的重要核心模块

+ Handler 抽象处理者

定义一个处理请求的接口，一般设计为抽象类，由于不同的具体处理者处理请求的方式不同，因此在其中定义了抽象请求处理方法。因为每一个处理者的下家还是一个处理者，因此在抽象处理者定义了一个抽象处理者类型的对象作为其对下家的引用。通过该引用，处理者可以连成一条链。

+ ConcreteHandler 具体处理者

抽象处理者的子类，可以处理用户请求，在具体处理者类中实现了抽象处理者中定义的抽象请求处理方法，在处理请求之前需要进行判断，看是否有相应的处理权限，如果可以处理请求就处理它，否则将请求转发给后继者，在具体处理者中可以访问链中下一个对象，以便请求的转发。

```java

public abstract class Handler {
    protected Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handleRequest(Request request);
}

public class ConcreteHandler1 extends Handler {
    public ConcreteHandler1(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE1) {
            System.out.println(request.getName() + " is handle by ConcreteHandler1");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

public class ConcreteHandler2 extends Handler {
    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2) {
            System.out.println(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

public class Request {
    
    private RequestType type;
    private String name;

    public Request(RequestType type, String name) {
        this.type = type;
        this.name = name;
    }

    public RequestType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

public enum RequestType {
    TYPE1, TYPE2
}

public class Client {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1(null);
        Handler handler2 = new ConcreteHandler2(handler1);

        Request request1 = new Request(RequestType.TYPE1, "request1");
        handler2.handleRequest(request1);

        Request request2 = new Request(RequestType.TYPE2, "request2");
        handler2.handleRequest(request2);
    }
}

```

+ `命令` `Command`：**将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能**
    + 使用命令来参数化其他对象
    + 将命令放入队列中进行排队
    + 将命令的操作记录到日志中
    + 支持可撤销的操作

下面说一下关键的一些类
1. Command类：是一个抽象类，类中对需要执行的命令进行声明，一般来说要对外公布一个`execute`方法用来执行命令
2. ConcreteCommane类：`Command`类的实现类，对抽象类中声明的方法进行实现
3. Client类：最终的客户端调用类
4. Invoker类：调用者，负责调用命令
5. Receiver类：接收者，负责接收命令并且执行命令

```java

public interface Command {
    void execute();
}

public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute(){
        light.off();
    }
}

public class Light {
    public void on() {
        System.out.println("light is on");
    }
    public void off() {
        System.out.println("light is off");
    }
}

public class Invoker {
    private Command[] onCommands;
    private Command[] offCommands;;
    private final int slotNum = 7;

    public Invoker() {
        this.onCommands = new Command[slotNum];
        this.offCommands = new Command[slotNum];
    }

    public void setOnCommand(Command command, int slot) {
        onCommands[slot] = command;
    }

    public void setOffCommand(Command command, int slot) {
        offCommands[slot] = command;
    }
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }
    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }
}

public class Client {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        invoker.setOnCommand(lightOnCommand, 0);
        invoker.setOffCommand(lightOffCommand, 0);
        invoker.onButtonWasPushed(0);
        invoker.offButtonWasPushed(0);
    }
}
```

+ `解释器` `Interpreter`：**为语言创建解释器，通常由语言的语法和语法分析来定义**
    + `TerminalExpression`：终结符表达式，每个终结符都需要一个`TerminalExpression`
    + `Context`：上下文，包含解释器之外的一些全局信息

以下是一个规则检验器实现，具有and和or规则，通过规则可以构建一颗解析树，用来检验一个文本是否满足解析树定义的规则。

```java
public abstract class Expression {
    public abstract boolean interpret(String str);
}

public class TerminalExpression extends Expression {
    private String literal = null;

    public TerminalExpression(String str) {
        literal = str;
    }

    public boolean interpret(String str) {
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens) {
            String test = st.nextToken();
            if (test.equals(literal)){
                return true;
            }
        }
        return false;
    }
}

public class AndExpression extends Expression {
    private Expression expression1 = null;
    private Expression expression2 = null;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public boolean interpret(String str){
        return expression1.interpret(str) && expression2.interpret(str);
    }
}

public class OnExpression extends Expression {
    private Expression expression1 = null;
    private Expression expression2 = null;

    public OrExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public boolean interpret(String str) {
        return expression1.interpret(str) || expression2.interpret(str);
    }
}

public class Client {
    
    public static Expression buildInterpreterTree() {
        Expression e1 = new TerminalExpression("A");
        Expression e2 = new TerminalExpression("B");
        Expression e3 = new TerminalExpression("C");
        Expression e4 = new TerminalExpression("D");

        Expression a1 = new OrExpression(e2, e3);
        Expression a2 = new OrExpression(e1, a1);

        return new AndExpression(e4, a2);

    }
}

```

+ `迭代器` `Iterator`：提供一种顺序访问聚合对象元素的方法，并且不暴露聚合对象的内部表示。
    + `Aggregate` 是聚合类，其中`createIterator()`方法可以产生一个`Iterator`
    + `Iterator` 主要定义了`hasNext()`和`next()`方法
    + `Client` 组合了`Aggregate`，为了迭代遍历`Aggregate`，也需要组合`Iterator`

```java

public iterface Aggregate {
    Iterator createIterator();
}

public class ConcreteAggregate implements Aggregate {
    private Integer[] items;

    public ConcreteAggregate() {
        items = new Integer[10];
        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator<Integer>(items);
    }
}

public interface Iterator<Item> {
    Item next();

    boolean hasNext();
}

public class ConcreteIterator<Item> implements Iterator {
    private Item[] items;
    private int position = 0;

    public ConcreteIterator(Item[] items) {
        this.items = items;
    }

    @Override
    public Object next() {
        return items[position++];
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }
}

public class Client {
    
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> it = aggregate.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
```

+ `中介者` `Mediator`：**集中相关对象之间复杂的沟通和控制方式**
    + `Mediator`：中介者，定义一个接口用于与各同事(`Colleague`)对象通信
    + `Colleague`：同事，相关对象

中介者模式又称为调停者模式，从类图中看，共分为3部分

1. 抽象中介者：`Medaitor`，定义好同事类对象到中介者对象的接口，用于各个同事类之间的通信。一般包括一个或几个抽象的事件方法，并由子类去实现。
2. 中介者实现类：`ConcreteMediator`，从抽象中介者继承而来，实现抽象中介者中定义的事件方法。从一个同事类接收消息，然后通过消息影响其他同事类。
3. 同事类：`Colleague`，如果一个对象会影响其他的对象，同时也会被其他对象影响，那么称这两个对象称为同事类。在类图中，同事类只有一个，这其实是现实的省略，在实际应用中，同事类一般由多个组成，它们之间相互影响，相互依赖。同事类越多，关系越复杂。在中介者模式中，**同事类之间必须通过中介者才能进行消息传递**。

> 一般来说，`同事类`之间的关系是比较复杂的，多个`同事类`之间互相关联时，他们之间的关系会呈现为复杂的`网状结构`，这是一种过度耦合的架构，不利于类的复用，也不稳定。如果引入`中介者模式`，那么`同事类`之间的关系将变为`星型结构`，任何一个类的变动，只会影响`本身`以及`中介者`，这样就减小系统的耦合。一个好的设计，必定不会把所有的对象关系处理逻辑封装在本类中，而是使用一个专门的类来管理那些不属于自己的行为。

```java

public abstract class Colleague {
    public abstract void onEvent(Mediator mediator);
}

public class Alarm extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("alarm");
    }

    public void doAlarm() {
        System.out.println("doAlarm()");
    }
}

public class CoffeePot extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("coffeePot");
    }

    public void doCoffeePot() {
        System.out.println("doCoffeePot()");
    }
}

public class Calender extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("calender");
    }

    public void doCalender() {
        System.out.println("doCalender()");
    }
}

public class Sprinkler extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("sprinkler");
    }

    public void doSprinkler() {
        System.out.println("doSprinkler()");
    }
}

public abstract class Mediator {
    public abstract void doEvent(String eventType);
}

public class ConcreteMediator extends Mediator {
    private Alarm alarm;
    private CoffeePot coffeePot;
    private Calender calender;
    private Sprinkler sprinkler;

    public ConcreteMediator(Alarm alarm, CoffeePot coffeePot, Calender calender, Sprinkler sprinkler) {
        this.alarm = alarm;
        this.coffeePot = coffeePot;
        this.calender = calender;
        this.sprinkler = sprinkler;
    }

    @Override
    public void doEvent(String eventType) {
        switch (eventType) {
            case "alarm":
                doAlarmEvent();
                break;
            case "coffeePot":
                doCoffeePotEvent();
                break;
            case "calender":
                doCalenderEvent();
                break;
            default:
                doSprinklerEvent();
        }
    }

    public void doAlarmEvent() {
        alarm.doAlarm();
        coffeePot.doCoffeePot();
        calender.doCalender();
        sprinkler.doSprinkler();
    }

    public void doCoffeePotEvent() {
        // ...
    }

    public void doCalenderEvent() {
        // ...
    }

    public void doSprinklerEvent() {
        // ...
    }
}

public class Client {
    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        CoffeePot coffeePot = new CoffeePot();
        Calender calender = new Calender();
        Sprinkler sprinkler = new Sprinkler();
        Mediator mediator = new ConcreteMediator(alarm, coffeePot, calender, sprinkler);
        // 闹钟事件到达，调用中介者就可以操作相关对象
        alarm.onEvent(mediator);
    }
}
```

+ `备忘录 Memento`：**在不违反封装的情况下获得对象的内部状态，从而在需要时可以将对象恢复到最初状态。**

1. Originator：原始对象
2. Caretaker：负责保存好备忘录
3. Memento：备忘录，存储原始对象的状态。备忘录实际上有两个接口，一个是提供给Caretaker的窄接口：它只能将备忘录传递给其他对象；一个是提供给Originator的宽接口，允许它访问到先前状态所需的所有数据。理想情况是只允许Originator访问本备忘录的内部状态。

以下实现了一个简单计算器程序，可以输入两个值，然后计算两个值的和，备忘录模式允许将这两个值存储起来，然后在某个时刻用储存的状态进行恢复。

```java
public interface Calculator {

    PreviousCalculationToCareTaker backupLastCalculation();

    void restorePreviousCalculatoin(PreviousCalculationToCareTaker memento);

    int getCalculationResult();

    void setFirstNumber(int firstNumber);

    void setSecondNumber(int secondNumber);
}

public class CalcuatorImp implemnets Calculator {

    private int firstNumber;
    private int secondNumber;

    @Override
    public PreviousCalculationToCareTaker backupLastCalculation() {
        return new PreviousCalculationImp(firstNumber, secondNumber);
    }

    @Override
    public void restorePreviousCalculation(PreviousCalculationToCareTaker memento) {
        this.firstNumber = ((PreviousCalculationToOriginator)memento).getFirstNumber();
        this.secondNumber = ((PreviousCalculationToOriginator)memento).getSecondNumber();
    }
}

public interface PreviousCalculationToCareTaker {
}

public class PreviousCalculationImp implements PreviousCalculationToCareTaker, PreviousCalculationToOriginator {
    private int firstNumber;
    private int secondNumber;

    public PreviousCalculationImp(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public int getFirstNumber() {
        return firstNumber;
    }

    @Override
    public int getSecondNumber() {
        return secondNumber;
    }
}

public class Client {
    public static void main() {
       Calculator calculator = new Calculator();
       calculator.setFirstNumber(10);
       calculator.setSecondNumber(100);

       System.out.println(calculator.getCalculationResult());

       PreviousCalculationToCareTaker memento = calculator.backupLastCalculation();
       calculator.setFirstNumber(17);
       calculator.setSecondNumber(-290);

       System.out.println(calculator.getCalculationResult());
       calculator.restorePreviousCalculation(memento);
       System.out.println(calculator.getCalculationResult());
    }
}
```

+ `观察者 Observer`：**定义对象之间的一对多依赖，当一个对象状态改变时，它的所有依赖都会收到通知并且自动更新状态。**

主题是被观察的对象，而其所有依赖者称为观察者。主题具有注册和移除观察者、并通知所有观察者的功能，主题是通过维护一张观察者列表来实现这些操作的。观察者的注册功能需要调用主题的`registerObserver`()方法。 

```java

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver(Observer o);
}

public class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeacherData() {
        observers = new ArrayList();
    }
    public void setMeasurements() {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }   
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }
    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }
}

public interface Observer {
    void update(float temp, float humidity, float pressure);
}

public class StatisticsDisplay implements Observer {
    public StatisticsDisplay(Subject weacherData) {
        weacherData.registerObserver(this);
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("StatisticsDisplay.update: "+temp+" "+humidity+" "+pressure);
    }
}

public class CurrentConditionsDisplay implements Observer {
    public CurrentConditionsDisplay(Subject weacherData) {
        weacherData.registerObserver(this);
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("CurrentConditionsDisplay.update: "+temp+" "+humidity+" "+pressure);
    }
}

public class WeacherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        
        weatherData.setMeasurements(0, 0, 0);
        weatherData.setMeasurements(1, 1, 1);
    }
}
```

+ `状态 state`： **允许对象在内部状态改变时改变它的行为，对象看起来好像修改了它所属的类。**

糖果销售机有多种状态，每种状态下销售机有不同的行为，状态可以发生转移，使得销售机的行为也发生改变。
    
```java

public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
public class GumballMachine {
    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;
    private State state;
    private int count = 0;
    
    public GumballMachine(int numberGumballs) {
        count = numberGumballs;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        if (numberGumballs > 0) {
            state = noQuarterState;
        } else {
            state = soldQuarterState;
        }
    }
    public void insertQuarter() {
        state.insertQuarter();
    }
    public void ejectQuarter() {
        state.ejectQuarter();
    }
    public void turnCrank() {
        state.turnCrank();
    }
    public void setState(State state) {
        this.state = state;
    }
    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot ...");
        if (count != 0) {
            count -= 1;
        }
    }
    public State getSoldOutState() {
        return soldOutState;
    }
    public State getNoQuarqterState() {
        return noQuarterState;
    }
    public State getHasQuarterState() {
        return hasQuarterState;
    }
    public State getSoldState() {
        return soldState;
    }
    public int getCount() {
        return count;
    }
}

public class HasQuarterState implements State {
    private GumballMachine gumballMachine;
    
    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quarter");
    }
    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballsMachine.setState(gumballMachine.getNoQuarterState());
    }
    @Override
    public void turnCrank() {
        System.out.println("You turned ...");
        gumballMachine.setState(gumballMachine.getSoldState());
    }
    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}

public class NoQuarterState implements State {
 // ...
}

public class SoldOutState implements State {
 // ...
}

public class SoldState implements State {
 // ...
}

public class Client {
    GumballMachine gumballMachine = new GumballMachine(5);
    gumballMachine.insertQuarter();
    gumballMachine.turnCrank();
    gumballMachine.insertQuarter();
}
```

+ `策略 Strategy` ： **定义一系列算法，封装每个算法，并使它们可以互换。策略模式可以让算法独立于使用它的客户端。**

1. Strategy 接口定义了一个算法族，它们都实现了behavior方法
2. Context是使用到该算法族的类，其中的doSomething方法会调用behavior，setStrategy(Strategy)方法可以动态地改变strategy对象，也就是说能动态地改变Context所使用的算法。

> 状态模式的类图和策略模式类似，并且都是能够动态改变对象的行为。但是状态模式是通过状态转移来改变Context所组合的State对象，而策略模式是通过Context本身的决策来改变组合的Strategy对象。所谓的状态转移，是指Context在运行过程中由于一些条件发生改变而使得State对象发生改变，注意必须要是在运行过程中。

> 状态模式主要是用来解决状态转移的问题，当状态发生转移了，那么Context对象就会改变它的行为；而策略模式主要是用来封装一组可以互相替代的算法族，并且可以根据需要动态地去替换Context使用的算法。

设计一个鸭子，它可以动态地改变叫声，这里的算法族是鸭子的叫声行为。

```java
public interface QuackBehavior {
    void quack();
}

public class Quack implements QuackBahavior {
    @Override
    public void quack() {
        System.out.println("quack!");
    }
}

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("squeak!");
    }
}

public class Duck {
    private QuackBehavior quackBehavior;
    public void performQuack() {
        if (quackBehavior != null) {
            quackBehavior.quack();
        }
    }
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}

public class Client {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.setQuackBehavior(new Squeak());
        duck.performQuack();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();
    }
}
```

+ `模板方法 Template Method` ：**定义算法框架，并将一些步骤的实现延迟到子类。通过模板方法，子类可以重新定义算法的某些步骤，而不用改变算法的结构。 **

冲咖啡和冲茶都有类似的流程，但是因为某些步骤会有点不一样，要求复用那些相同步骤的代码。

```java
public abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    abstract void brew();
    abstract void addCondiments();
    void boilWater() {
        System.out.println("boilWater");
    }
    void pourInCup() {
        System.out.println("pourInCup");
    }
}
```

+ `访问者 visitor` ： **为一个对象结构增加新能力**

1. Visitor：访问者，为每一个ConcreteElement声明一个visit操作
2. ConcreteVisitor：具体访问者，存储遍历过程中的累计结果
3. ObjectStructure：对象结构，可以是组合结构，或者是一个集合

```java
public interface Element {
    void accept(Visitor visitor);
}
class CustomerGroup {
    private List<Customer> customers = new ArrayList();
    void accept(Visitor visitor) {
        for (Customer customer : customers) {
            customer.accept(visitor);
        }
    }
    void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
public class Customer implements Element {
    private String name;
    private List<Order> orders = new ArrayList();
    Customer(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    void addOrder(Order order) {
        orders.add(order);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Order order : orders) {
            order.accept(visitor);
        }
    }
}
public class Order implements Element {
    private String name;
    private List<Item> items = new ArrayList();
    Order(String name) {
        this.name = name;
    }
    Order(String name, String itemName) {
        this.name = name;
        this.addItem(new Item(itemName));
    }
    String getName() {
        return name;
    }
    void addItem(Item item) {
        items.add(item);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Item item : items) {
            item.accept(visitor);
        }
    }
}
public class Item implements Element {
    private String name;
    Item(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
public interface Visitor {
    void visit(Customer customer);
    void visit(Order order);
    void visit(Item item);
}
public class GeneralReport implements Visitor {
    private int customersNo;
    private int ordersNo;
    private int itemsNo;
    public void visit(Customer customer) {
        System.out.println(customer.getName());
        customersNo++;
    }
    public void visit(Order order) {
        System.out.println(order.getName());
        ordersNo++;
    }
    public void visit(Item item) {
        System.out.println(item.getName());
        itemsNo++;
    }
    public void displayResults() {
        System.out.println(customersNo);
        System.out.println(itemsNo);
        System.out.println(ordersNo);
    }
}

public class Client {
    public static void main(String[] args) {
        Customer customer1 = new Customer("customer1");
        customer1.addOrder(new Order("order1", "item1"));
        customer1.addOrder(new Order("order2", "item1"));
        customer1.addOrder(new Order("order3", "item1"));
        
        Order order = new Order("order_a");
        order.addItem(new Item("item_a1"));
        order.addItem(new Item("item_a2"));
        order.addItem(new Item("item_a3"));
        
        Customer customer2 = new Customer("customer2");
        customer2.addOrder(order);
        
        CustomerGroup customers = new CustomerGroup();
        customers.addCustomer(customer1);
        customers.addCustomer(customer2);
    
        GeneralReport visitor = new GeneralReport();
        customers.accept(visitor);
        visitor.displayResults();
    }
}
```

#### 结构型

+ `适配器` `Adapter` ：**把一个类接口转换称另一个用户需要到接口。**

鸭子和火鸡拥有两种不同的叫声，鸭子的叫声调用quack方法，而火鸡调用gobble方法。要求将火鸡的gobble方法适配成鸭子的quack方法，从而让火鸡冒充鸭子

```java
public interface Duck {
    void quack();
}
public interface Turkey {
    void gobble();
}
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble!");
    }
}
public class TurkeyAdapter implements Duck {
    Turkey turkey;
    
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
    @Override
    public void quack() {
        turkey.gobble();
    }
}
public class Client {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck=  new TurkeyAdapter(turkey);
        duck.quack();
    }
}
```

+ `桥接` `Bridge`：将抽象与实现分离开来，使他们可以独立变化

1. `Abstraction` : 定义抽象类的接口
2. `Implementor` ： 定义实现类的接口

RemoteControl表示遥控器，指代Abstraction。TV表示电视，指代Implementor。桥接模式将遥控器和电视分离开来，从而可以独立改变遥控器或者电视的实现。

```java
public abstract class TV {
    public abstract void on();
    public abstract void off();
    public abstract void tuneChannel();
}
public class Sony extends TV {
    @Override
    public void on() {
       System.out.println("Sony.on()");
    }
    @Override
    public void off() {
        System.out.println("Sony.off()");
    }
    @Override   
    public void tuneChannel() {
        System.out.println("Sony.tuneChannel()");
    }
}
```

+ `组合` `Composite` ：**将对象组合成树形结构来表示"整体/部分"层次关系，允许用户以相同的方式处理单独对象和组合对象。**

组件类是组合类和叶子类的父类，可以把组合类看成是树的中间节点。组合对象拥有一个或者多个组件对象，因此组合对象的操作可以委托给组件对象去处理，而组件对象可以是另一个组合对象或者叶子对象。

+ `装饰` `Decorator` ： **为对象动态添加功能**

装饰者(`Decorator`)和具体组件(`ConcreteComponent`)都继承自组件(`Component`)，具体组件的方法实现不需要依赖于其他对象，而装饰者组合了一个组件，这样它可以装饰其他装饰者或者具体组件。所谓装饰，就是把这个装饰者套在被装饰者之上，从而动态扩展被装饰者的功能。装饰者的方法有一部分是自己的，这属于它的功能，然后调用被装饰者的方法实现，从而也保留了被装饰者的功能。可以看到，具体组件应当是装饰层次的最底层，因为只有具体组件的方法实现不需要依赖于其他对象。

> 设计原则：类应该对扩展开放，对修改关闭。也就是添加新功能时不需要修改代码。饮料可以动态添加新的配料，而不需要去修改饮料的代码。

+ `门面` `Facade` ：**提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用。**

> 设计原则：只和你的密友谈话，也就是说客户对象所需要交互的对象应当尽可能的少。

+ `享元` `Flyweight` ：**利用共享的方式来支持大量细粒度的对象，这些对象一部分内部状态是相同的。**

1. Flyweight : 享元对象
2. IntrinsicState : 内部状态，享元对象共享内部状态
3. ExtrinsicState : 外部状态，每个享元对象的外部状态不同

+ `代理` `Proxy` : **控制对其他对象的访问**

1. 远程代理 Remote Proxy : 控制对远程对象的访问，它负责将请求及其参数进行编码，并向不同地址空间中的对象发送已经编码的请求。
2. 虚拟代理 Virtual Proxy : 根据需要创建开销很大的对象，它可以缓存实体的附加信息，以便延迟对它的访问，例如在网站加载一个很大图片时，不能马上完成，可以用虚拟代理缓存图片的大小信息，然后生成一张临时图片代替原始图片。
3. 保护代理 Protection Proxy : 按权限控制对象的访问，它负责检查调用者是否具有实现一个请求所必须的访问权限。
4. 智能代理 Smart Proxy : 取代了简单的指针，它在访问对象时执行一些附加操作；记录对象的引用次数，当第一次引用一个对象时，将它装入内存；在访问一个实际对象前，检查是否已经锁定了它，以确保其他对象不能改变它。

### 设计原则

+ 单一责任原则

> 修改一个类的原因应该只有一个

换句话说就是让一个类只负责一件事，当这个类需要做过多事情时，就需要分解这个类。如果一个类承担的指责过多，就等于把这些职责耦合在一起，一个职责的变化可能会削弱这个类完成其他职责的能力。

+ 开放封闭原则

> 类应该对扩展开放，对修改关闭

扩展就是添加新功能的意思，因此该原则要求在添加新功能时不需要修改代码。符合开闭原则最典型的设计模式就是`装饰者模式`，它可以动态地将责任附加到对象上，而不用去修改类的代码。

+ 里氏替换原则

> 子类对象必须能够替换掉所有父类对象

继承是一种`IS-A`关系，子类需要能够当成父类来使用，并且需要比父类更特殊。如果不满足这个原则，那么各个子类的行为上就会有很大差异，增加继承体系的复杂度。

+ 接口分离原则

> 不应该强迫客户依赖于它们不用的方法

因此使用多个专门的接口比使用单一的总接口要好

+ 依赖倒置原则

> 高层模块不应该依赖于底层模块，二者都应该依赖于抽象；抽象不应该依赖于细节，细节应该依赖于抽象。

高层模块包含一个应用程序中重要的策略选择和业务模块，如果高层模块依赖于底层模块，那么底层模块的改动就会直接影响到高层模块，从而迫使高层模块也需要改动。

依赖于抽象意味着：

1. 任何变量都不应该有一个指向具体类的指针或者引用
2. 任何类都不应该从具体类派生
3. 任何方法都不应该覆写它的任何基类中的已经实现的方法

+ 迪米特法则

> LKP，最少知识原则，一个对象应当对其他对象有尽可能少的了解，不和陌生人说话。

+ 合成复用原则

> 尽量使用对象组合，而不是通过继承来达到复用的目的

+ 共同封闭原则

> 一起修改的类，应该组合在一起(同一个包里)。如果必须修改应用程序的代码，我们希望所有的修改都发生在一个包里(修改关闭)，而不是遍布在很多包里。

+ 稳定抽象原则

> 最稳定的包应该是最抽象的包，不稳定的包应该是具体的包，即包的抽象程度跟他的稳定性成正比

+ 稳定依赖原则

> 包之间的依赖关系都应该是稳定方向依赖的，包要依赖的包要比自己更具有稳定性

### 分布式锁

阻塞锁通常使用互斥量来实现：

+ 互斥量为0表示有其他进程在使用锁，此时处于锁定状态
+ 互斥量为1表示未锁定状态

#### 数据库的唯一索引

> 获得锁时向表中插入一条记录，释放锁时删除这条记录。唯一索引可以保证该记录只被插入一次，那么就可以用这个记录是否存在来判断是否处于锁定状态。

存在以下几个问题

+ 锁没有失效时间，解锁失败的话其他进程无法再获得该锁
+ 只能是非阻塞锁，插入失败直接就报错了，无法重试
+ 不可重入，已经获得锁的进程也必须重新获取锁

#### Redis的SETNX指令

使用`SETNX`指令插入一个键值对，如果`key`已经存在，那么会返回`false`，否则插入成功并返回`true`。`SETNX`指令和数据库唯一索引类似，保证了只存在一个key的键值对，那么可以用一个key的键值对是否存在来判断是否处于锁定状态。expire指令可以为一个键值对设置一个过期时间，从而避免了数据库唯一索引实现方式中释放锁失败的问题。 

> expire指令可以为一个键值对设置一个过期时间，避免了数据库唯一索引实现方式中释放锁失败的问题

#### Redis的RedLock算法

使用了多个Redis实例来实现分布式锁，这是为了保证在发生单点故障时仍然可用。

+ 尝试从N个互相独立redis实例获取锁
+ 计算获取锁消耗的时间，只有当这个时间小于锁的过期时间，并且从大多数(N/2 + 1)实例上获取了锁，那么就认为锁获取成功了。
+ 如果锁获取失败，就到每个实例上释放锁

#### Zookeeper的有序节点

+ Zookeeper抽象模型

Zookeeper提供了一种树形结构的命名空间，`/app1/p_1`节点的父节点为`/app1`

+ 节点类型
    + 永久节点 不会因为会话结束或者超时而消失
    + 临时节点 如果会话结束或者超时就会消失
    + 有序节点 会在节点名的后面加一个数字后缀，并且是有序的，例如生成的有序节点是`/lock/node-0000000`，它的下一个有序节点则为`/lock/node-0000001`，以此类推 
    
+ 监听器
为一个节点注册监听器，在节点状态发生改变时，会给客户端发送消息

+ 分布式锁实现
    + 创建一个锁目录 /lock
    + 当一个客户端需要获取锁时，在/lock下创建临时的且有序的子节点
    + 客户端获取/lock下的子节点列表，判断自己创建的子节点是否为当前子节点列表中序号最小的子节点，如果是则认为获得锁；否则监听自己的前一个子节点，获得子节点的变更通知后重复此步骤直至获得锁
    + 执行业务代码，完成后，删除对应的子节点
    
    
### 分布式事务

指事务的操作位于不同的节点上，需要保证事务的ACID特性

> 例如下单场景，库存和订单如果不在同一个节点上，就涉及分布式事务

#### 2PC

两阶段提交，通过引入协调者来协调参与者的行为，并最终决定这些参与者是否要真正执行事务

+ 运行过程
    + 准备阶段
        
        + 协调者询问参与者事务是否执行成功，参与者发回事务执行结果
    + 提交阶段
        + 如果事务在每个参与者上都执行成功，事务协调者发送通知让参与者提交事务；否则，协调者发送通知让参与者回滚事务
        > 需要注意的是，在准备阶段，参与者执行了事务，但是还未提交，只有在提交阶段接收到协调者发来的通知后，才进行提交或者回滚。
+ 存在的问题
    + `同步阻塞`
        + 所有事务参与者在等待其他参与者响应的时候都处于同步阻塞状态，无法进行其他操作
    + `单点问题`
        + 协调者在2PC中起到非常大的作用，发生故障将会造成非常大的影响，特别是在阶段二发生故障，所有参与者会一直等待，无法完成其他操作。                                                                               
    + `数据不一致`
        + 在阶段二，如果协调者只发送了部分commit消息，此时网络发生异常，那么只有部分参与者接收到commit消息，也就是说只有部分参与者提交了事务，使得系统数据不一致。
    + `太过保守`
        + 任意一个节点失败就会导致整个事务失败，没有完善的容错机制
+ 本次消息表
    + 在分布式事务操作的一方完成写业务数据的操作之后向本地消息表发送一个消息，本地事务能保证这个消息一定会被写入本地消息表中
    + 之后将本地消息表中的消息转发到消息队列中，如果转发成功则将消息从本地消息表中删除，否则继续重新转发
    + 在分布式事务操作的另一方从消息队列中读取一个消息，并执行消息中的操作
    
> 本地消息表与业务数据表处于同一个数据库中，这样就能利用本地事务来保证在对这两个表的操作满足事务特性，并且使用了消息队列来保证最终一致性。

### CAP

分布式系统不可能同时满足`一致性 (Consistency)`、`可用性 (Availability)`、和`分区容忍性 (Partition Tolerance)`，最多只能同时满足其中两项

+ 一致性
一致性指的是`多个数据副本是否能保持一致的特性`，在一致性的条件下，系统在执行数据更新操作之后能够从一致性转移到另一个一致性状态。 

> 对系统的一个数据更新成功之后，如果所有用户都能够读取到最新的值，该系统就被认为具有强一致性。

+ 可用性
可用性指`分布式系统在面对各种异常时可以提供正常服务的能力`，可以用系统可用时间占总时间的比值来衡量，4个9的可用性表示系统`99.99%`的时间是可用的。 

> 在可用性条件下，要求系统提供的服务一直处于可用的状态，对于用户的每一个操作请求总是能够在有限的时间内返回结果

+ 分区容忍性
网络分区指分布式系统中的节点被划分为多个区域，每个区域内部可以通信，但是区域之间无法通信。

在分区容忍性条件下，`分布式系统在遇到任何网络分区故障的时候，仍然需要能对外提供一致性和可用性的服务`，除非是整个网络环境都发生了故障。 

> 在分布式系统中，分区容忍性必不可少，因为需要总是假设网络是不可靠的，因此，CAP理论实际上是要在C和A之间做权衡。可用性和一致性往往是冲突的，很难使他们同时满足。在多个节点之间进行数据同步时
> + 为了保证`一致性(CP)`，不能访问未同步完成的节点，也就失去了部分可用性
> + 为了保证`可用性(AP)`，允许读取所有节点的数据，但是数据可能不一致

### 线程

+ 线程状态转换
    + `新建`：创建后尚未启动
    + `可运行`：可能正在运行，也可能正在等待CPU时间片 (`Running/Ready`)
    + `阻塞`：等待获取一个拍它锁，如果其线程释放了锁就会结束此状态
    + `无限期等待`：等待其他线程显式地唤醒，否则不会分配CPU时间片
    + `限期等待`：无需等待其他线程显式的唤醒，在一定时间之后会被系统自动唤醒
    > + 调用`Thread.sleep()`方法使线程进入限期等待状态时，常常用"`使一个线程睡眠`"进行描述
    > + 调用`Object.wait()`方法使线程进入限期等待或者无限等待状态，常常用"`挂起一个线程`"进行描述                                                                                                                                                                                             
    > + `睡眠`和`挂起`用来描述行为，`阻塞`和`等待`用来描述状态。`阻塞`是被动的，等待获取一个排他锁，`等待`是主动的，通过调用Thread.sleep()和Object.wait()等方法进入                                                                                                                                                                                             
    
    > + `wait` : 调用wait之前，线程必须获得该对象的锁，因此只能在同步方法/同步代码块中调用wait方法                                                                                                                                                                                                         
    > + `notify` : 和wait一样，notify也要在同步方法/同步代码块中调用。notify的作用是，如果有多个线程等待，那么线程规划器随机挑选出一个wait的线程，对其发出通知，并使它等待获取该对象的对象锁，注意，即使收到了通知，wait的线程也不会马上获取对象锁，必须等待notify方法的线程释放锁才可以。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

|进入方法|退出方法|
|:---:|:---:|
|Thread.sleep()方法|时间结束|
|设置了Timeout参数的Object.wait()方法|时间结束/Object.notify()/Object.notifyAll()|
|设置了Timeout参数的Thread.join()方法|时间结束/被调用的线程执行完毕|

+ interrupted

如果一个线程的run方法执行一个无限循环，并且没有执行sleep等会抛出`InterruptedException`的操作，那么调用线程的interrupt方法就无法使线程提前结束。但是调用interrupt方法会设置线程的中断标记，此时调用interrupted方法会返回true，因此可以在循环体中使用`interrupted()`方法来判断线程是否处于中断状态，从而提前结束线程。

```java
public class InterruptExample {
    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
                // ..
            }
            System.out.println("Thread end");
        }
    }
}
```

+ 互斥同步

Java提供了两种锁机制来控制多个线程对共享资源的互斥访问，第一个是`JVM`实现的`synchronized`，另一个是`JDK`实现的`ReentrantLock`。 

两种同步机制的比较

+ 锁的实现

`synchronized`基于`JVM`实现，`ReentrantLock`基于`JDK`实现

+ 等待可中断

当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。

`ReentrantLock`可中断，`synchronized`不行

+ 公平锁

公平锁是指多个线程在等待同一个锁的时候，必须按照申请锁的时间顺序来一次获取锁。
`synchronized`中的锁是非公平的，`ReentrantLock`默认情况下也是非公平，但是也可以是公平的。

+ 锁绑定多个条件

一个`ReentrantLock`可以同时绑定多个`Condition`对象

> 除非需要使用`ReentrantLock`的高级功能，否则优先使用`synchronized`。这是因为`synchronized`是`JVM`实现的一种锁机制，`JVM`原生的支持它，而`ReentrantLock`不是所有的JDK版本都支持。并且使用`synchronized`不用担心没有释放锁而导致死锁问题，因为`JVM`会确保锁的释放。 

线程之间的协作

+ join

在线程中调用另一个线程的join方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。

+ wait/notify/notifyAll

调用wait使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其他线程会调用notify()或者notifyAll()来唤醒挂起的线程。

> `wait`是`Object`的方法，`sleep`是`Thread`的静态方法。`wait`会释放锁，`sleep`不会

+ await/signal/signalAll

`JUC` (`java.util.concurrent`) 类库中提供了Condition类来实现线程之间的协调，可以在Condition上调用await方法使线程等待，其他线程调用signal或者signalAll方法唤醒等待的线程。

> 相比wait这种等待方式，await可以指定等待的条件，因此更加灵活。

多线程开发良好的实践

+ 给线程起个有意义的名字，方便找BUG
+ 缩小同步范围，减少锁争用。如`synchronized`，应尽可能使用`同步块`而不是`同步方法`
+ 多用同步工具，少用`wait`和`notify`。`CountDownLatch`、`CyclicBarrier`，`Semaphore`和`Exchanger`这些同步类简化了编码操作，而用`wait`和`notify`很难实现复杂控制流。
+ 使用`BlockingQueue`实现生产者消费者问题
+ 多用`并发集合`，少用`同步集合`
+ 使用`本地变量`和`不可变类`来保证线程安全
+ 使用线程池而不是直接创建线程

### Redis

|数据类型|可以储存的值|操作|
|:---:|:---:|:---:|
|String|字符串、整数或者浮点数|对整个字符串或者字符串的其中一部分执行操作，对整数和浮点数执行自增或者自减操作|
|List|列表|从两端压入或者弹出元素，对单个或者多个元素进行修建，只保留一个范围内的元素|
|Set|无序集合|添加、获取、移除单个元素，检查一个元素是否存在于集合中，计算交集、并集、差集，从集合中随机获取元素|
|Hash|包含键值对的无序散列表|添加、获取、移除单个键值对，获取所有键值对，检查某个键是否存在|
|ZSet|有序集合|添加、获取、删除元素、根据分值范围或者成员来获取元素，计算一个键排名|

### DML/DDL/DCL

+ `DML` (Data Manipulation Language)

`SELECT`、`UPDATE`、`INSERT`、`DELETE`、`CALL`、`EXPLAIN PLAN`，对数据库的数据进行操作的语言

+ `DDL` (Data Definition Language)

`CREATE`、`ALTER`、`DROP`、`TRUNCATE`等，主要用在定义或改变表结构、数据类型、表之间的联系和约束等初始化工作

> + `TRUNCATE TABLE`比`DELETE`速度快。因为`DELETE`每删除一行，都在事务日志中为所删除的每行记录一项。`TRUNCATE TABLE`通过释放存储表数据所用的数据页来删除数据，并且只在事务日志中记录页的释放 
> + `TRUNCATE TABLE`后，新行标识所用的计数值充值为该列的种子。如果想保留标识计数值，使用`DELETE`
> + 对于有`FOREIGN KEY`约束引用的表，不能使用`TRUNCATE TABLE`，而应使用不带`WHERE`子句的`DELETE`语句，由于`TRUNCATE TABLE`不记录在日志中，所以它不能激活触发器。

+ `DCL` (Data Control Language)

`GRANT`、`DENY`、`REVOKE`等，数据库控制功能。用来设置或更改数据库用户或角色权限的语句

### MYSQL索引


+ 独立的列

进行查询时，索引的列不能是表达式的一部分，也不能是函数的参数，否则无法使用索引

```mysql
SELECT actor_id FROM sakila.actor WHERE actor_id + 1 = 5;
```

+ 多列索引
需要使用多个列作为条件进行查询时，使用多列索引比使用多个单列索引性能更好。

+ 索引列的顺序

让选择性最强的索引放在前面。

> 索引的选择性：不重复的索引值和记录总数的比值，最大为1，此时每个记录都有唯一的索引与其对应。

+ 前缀索引

对于`BLOB`、`TEXT`和`VARCHAR`类型的列，必须使用前缀索引，只索引开始的部分字符。

```mysql
ALTER TABLE table_name ADD KEY(column_name(prefix_length))
```

+ 覆盖索引

索引包含所有需要查询的字段的值

### InnoDB 与 MyISAM

|对比项|InnoDB|MyISAM|
|:---:|:---:|:---:|
|事务|事务型，可使用commit和rollback语句|不支持|
|并发|支持表级锁、行级锁|只支持表级锁|
|外键|支持|不支持|
|其他||支持压缩表和空间数据索引|

### SQL事务

+ `原子性` Atomicity
事务被视为不可分割的最小单元，事务的所有操作要么全部提交成功，要么全部失败回滚。

+ `一致性` Consistency
数据库在事务执行前后都保持一致性状态。在一致性状态下，所有事务对一个数据的读取结果都是相同的。

+ `隔离性` Isolation
一个事务所做的修改在最终提交前，对其他事务是不可见的。

+ `持久性` Durability
一旦事务提交，则其所做的修改将会永远保存到数据库中。即使系统发生崩溃，事务执行的结果也不能丢失。使用重做日志来保证持久性。

**事务的ACID特性概念简单，但不是很好理解，主要是因为这几个特性不是一种平级关系**

+ 只有满足`一致性`，事务的执行结果才是正确的
+ 在无并发的情况下，事务串行执行，隔离性一定能够满足。此时只要能满足原子性，就一定能满足一致性
+ 在并发的情况下，多个事务并行执行，事务不仅要满足原子性，还需要满足隔离性，才能满足一致性
+ 事务满足持久性是为了能应对数据库崩溃的情况

#### 并发一致性问题

+ 丢失更改

T1和T2两个事务都对一个数据进行修改，T1先修改，T2随后修改，T2的修改覆盖了T1的修改

+ 读脏数据

T1修改一个数据，T2随后读取这个数据。如果T1撤销了这次修改，那么T2读取的数据是脏数据

+ 不可重复读

T2读取一个数据，T1对该数据做了修改。如果T2再次读取这个数据，此时读取的结果和第一次读取的结果不同

+ 幻影读

T1读取某个范围的数据，T2在这个范围内插入新的数据，T1再次读取这个范围的数据，此时读取的结果和第一次结果不同

> 产生并发不一致性问题主要原因是破坏了`事务的隔离性`，解决方法是通过`并发控制`来保证隔离性。并发控制可以通过封锁来实现，但是封锁操作需要用户自己控制，相当复杂。数据库管理系统提供了事务的隔离级别，让用户以一种更轻松的方式处理并发一致性的问题。

#### 隔离级别

+ 未提交读 READ UNCOMMITTED

事务中的修改，即使没有提交，对其他事务也是可见的

+ 提交读 READ COMMITTED

一个事务只能读取已经提交的事务所做的修改。换句话说，一个事务所做的修改在提交之前对其他事务是不可见的

+ 可重复读 REPEATABLE READ

保证在同一个事务中多次读取同样数据的结果是一样的

+ 可串行化

强制事务串行执行。

> 需要加锁实现，而其他隔离级别通常不需要。

|隔离级别|脏读|不可重复读|幻影读|
|:---:|:---:|:---:|:---:|
|未提交读|Y|Y|Y|
|提交读|N|Y|Y|
|可重复读|N|N|Y|
|可串行化|N|N|N|

### SQL范式

+ 第一范式
属性不可分

+ 第二范式
每个`非主属性`完全函数依赖于`候选主键`，可以通过分解来满足。

+ 第三范式
在`第二范式`的基础上，非主属性不`传递函数`依赖于候选主键。

+ BC范式

只要属性或属性组A能够决定任何一个属性B，则A的子集中必须有候选主键。BC范式排除了任何属性对候选主键的传递依赖与部分依赖。


### 类加载机制

+ 加载
    + 通过类的完全限定名称获取定义该类的二进制字节流
    + 将该字节流表示的静态存储结构转换为方法区的运行时储存结构
    + 在内存中生成一个代表该类的Class对象，作为方法区中该类各种数据的访问入口
+ 验证
    + 确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全
+ 准备
    + 类变量是被static修饰的变量，准备阶段为类变量分配内存并设置初始值，使用的是方法区的内存
    + 实例变量不会在这阶段分配内存
    > 实例化不是类加载的一个过程，类加载发生在所有实例化操作之前，并且类加载只进行一次，实例化可以进行多次
+ 解析
    + 将常量池的符号引用替换为直接引用的过程
    > 解析过程在某些情况下可以在初始化阶段之后再开始，这是为了支持Java的动态绑定
+ 初始化
    + 初始化阶段才真正开始执行类中定义的Java程序代码。


### Java的GC

判断一个对象是否可被回收

+ 引用计数算法

为对象添加一个引用计数器，当对象增加一个引用时，计数器加1，引用失效时计数器减1。引用计数为0的对象可被回收

> 在两个对象出现循环引用的情况下，此时引用计数器永远不为0，导致无法对他们进行回收。正是因为循环引用的存在，因此Java虚拟机不再使用计数算法

+ 可达性算法

以GC Roots为起始点进行搜索，可达的对象都是存活的，不可达的对象可被回收。Java虚拟机使用该算法来判断对象是否可回收，GC Roots一般包含以下内容

+ 虚拟机栈中局部变量表中引用的对象
+ 本地方法栈中JNI引用的对象
+ 方法区中类静态属性引用的对象
+ 方法区中常量引用的对象

+ finalize()

用于关闭外部资源，但是try-finally等方式可以做的更好，并且该方法运行代价很高，不确定性大，无法保证各个对象的调用顺序，因此最好不要使用。

> 当一个对象可被回收时，如果需要执行该对象的finalize方法，那么就有可能在该方法中让对象重新被引用，从而实现自救。自救只能进行一次，如果回收的对象之前调用了finalize方法自救，后面回收时不会再调用该方法。

#### 引用类型

+ 强引用

被强引用关联的对象不会被回收。使用new一个新对象的方式来创建强引用

+ 软引用

被软引用关联的对象只有在内存不够的情况下才会被回收。使用SoftReference类来创建软引用。

```java
Object obj = new Object();
SoftReference<Object> sf = new SoftReference<Object>(obj);
obj = null;  // 使对象只被软引用关联
```

+ 弱引用
被若引用关联的对象一定会被回收，也就是说它只能存活到下一次垃圾回收发生之前。使用WeakReference类来创建弱引用。

```java
Object obj = new Object();
WeakReference<Object> wf = new WeakReference<Object>(obj);
obj = null;
```

+ 虚引用

一个对象是否有虚引用的存在，不会对其生存时间造成影响，也无法通过虚引用得到一个对象。为一个对象设置虚引用的唯一目的是能在这个对象被回收时收到系统的一个通知。使用PhantomReference来创建虚引用。

```java
Object obj = new Object();
PhantomReference pf = new PhantomReference<Object>(obj, null);
obj = null;
```

### session管理

+ `Sticky Session`

需要配置负载均衡，使得一个用户的所有请求都路由到同一个服务器，这样就可以把用户的sessin存放在该服务器中。

> 缺点：当服务器宕机时，将丢失该服务器上的所有session

+ `Session Replication`

在服务器之间进行Session同步操作，每个服务器都有所有用户的Session信息，因此用户可以向任何一个服务器进行请求

> 缺点：占用内存过多。同步过程占用网络带宽以及服务器处理时间

+ `Session Server`

使用一个单独的服务器储存Session数据，可以使用传统的`MySQL`，也可以使用`Redis`或者`Memcached`这种内存型数据库。

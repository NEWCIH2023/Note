
## Servlet

### 介绍

Servlet是一个接口，有三个重要方法

+ void init(ServletConfig var) throws ServletException
	Servlet初始化容器执行的方法

+ void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
	执行具体的逻辑

+ void destroy();
	当Servlet服务器正常关闭时，执行destroy方法，只执行一次

HttpServlet是抽象类，继承自GenericServlet，两个关键方法

+ protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException;

+ protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException;

### 生命周期

+ Servlet容器，如Tomcat首先加载`Servlet类`
+ Servlet的实例化，即创建Servlet对象，执行Servlet的`构造函数`
+ 执行Servlet容器的初始化`init()`方法，`注意这个方法只会执行一次`
+ 执行`service()`执行具体的业务逻辑，处理客户端发过来的请求，实际上是执行我们熟悉的`doGet()`或`doPost()`方法
+ 处理完具体的逻辑后，进行`Servlet容器`的销毁，调用`destroy()`，并且销毁对应的线程

### 执行流程

+ 客户端浏览器向服务器发起一个请求
+ 服务器接收到这个请求，判断请求url是否满足过滤器配置的过滤条件，如果属于servlet拦截条件，则会去寻找目标servlet
+ 装载并创建该servlet的一个实例对象
+ 调用Servlet实例对象的init()方法完成Servlet初始化
+ 创建一个用于封装HTTP请求消息的HttpServletRequest对象和一个代表HTTP响应消息的HttpServletResponse对象，然后调用servlet的service()方法并将请求和响应对象作为参数传递进去
+ 根据客户端发器的是get还是post请求，执行对应的doGet()或者doPost()方法
+ 执行完service()方法，将结果返回给客户端
+ 销毁对应线程，注意每个线程一旦执行完任务，就被销毁或放在线程池中等待回收。

> Servlet对象是用户第一次访问时创建，对象创建之后就驻留在内存里面，响应后续的请求。Servlet对象一旦被创建，init()方法就会被执行，客户端每次请求导致service()方法被执行。Servlet对象被摧毁时(Web服务器停止后或者Web应用从服务器里删除时)，destory()方法就会被执行。



[TOC]

# 集合

## Collections

## Map

+ 永远不要将可变对象类型用作HashMap中的键，因为hashCode()可能会随着变动，导致get出来为null

## ConcurrentMap

当一个Map被多个线程访问时，通常使用`containsKey()`或者`get()`来查看给定键是否在存储键值对之前出现。但是即使有一个同步的Map，线程还是可以在这个过程中潜入，然后夺取对Map的控制权。问题是，在对put()的调用中，锁在get()开始时获取，然后在可以再次获取锁之前释放。它的结果是个竞争条件：`这是两个线程之间的竞争，结果也会因谁先运行而不同`。

## HashMap工作原理

### 重要参数

+ loadFactor
  负载因子，会影响到HashMap的扩容，负载因子越大，HashMap的碰撞越剧烈，但是resize越少；负载因子越少，HashMap碰撞概率越小，但是resize越多。

+ threshold
  要扩容的阈值。当容量大小等于这个阈值的时候，HashMap就会扩容 

### 重要方法

+ `put`
  + 取Key的hashCode
  + 判断HashMap里面的槽是不是空的，如果是空的就需要初始化HashMap里面槽的数量
  + 判断这个Kay所对应的槽是否被占用，如果没有被占用，就将Key-Value生成一个新的节点，放到对应的槽中
  + 如果这个槽被占用了，分成三步判断：
    + 判断Key是否相同，如果相同则返回原有的Node，如果不相同则进行下面的判断
    + 判断节点是否属于树节点（TreeNode），如果属于，则添加到红黑树中
    + 如果不是树节点，则一定是链表的Node，遍历链表，如果链表长度大于等于7了，则将链表转换成为红黑树，否则添加到链表中
  + 最后判断HashMap的大小是否大于阈值，如果大于，则进行扩容

+ `resize`
  + 判断当前的table里面的size是否大于0，如果大于0的话，就会判断当前的table里面的size是否超过了最大值，如果超过最大值，就不会再扩容，如果没有超过的话，就会将原有的size扩大到原来的两倍，并且判断扩容之后的size是否大于最大值，如果超过最大值就按照最大值来扩容。
  + 如果当前table里面的size等于0的话，并且当前table里面的阈值大于0的时候，就会将原有的阈值设置为新的table的size大小
  + 如果两个条件都不满足的话，则会对新的table设置默认的size(16)，和默认的阈值(16 * 0.75)
  + 如果这个时候新的table的阈值为空，则会重新计算新的阈值

> HashMap的容量始终为2的幂次。在扩容的时候，元素的位置要么是在原位置，要么是否原位置再移动2次幂的位置

+ `get`
  + 判断现在HashMap里面的table是否为空，以及要获取的key对应的槽是否为空，如果为空，就直接返回null
  + 如果都不为空，就判断槽里面的第一个node是不是想要找的key，如果是直接返回
  + 如果第一个不是，就判断node节点是不是树节点，如果是，就直接去红黑树里面查找
  + 如果也不是树节点，那就在链表里面循环查找

### 与JDK7对比

+ 插入链表的时候，在JDK7的时候，HashMap插入链表是采用头插法，而在JDK8使用的是尾插法，之所以这么改变的原因是因为，头插法的链表在HashMap的resize()过程中可能因为多线程导致的逆序，让链表形成死循环。
+ 在JDK7的HashMap中，HashMap的数据结构是数组+单向链表，在JDK8的HashMap中，采用的是数组+单链表+红黑树的数据结构
+ 在resiez过程中，JDK7和JDK8的差别主要是在迁移时计算新的索引的位置。JDK7是重新计算Key的hash值，然后用（size-1） & hash得到新的索引位置，而JDK8时，是采用判断高一个bit位的位值，如果高一位的位值是0，那么索引位置就不变，如果是1那么就用原来的HashMap的size大小加上原有的索引位置（原索引+oldCap），这么改变是为了降低rehash带来的开销

### 细节总结

+ 在HashMap中链表什么时候会转换成红黑树？
  链表在大于等于7个的时候

### 结构体系

+ HashMap结构实际上是由`数组`+`链表`+`红黑树`组成

# 序列化

java对象序列化是JDK1.1中引入的一组开创性特性之一，用于作为一种将Java对象的状态转换为字节数组，以便存储或传输的机制，以后，仍可以将字节数组转换回Java对象原有的状态。

### 序列化允许重构

序列化允许一定数量的变种泪，甚至重构之后也是如此，（新旧类保持同一个`序列化版本hash`，即`private static final serialVersionUID`字段）。`ObjectInputStream`仍可以很好的将其读出来。Java Object Serialization规范可以自动管理的关键任务是

+ 将`新字段`添加到类
+ 将字段从`static`改为`非static`
+ 将字段从`transient`改为`非transient`

### 序列化并不安全

序列化二进制格式完全编写在文档中，并且完全可逆。实际上，只需将二进制序列化流的内容转储到控制台，就足以看清类是什么样子。好在序列化允许`hook`序列化过程。可以通过在`Serializable对象`上提供一个`writeObject`方法来做到这一点。

```java
public class Person implements java.io.Serializable {
  private int age;
  // 这两个方法不是override，要求方法和参数与下面要求一致
  private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
    age = age << 2;
    stream.defaultWriteObject();
  }
  
  private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
    stream.defaultReadObject();
    age = age << 2;
  }
}
```

### 序列化的数据可以被签名和密封

可以通过`writeObject`和`readObject`实现密码加密和签名管理，但其实还有更好的方式。如果需要对整个对象进行加密和签名，最简单的是将它放在一个`javax.crypto.SealedObject`和`java.security.SignedObject`包装器中。两者都是可序列化的，所以将对象包装在`SealedObject`中可以围绕原对象创建一种“包装盒”。必须有对称密钥才能解密，而且密钥必须单独管理。同样，也可以将`SignedObject`用于数据验证，并且对称密钥也必须单独管理。

### 序列化允许将代理放在流中

很多情况下，类中包含一个核心数据元素，通过它可以派生或找到类中的其他字段。在此情况下，没有必要序列化整个对象。可以将字段标记为transient，但是每当有方法访问一个字段时，类仍然必须显式地产生代码来检查它是否被初始化。



如果首要问题是序列化，那么最好指定一个flyweight或代理放在流中。为原始person提供一个writeReplace方法，可以序列化不同类型的对象来代替它。类似地，如果反序列化期间发现一个readResolve方法，那么将调用方法，将替代对象提供给调用者。



```java
class PersonProxy implements java.io.Serializable {
  public String data;
  
  private Object readResolve() throws java.io.ObjectStreamException {
    String[] pieces = data.split(",");
    Person result = new Person(...);
    // do something
    return result;
  }
}

public class Person implements java.io.Serializable {
  // 
}
```



**注意，PersonProxy必须跟踪Person的所有数据。这通常意味着代理需要是Person的一个内部类，以便能访问private字段。有时候，代理还需要追踪其他对象引用并手动序列化他们。**



> 这种技巧是少数几种不需要读/写平衡的技巧之一。例如，一个类被重构成另一种类型后的版本可以提供一个readResolve方法，以便静默地将序列化的对象转换成新类型。类似地，它可以采用writeReplace方法将旧类序列化成新版本。



### 信任，但要验证

对于序列化的对象，这意味着验证字段，以确保在反序列化之后，它们仍具有正确的值。为此，可以实现`ObjectInputValidation`接口，并覆盖`validateObject`()方法，如果调用该方法时发现某处有错误，则抛出一个`InvalidObjectException`。



### 参考链接

[Java 对象序列化](https://developer.ibm.com/zh/articles/j-5things1/)



# Java特性列表

## Java5

+ 自动装箱拆箱
+ 泛型
+ 枚举
+ 变长参数
+ 注解
+ foreach循环
+ 静态导入
+ 新的线程模型和并发库 `java.util.concurrent`

## Java6

+ 脚本引擎
+ Java Compiler API
+ JDBC 4.0规范

## Java7

+ 字面常量数字的下划线
+ switch支持String
+ 泛型实例化类型自动推断

```java
// java7之前
Map<String,List<String>> map = new HashMap<String,List<String>>();
// java7之后
Map<String,List<String>> map = new HashMap<>();
```

+ `try-with-resources` 
+ 单个catch中捕获多个异常类型
+ NIO 2.0 (`AIO`)

```java
ByteBuffer buffer = ByteBuffer.allocate(32);
buffer.put(new byte[16]);
buffer.position();
```



## `Java8 (LTS)`

+ `Lambda`表达式
+ 新`Date Time API`
+ 接口的`默认方法`和`静态方法`

```java
public interface Test {
  default String defaultFunction() {
    return "default";
  }
  
  static String staticFunction() {
    return "static";
  }
}
```

+ 方法引用
+ 集合的`stream`操作
+ `Optional`处理空指针异常

## Java9

+ REPL工具 `jShell`
+ 模块系统
+ 增强Stream

## Java10

+ 局部变量类型推断

```java
var url = new URL("http://www.oracle.com/")
```

+ Optional新增`orElseThrow`方法

## `Java11 (LTS)`

+ 字符串、集合、Stream、Optional、InputStream增强
+ java直接编译运行

```shell
# before
javac Javastack.java; java Javastack

# after
java Javastack.java
```

## Java12

+ switch增强

```java
// 之前
switch (day) {
  case MONDAY:
    // XXX
}

// 之后
switch (day) {
    case MONDAY -> "monday";
}
```

+ 其他增强

## Java13

## Java14

+ 增强instanceof

```java
// 无需自己进行类型转换
public boolean isBadRequestError(Exception ex) {
  return (ex instanceof HttpClientErrorException rce) && HttpStatus.BAD_REQUEST == rce.getStatusCode();
}
```

+ JDK12引入的switch在JDK14变为正式版本。增强switch，提供`yield`在块中返回值，但不同于`return`

```java
String quantityString = switch (n) {
  case 1 : yield "one";
  case 2 -> "two";
}
```

+ 长文本字符串

```java
String name = """
  what's your name
  """;
```

##Java15

+ 关键字`sealed`、`permits`、`non-sealed`限定实现类，限定父类的使用。

## Java16

##`Java17 (LTS)`
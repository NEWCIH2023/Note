+ Mybatics框架的原理
  + 初始化
    + 解析配置文件，初始化Configuration对象
    + 创建SqlSessionFactory建造者对象，用来创建SqlSessionFactory
    + 解析mapper文件，结果存入configuration对象
    + 根据configuration对象，创建SqlSession
  + SQL查询流程
    + sqlSession调用执行器进行查询 (`优先使用缓存`)
  + MyBatis缓存
    + 一级缓存，sqlSession缓存，每个sqlSession都有一个`哈希表`缓存数据，不同SqlSession对象之间缓存不共享。**默认开启**
    + 二级缓存，mapper级别缓存，跨sqlSession，不同的SqlSession对象执行两次相同的SQL语句，第一次就将查询结果缓存，第二次返回缓存。**默认不开启**

![772134-20170407225038957-716642123](../images/772134-20170407225038957-716642123.png)
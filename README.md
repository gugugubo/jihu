# jihu-记乎

## 前言

这是一个仿造记乎app的后台，登录基于redis-cookie，可以作为后台的入门练手项目！安卓端地址为：[github](https://github.com/LoveLifeEveryday/MemoryKeeper)



## 项目文档

- 文档地址：https://www.eolinker.com/#/share/index?shareCode=YYCTkE

  

## 项目介绍

### 功能结构图

![tu](https://img2020.cnblogs.com/blog/2152948/202011/2152948-20201104022818374-1135361567.png)



### 具体功能列表

| 功能             | 详细描述                                                     |
| ---------------- | ------------------------------------------------------------ |
| 登录注册         | 注册账号，登录账号                                           |
| 用户信息管理     | 更改用户信息，用于展示卡包作者简略信息                       |
| 创建卡包         | 创建时输入名字，描述和上传图片                               |
| 更新卡包         | 对卡片的操作都是更新卡包                                     |
| 卡包内容         | 多张卡片的集合                                               |
| 删除卡包         | 删除卡包，若卡包被共享则本地化（没有实现本地化）             |
| 共享卡包         | 使其可以被搜索和关联，共享者获得卡包凭证（直接收藏之后就可以进行卡包阅读） |
| 卡包凭证         | 卡片凭证用于换取卡包（直接收藏之后就可以进行卡包阅读）       |
| 卡片内容         | 一问一答形式                                                 |
| 创建卡片         | 输入问题和回答                                               |
| 更新卡片         | 对卡片的问题或者回答进行更改                                 |
| 删除卡片         | 删除卡片                                                     |
| 推荐背诵卡片算法 | [算法简单解析](https://blog.csdn.net/hnliuwx/article/details/5519354)（这个没写） |

### 组织结构

```properties
├─main
│  ├─java
│  │  └─com
│  │      └─gdut
│  │          └─jiyi
│  │              ├─advice   全局异常控制
│  │              ├─common   返回工具类
│  │              ├─config   配置类
│  │              ├─controller   controller层
│  │              ├─emum    枚举类
│  │              ├─filter  实现登录过滤器
│  │              ├─mapper  sql自动生成的mapper层
│  │              ├─mapperMore
│  │              ├─mbg     sql自动生成类
│  │              ├─model   sql自动生成的数据库实体类
│  │              ├─server  service层
│  │              │  └─impl
│  │              ├─util  工具列
│  │              └─vo  与前端交换的实体类
│  └─resources
│      ├─mapper  自动生成的sql
│      ├─mapperMore  自己手写的sql
│      └─mbgconfig  sql自动生成配置类
```



### 技术选型

#### 后端技术

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot         |
| SpringSecurity       | 认证和授权框架      | https://spring.io/projects/spring-security     |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html |
| MyBatisGenerator     | 数据层代码生成      | http://www.mybatis.org/generator/index.html    |
| PageHelper           | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |
| Swagger-UI           | 文档生产工具        | https://github.com/swagger-api/swagger-ui      |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                 |
| Redis                | 分布式缓存          | https://redis.io/                              |
| Docker               | 应用容器引擎        | https://www.docker.com                         |
| OSS                  | 对象存储            | https://github.com/aliyun/aliyun-oss-java-sdk  |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok         |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid               |
|                      |                     |                                                |
|                      |                     |                                                |
|                      |                     |                                                |
|                      |                     |                                                |
|                      |                     |                                                |
|                      |                     |                                                |
|                      |                     |                                                |

## 环境搭建

### 开发工具

| 工具         | 说明                |
| ------------ | ------------------- |
| IDEA         | 开发IDE             |
| RedisDesktop | redis客户端连接工具 |
| Typora       | Markdown编辑器      |
| Navicat      | 数据库连接工具      |
| X-shell      | Linux远程连接工具   |

### 开发环境

| 工具  | 版本号 |
| ----- | ------ |
| JDK   | 1.8    |
| Mysql | 5.7    |
| Redis | 5.0    |

### 


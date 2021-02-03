
## 简介
基于Java语言使用springboot + shiro + jwt + mybatisplus为基础架构搭建的安全框架demo适用于前后端分离的项目。

虽然 Shiro 本身可以支持扩展 RememberMe 功能，但仅限于传统项目，不适用前后端分离的单体项目，和微服务架构的项目等。
因为 Shiro 的用户信息是基于 Session 进行管理，在前后端分离的项目中无法实现 Session 状态的前后统一，
所以本文通过 JWT 对 Shiro 原生的 Session 控制进行替换，从而实现用户信息的前后传递及判断。



## 核心技术选型

* 核心框架：springboot 2.3.5
* 安全框架：Apache Shiro 1.7.0
* 持久层框架：mybatisPlus 3.4.2
* 缓存框架：Ehcache 2.10.6

## 安全考虑
yml配置文件的一些账号密码等敏感信息用jasypt加了密，本地application-local.yml除外。
如果不用jar包的运行命令执行应用程序，那本地执行dev/pro环境的应用程序有以下两种方法：
（1）：需自行在yml配置中jasypt:encryptor:的加入password: 00AA1789cee32A4482da531e72669788（可换为自定义秘钥）
（2）：在idea或者eclipse的JVM中加入-Djasypt.encryptor.password=00AA1789cee32A4482da531e72669788

## 其他
# jar包启动命令（dev/pro）
java -Dfile.encoding=utf-8 -Djasypt.encryptor.password=00AA1789cee32A4482da531e72669788 -jar shiro.jar

# swagger 接口文档
访问地址：http://127.0.0.1:1234/doc.html  账号/密码：admin/admin123456

# druid 数据库连接池
访问地址：http://127.0.0.1:1234/druid/login.html   账号/密码：admin/admin123456

# mybatisPlus 官方代码生成器
MybatisPlusGenerator





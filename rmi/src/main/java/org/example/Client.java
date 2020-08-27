package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        //User user= (User)registry.lookup("user");
        //System.out.println(user.getName());
        registry.list();
    }
}
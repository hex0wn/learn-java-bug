package org.example;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        User user = new RemoteUser("liming",15);

        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        registry.bind("user",user);

        CountDownLatch latch=new CountDownLatch(1);
        latch.await();
    }
}

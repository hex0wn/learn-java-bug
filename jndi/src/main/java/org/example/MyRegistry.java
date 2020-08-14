package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CountDownLatch;

public class MyRegistry {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        Registry registry = LocateRegistry.createRegistry(1099);
        CountDownLatch latch=new CountDownLatch(1);
        latch.await();
    }
}
package org.example;
import java.rmi.RemoteException;

public interface User extends java.rmi.Remote {
    public String getName() throws RemoteException;
    public User getUser() throws RemoteException;
    public void updateName(String name) throws RemoteException;
    public void vuln(Object obj) throws RemoteException;;
}

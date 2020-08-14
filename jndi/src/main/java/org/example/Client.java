package org.example;

import javax.naming.*;

public class Client {
    public static void main(String[] args) throws Exception {
        //Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        //User user= (User)registry.lookup("user");
        Context ctx = new InitialContext();
        //User user= (User)ctx.lookup("rmi://127.0.0.1:1099/user");
        //System.out.println(user.getName());

        ctx.lookup("ldap://127.0.0.1:1389/user");
        //ctx.lookup("rmi://127.0.0.1:1099/user");
    }
}
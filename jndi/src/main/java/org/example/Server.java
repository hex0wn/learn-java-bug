package org.example;

import javax.naming.*;
import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] args) throws Exception {
        User user = new RemoteUser("liming",15);

        //Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        //registry.bind("user",user);
        Context ctx = new InitialContext();
        ctx.bind("rmi://127.0.0.1:1099/user", user);

        CountDownLatch latch=new CountDownLatch(1);
        latch.await();
    }
}

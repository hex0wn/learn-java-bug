package com.satan;


public class MyService implements DefaultService {

    @Override
    public String sayHello(String name) {
        return String.format("Hello, %s", name);
    }
}
package com.example.springbootspel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/index")
    public String hello(String name){
        throw new IllegalArgumentException("throw exception with message: "+name);
    }
}

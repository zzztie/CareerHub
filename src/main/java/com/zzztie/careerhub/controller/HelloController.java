package com.zzztie.careerhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
       public String hello(){
           return "Hello CareerHub";
       }
    @GetMapping("/hello-user")
    public String helloUser(@RequestParam String name){
        return "Hello"+name;
    }
}

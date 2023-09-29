package com.restapi.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    //add for the "/hello" endPoint

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

}

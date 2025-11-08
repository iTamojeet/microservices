package com.example.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ShellComponent
public class Controller {

    @GetMapping("/hello")
    @ShellMethod(key="tom",value="It should return hello world")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/add/{a}/{b}")
    @ShellMethod(key = "koyel", value = "It adds two numbers")
    public int add(@PathVariable int a, @PathVariable int b){
        return a+b;
    }

    @GetMapping("/list")
    @ShellMethod(key = "abc")
    public List<String> list(){
        return List.of("a","b","c");
    }
}

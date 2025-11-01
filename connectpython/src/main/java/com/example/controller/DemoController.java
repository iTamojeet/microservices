package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    //public final static String URL = "http://localhost:7676/python";
    public final static String URL = "http://192.168.0.11:7676/python";
    public final static String BookUrl = "http://192.168.0.11:7676/books";

    @GetMapping("/api")
    public String testing(){
        return "I am Spring Boot";
    }

    @GetMapping("/callpy")
    public ResponseEntity<String> callPython() {
        return restTemplate.getForEntity(URL, String.class);
    }

    @GetMapping("/callbooks")
    public ResponseEntity<List> callPythonBooks() {
        return restTemplate.getForEntity(BookUrl, List.class);
    }
}

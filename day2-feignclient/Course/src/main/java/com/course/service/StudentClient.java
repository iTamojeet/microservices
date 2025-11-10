package com.course.service;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "STUDENT")
public interface StudentClient {
    @GetMapping("/students")
    List<Map<String,Object>> getAllStudents();

}
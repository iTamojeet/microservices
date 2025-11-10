package com.ai.controller;

import com.ai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

    @Autowired
    private OpenAiService openAiService;

    @GetMapping("ai")
    public String prompt() {
        return openAiService.getAnswer("What is AI in 2 bullet points?");
    }
}

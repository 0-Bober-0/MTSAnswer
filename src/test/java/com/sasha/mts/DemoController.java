package com.sasha.mts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/test")
    public String testEndpoint() {
        return "OK";
    }
}
package com.thaipham.datahub.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello from Spring Boot!");
    }

    @GetMapping("/about")
    public Map<String, String> about() {
        return Map.of(
            "project", "DataHub",
            "description", "A simple Spring Boot project for testing endpoints"
        );
    }

    @GetMapping("/greet")
    public Map<String, String> greet(@RequestParam(defaultValue = "friend") String name) {
        return Map.of("greeting", "Hello, " + name + "!");
    }

    @PostMapping("/welcome")
    public Map<String, String> welcome(@RequestBody Map<String, String> payload) {
        String name = payload.getOrDefault("name", "friend");
        return Map.of("message", "Welcome, " + name + "!");
    }
}

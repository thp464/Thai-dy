package com.thaipham.datahub.controller;

import com.thaipham.datahub.model.User;
import com.thaipham.datahub.model.Note;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class HelloController {

    private final List<Note> notes = new ArrayList<>();

    @GetMapping
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

    // New: Create a user
    @PostMapping("/users")
    public Map<String, String> createUser(@RequestBody User user) {
        return Map.of(
            "message", "User " + user.getName() + " registered!",
            "email", user.getEmail()
        );
    }

    // New: Add a note
    @PostMapping("/notes")
    public Map<String, String> addNote(@RequestBody Note note) {
        notes.add(note);
        return Map.of(
            "status", "Note saved",
            "title", note.getTitle()
        );
    }

    // New: Get all notes
    @GetMapping("/notes")
    public List<Note> getNotes() {
        return notes;
    }
}

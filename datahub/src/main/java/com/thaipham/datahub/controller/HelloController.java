package com.thaipham.datahub.controller;

import com.thaipham.datahub.model.Note;
import com.thaipham.datahub.model.User;
import com.thaipham.datahub.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@RequestMapping("/")
public class HelloController {

    private final List<Note> notes = new ArrayList<>();
    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        boolean deleted = userService.deleteUserByEmail(email);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        Optional<User> updated = userService.updateUserByEmail(email, updatedUser);
        return updated
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


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

    @PostMapping("/users")
    public Map<String, String> createUser(@RequestBody User user) {
        userService.addUser(user);  
        return Map.of(
            "message", "User " + user.getName() + " registered!",
            "email", user.getEmail()
        );
    }

    @PostMapping("/notes")
    public Map<String, String> addNote(@RequestBody Note note) {
        notes.add(note);
        return Map.of(
            "status", "Note saved",
            "title", note.getTitle()
        );
    }

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return notes;
    }



}

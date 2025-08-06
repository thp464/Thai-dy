package com.thaipham.datahub.controller;

import com.thaipham.datahub.model.User;
import com.thaipham.datahub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User user) {
        boolean added = userService.addUser(user);

        if (!added) {
            return ResponseEntity
                .badRequest()
                .body(Map.of("error", "User with this email already exists."));
        }

        return ResponseEntity.ok(Map.of(
            "message", "User " + user.getName() + " registered!",
            "email", user.getEmail(),
            "id", user.getId()
        ));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        boolean deleted = userService.deleteUserByEmail(email);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        Optional<User> updated = userService.updateUserByEmail(email, updatedUser);
        return updated
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package com.thaipham.datahub.service;

import com.thaipham.datahub.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users); 
    }

    public boolean deleteUserByEmail(String email) {
        return users.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> updateUserByEmail(String email, User updatedUser) {
        Optional<User> userOpt = users.stream()
            .filter(u -> u.getEmail().equalsIgnoreCase(email))
            .findFirst();

        userOpt.ifPresent(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail()); 
        });

        return userOpt;
    }
}

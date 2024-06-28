package com.social_book.controller;

import com.social_book.entity.User;
import com.social_book.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(existingUser.get());
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<Void> followUser(@PathVariable Long userId, @RequestParam Long followerId) {
        userService.followUser(userId, followerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/unfollow")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long userId, @RequestParam Long followerId) {
        userService.unfollowUser(userId, followerId);
        return ResponseEntity.noContent().build();
    }
}
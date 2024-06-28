package com.social_book.controller;


import com.social_book.entity.Post;
import com.social_book.entity.User;
import com.social_book.service.PostService;
import com.social_book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        post.setUser(user);
        return ResponseEntity.ok(postService.savePost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow();
        return ResponseEntity.ok(postService.findByUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}

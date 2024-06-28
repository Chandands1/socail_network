package com.social_book.controller;


import com.social_book.entity.Like;
import com.social_book.entity.Post;
import com.social_book.entity.User;
import com.social_book.service.LikeService;
import com.social_book.service.PostService;
import com.social_book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/posts/{postId}/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Like> likePost(@PathVariable Long postId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        Post post = postService.findById(postId).orElseThrow();
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        return ResponseEntity.ok(likeService.saveLike(like));
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        Post post = postService.findById(postId).orElseThrow();
        Like like = likeService.findByPostAndUser(post, user).orElseThrow();
        likeService.deleteLike(like.getId());
        return ResponseEntity.noContent().build();
    }
}
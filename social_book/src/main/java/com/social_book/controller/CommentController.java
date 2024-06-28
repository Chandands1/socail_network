package com.social_book.controller;


import com.social_book.entity.Comment;
import com.social_book.entity.Post;
import com.social_book.entity.User;
import com.social_book.service.CommentService;
import com.social_book.service.PostService;
import com.social_book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody Comment comment, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        Post post = postService.findById(postId).orElseThrow();
        comment.setUser(user);
        comment.setPost(post);
        return ResponseEntity.ok(commentService.saveComment(comment));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        Post post = postService.findById(postId).orElseThrow();
        return ResponseEntity.ok(commentService.findByPost(post));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
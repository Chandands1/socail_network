package com.social_book.service;

import com.social_book.entity.Comment;
import com.social_book.entity.Post;
import com.social_book.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

package com.social_book.repository;

import com.social_book.entity.Comment;
import com.social_book.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
package com.social_book.repository;

import com.social_book.entity.Post;
import com.social_book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}

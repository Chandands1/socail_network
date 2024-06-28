package com.social_book.repository;

import com.social_book.entity.Like;
import com.social_book.entity.Post;
import com.social_book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
}

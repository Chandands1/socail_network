package com.social_book.service;

import com.social_book.entity.Like;
import com.social_book.entity.Post;
import com.social_book.entity.User;
import com.social_book.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public Like saveLike(Like like) {
        return likeRepository.save(like);
    }

    public Optional<Like> findByPostAndUser(Post post, User user) {
        return likeRepository.findByPostAndUser(post, user);
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}

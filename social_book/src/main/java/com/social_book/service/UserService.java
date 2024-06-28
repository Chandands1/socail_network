package com.social_book.service;

import com.social_book.entity.User;
import com.social_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void followUser(Long userId, Long followerId) {
        User user = userRepository.findById(userId).orElseThrow();
        User follower = userRepository.findById(followerId).orElseThrow();
        user.getFollowers().add(follower);
        userRepository.save(user);
    }

    public void unfollowUser(Long userId, Long followerId) {
        User user = userRepository.findById(userId).orElseThrow();
        User follower = userRepository.findById(followerId).orElseThrow();
        user.getFollowers().remove(follower);
        userRepository.save(user);
    }
}
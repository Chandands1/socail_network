package com.social_book.service;

import com.social_book.entity.Post;
import com.social_book.entity.User;
import com.social_book.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findByUser(User user) {
        return postRepository.findByUser(user);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

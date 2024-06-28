package com.social_book.controller;

import com.social_book.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public void sendUpdate(@RequestBody Post post) {
        messagingTemplate.convertAndSend("/topic/feed", post);
    }
}

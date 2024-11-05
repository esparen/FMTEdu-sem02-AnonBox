package com.example.AnonBox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.AnonBox.database.entity.CommentEntity;
import com.example.AnonBox.service.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/suggestions/{suggestionId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentEntity> getAllComments(@PathVariable Long suggestionId) {
        log.info("GET request received for all comments of suggestion with id: {}", suggestionId);
        return commentService.getAllCommentsBySuggestion(suggestionId);
    }

    @GetMapping("/{commentId}")
    public CommentEntity getCommentById(@PathVariable Long suggestionId, @PathVariable Long commentId) {
      log.info("GET request received for comment with id: {} on suggestion with id: {}", commentId, suggestionId);
      return commentService.getCommentById(commentId);
    }

    @PostMapping
    public CommentEntity createComment(@PathVariable Long suggestionId, @RequestBody CommentEntity comment) {
        log.info("POST request received for creating a new comment on suggestion with id: {}", suggestionId);
        return commentService.saveComment(suggestionId, comment);
    }
}




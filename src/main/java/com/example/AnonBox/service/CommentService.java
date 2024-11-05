package com.example.AnonBox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AnonBox.database.entity.CommentEntity;
import com.example.AnonBox.database.entity.SuggestionEntity;
import com.example.AnonBox.database.repository.CommentRepository;
import com.example.AnonBox.database.repository.SuggestionRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    private SuggestionRepository suggestionRepository;

    public List<CommentEntity> getAllCommentsBySuggestion(Long suggestionId) {
        log.info("Fetching all comments for suggestion with id: {}", suggestionId);
        return commentRepository.findBySuggestionId(suggestionId);
    }

    public CommentEntity getCommentById(Long commentId) {
      log.info("Fetching comment with id: {}", commentId);
      return commentRepository.findById(commentId).orElse(null);
    }

    @Transactional
    public CommentEntity addComment(Long suggestionId, CommentEntity comment) {
        log.info("Saving new comment for suggestion with id: {}", suggestionId);
        SuggestionEntity suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));

        suggestion.setSubmittedAt(LocalDateTime.now());
        suggestionRepository.save(suggestion);

        comment.setSuggestion(suggestion);
        comment.setSubmittedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}

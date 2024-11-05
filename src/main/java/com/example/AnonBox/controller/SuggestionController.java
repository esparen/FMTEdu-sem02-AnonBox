package com.example.AnonBox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.AnonBox.database.entity.CommentEntity;
import com.example.AnonBox.database.entity.SuggestionEntity;
import com.example.AnonBox.service.CommentService;
import com.example.AnonBox.service.SuggestionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;
    private CommentService commentService;

    @GetMapping
    public Optional<List<SuggestionEntity>> getAllSuggestions() {
        log.info("GET request received for all suggestions");
        return suggestionService.getAllSuggestions();
    }

    @GetMapping("/{id}")
    public SuggestionEntity getSuggestionById(@PathVariable Long id) {
      log.info("GET request received for suggestion with id: {}", id);
      return suggestionService.getSuggestionById(id);
    }

    @PostMapping
    public SuggestionEntity createSuggestion(@RequestBody SuggestionEntity suggestion) {
        log.info("POST request received for creating a new suggestion");
        return suggestionService.saveSuggestion(suggestion);
    }

    @PutMapping("/{id}")
    public SuggestionEntity updateSuggestion(@PathVariable Long id, @RequestBody SuggestionEntity suggestion) {
        log.info("PUT request received for updating suggestion with id: {}", id);
        return suggestionService.updateSuggestion(id, suggestion);
    }

    
    @PostMapping("/{id}/comentario")
    public CommentEntity addComment(@PathVariable Long suggestionId, @RequestBody CommentEntity comment) {
        log.info("POST request received for creating a new comment on suggestion with id: {}", suggestionId);
        return commentService.addComment(suggestionId, comment);
    }
}

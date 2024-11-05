package com.example.AnonBox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AnonBox.database.entity.SuggestionEntity;
import com.example.AnonBox.database.repository.SuggestionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<SuggestionEntity> getAllSuggestions() {
        log.info("Fetching all suggestions");
        return suggestionRepository.findAll();
    }

    public SuggestionEntity getSuggestionById(Long id) {
      log.info("Fetching suggestion with id: {}", id);
      return suggestionRepository.findByIdWithOrderedComments(id)
        .orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));
    }

    public SuggestionEntity saveSuggestion(SuggestionEntity suggestion) {
        suggestion.setSubmittedAt(LocalDateTime.now());
        log.info("Saving new suggestion with title: {}", suggestion.getTitle());
        return suggestionRepository.save(suggestion);
    }

    public SuggestionEntity updateSuggestion(Long id, SuggestionEntity updatedSuggestion) {
        log.info("Updating suggestion with id: {}", id);
        return suggestionRepository.findById(id)
            .map(suggestion -> {
                suggestion.setTitle(updatedSuggestion.getTitle());
                suggestion.setDescription(updatedSuggestion.getDescription());
                suggestion.setUpdatedAt(LocalDateTime.now());
                return suggestionRepository.save(suggestion);
            })
            .orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));
    }
}

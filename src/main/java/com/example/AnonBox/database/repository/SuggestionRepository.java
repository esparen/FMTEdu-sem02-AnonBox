package com.example.AnonBox.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AnonBox.database.entity.SuggestionEntity;

public interface SuggestionRepository extends JpaRepository<SuggestionEntity, Long> {}

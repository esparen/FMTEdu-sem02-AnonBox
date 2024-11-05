package com.example.AnonBox.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AnonBox.database.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

  List<CommentEntity> findBySuggestionId(Long suggestionId);
  
}

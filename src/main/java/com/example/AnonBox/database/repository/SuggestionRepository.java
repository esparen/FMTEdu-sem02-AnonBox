package com.example.AnonBox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.AnonBox.database.entity.SuggestionEntity;

public interface SuggestionRepository extends JpaRepository<SuggestionEntity, Long> {

  @Query("SELECT s FROM Suggestion s LEFT JOIN FETCH s.comments c WHERE s.id = :id ORDER BY c.submittedAt ASC")
  Optional<SuggestionEntity> findByIdOrderByCommentsSubmittedAt(@Param("id") Long id);

  Optional<List<SuggestionEntity>> findAllByOrderBySubmittedAtDesc();

}

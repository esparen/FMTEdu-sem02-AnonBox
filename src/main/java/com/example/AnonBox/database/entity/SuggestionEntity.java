package com.example.AnonBox.database.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime submittedAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "suggestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
}





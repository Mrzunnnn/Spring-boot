package org.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer userId;
    Integer movieId;
    Integer rating;
    @Column(columnDefinition = "TEXT")
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

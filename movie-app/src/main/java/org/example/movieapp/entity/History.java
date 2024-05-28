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
@Table(name = "historys")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class History {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer movieId;
    Integer userId;
    Double duration;
    Integer episode;
    LocalDateTime createdAt;
}

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

    Double duration;

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name="episode_id")
    Episode episode;

}

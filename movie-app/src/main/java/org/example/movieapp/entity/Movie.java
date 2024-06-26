package org.example.movieapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movieapp.model.enums.MovieType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    String slug;

    @Column(columnDefinition = "TEXT")
    String description;
    String trailerURL;
    Boolean status;
    Integer releaseYear;
    String poster;
    Double rating;

    @Enumerated(EnumType.STRING)
    MovieType type;


    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;


    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directors;

    @JsonIgnore
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    List<Episode> episodes;
}

package org.example.movieapp.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movieapp.model.enums.MovieType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertMovieRequest {
    @NotEmpty(message = "Tên không được để trống")
    String name;
    @NotEmpty(message = "Trailer không được để trống")

    String trailerUrl;
    @NotEmpty(message = "Mô tả không được để trống")

    String description;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;

    @NotEmpty(message = "Năm phát hành không được để trống")
    Integer releaseYear;

    @NotEmpty(message = "Loại không được để trống")
    MovieType type;

    @NotEmpty(message = "Trạng thái không được để trống")
    Boolean status;

    @NotEmpty(message = "Quốc gia không được để trống")
    Integer countryIds;


}

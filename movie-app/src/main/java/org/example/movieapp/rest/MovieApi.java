package org.example.movieapp.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.model.request.UpsertMovieRequest;
import org.example.movieapp.servive.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    @PostMapping("/admin/movie")
    ResponseEntity<?> createMovie(@Valid @RequestBody UpsertMovieRequest request){
        return ResponseEntity.ok(movieService.createMovie(request));
    }
}

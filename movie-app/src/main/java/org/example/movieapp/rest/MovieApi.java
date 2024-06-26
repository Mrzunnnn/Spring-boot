package org.example.movieapp.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.model.request.UpsertMovieRequest;
import org.example.movieapp.servive.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    @PostMapping("/admin/movie")
    ResponseEntity<?> createMovie(@Valid @RequestBody UpsertMovieRequest request){
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @PutMapping("/admin/movie/{id}")
    ResponseEntity<?> updateMovie(@Valid @PathVariable Integer id , @RequestBody UpsertMovieRequest request){
        return ResponseEntity.ok(movieService.updateMovie(id,request));
    }


    @DeleteMapping("/admin/movie/{id}")
    ResponseEntity<?> deleteMovie(@Valid @PathVariable Integer id ){
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }

    @PostMapping("/admin/movie/{id}/upload-poster")
    ResponseEntity<?> uploadPoster(@PathVariable Integer id, @RequestParam MultipartFile file){
        return ResponseEntity.ok(movieService.uploadPoster(id,file));
    }
}

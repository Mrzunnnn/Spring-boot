package org.example.movieapp.rest;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Favorite;
import org.example.movieapp.servive.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;
    @PostMapping
    public ResponseEntity<?> addToFavourite(@RequestParam Integer movieId){
        Favorite favourite = favoriteService.addToFavorite(movieId);
        return ResponseEntity.ok(favourite);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromFavourite(@RequestParam Integer movieId){
        favoriteService.deleteFromFavorite(movieId);
        return ResponseEntity.ok().build();
    }
}

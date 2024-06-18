package org.example.movieapp.servive;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Favorite;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.exception.ResourceNotFoundException;
import org.example.movieapp.repository.FavoriteRespository;
import org.example.movieapp.repository.MovieRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private  final FavoriteRespository favoriteRespository;
    private final MovieRepository movieRepository;
    private final HttpSession session;
    public Favorite addToFavorite(@Valid Integer movieId) {
        User user = (User) session.getAttribute("currentUser");

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found"));

        if (favoriteRespository.existsByUser_IdAndMovie_Id(user.getId(),movie.getId())){
            throw new ResourceNotFoundException("Movie already in favorite list.");
        }
        Favorite favorite = Favorite.builder()
                .user(user)
                .movie(movie)
                .createdAt(LocalDateTime.now())
                .build();
        return favoriteRespository.save(favorite);
    }

    public void deleteFromFavorite(@Valid Integer movieId) {
        User user = (User) session.getAttribute("currentUser");

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found"));

        Optional<Favorite> favoriteOptional = favoriteRespository.findByUser_IdAndMovie_Id(user.getId(),movie.getId());
        if (favoriteOptional.isEmpty()){
            throw new ResourceNotFoundException("Movie not in favorite list");
        }

        favoriteRespository.delete(favoriteOptional.get());
    }

    public boolean isFavorite(@Valid Integer movieId) {
        // Lấy thông tin user đang đăng nhập
        User user = (User) session.getAttribute("currentUser");

        // Kiểm tra movie có tồn tại không
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return favoriteRespository.existsByUser_IdAndMovie_Id(user.getId(), movie.getId());
    }

    public List<Favorite> getAllFavoritesByCurrentUser(){
        User user = (User) session.getAttribute("currentUser");
        return favoriteRespository.findByUser_IdOrderByCreatedAtDesc(user.getId());
    }
}

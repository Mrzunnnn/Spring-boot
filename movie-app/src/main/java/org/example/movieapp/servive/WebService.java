package org.example.movieapp.servive;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.model.enums.MovieType;
import org.example.movieapp.repository.MovieRepository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebService {
    private final MovieRepository movieRepository;

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }
}

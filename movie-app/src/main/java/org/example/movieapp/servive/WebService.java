package org.example.movieapp.servive;


import org.example.movieapp.entity.Episode;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.entity.Review;
import org.example.movieapp.model.enums.MovieType;
import org.example.movieapp.repository.EpisodeRespository;
import org.example.movieapp.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.repository.ReviewRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebService {
    private final MovieRepository movieRepository;
    private final EpisodeRespository episodeRespository;
    private final ReviewRespository reviewRespository;

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }

    public List<Movie> getHotMovie(){
        return movieRepository.findTop10ByStatusOrderByRatingDesc(true);
    }


    public Movie findById(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true).orElse(null);
    }

    public List<Movie> getRelatedMovies(Movie movie) {
      return movieRepository.findTop6ByTypeAndStatusAndIdNotOrderByRatingDesc(movie.getType(), true, movie.getId());
    };

    public List<Episode> getEpisodes(Integer movieId,Boolean status) {
        return episodeRespository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(movieId,true);
    }

    public List<Review> getReviews(Integer movieId) {
        return reviewRespository.findByMovie_IdOrderByCreatedAtDesc(movieId);
    }

    public Optional<Episode> getEpisode(Integer movieId, Boolean status, String tap) {
        Integer coverTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
      return episodeRespository
              .findByMovie_IdAndStatusAndDisplayOrder(movieId,true,1);
    }

}

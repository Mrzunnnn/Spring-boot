package org.example.movieapp.servive;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Country;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.exception.ResourceNotFoundException;
import org.example.movieapp.model.request.UpsertMovieRequest;
import org.example.movieapp.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRespository countryRespository;
    private final DirectorRespository directorRespository;
    private final ActorRespository actorRespository;
    private final GenreRespository genreRespository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by("createdAt").descending());
    }

    public  Movie getMovieById(Integer id){
        return movieRepository.findById(id).orElse(null);
    }

    public Movie createMovie(UpsertMovieRequest request) {
        Slugify slugify = Slugify.builder().build();
        Country country = countryRespository.findById(request.getCountryIds())
                .orElseThrow(()->new ResourceNotFoundException("Quốc gia không tồn tại"));

        Movie movie = Movie.builder()
                .name(request.getName())
                .slug(slugify.slugify(request.getName()))
                .description(request.getDescription())
                .poster("https://placehold.co/600x400?text="+request.getName().substring(0,1).toUpperCase())
                .releaseYear(request.getReleaseYear())
                .trailerURL(request.getTrailerUrl())
                .type(request.getType())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(request.getStatus()?LocalDateTime.now():null)
                .country(country)
                .genres(genreRespository.findAllById(request.getGenreIds()))
                .actors(actorRespository.findAllById(request.getActorIds()))
                .directors(directorRespository.findAllById(request.getDirectorIds()))
                .build();
        return movieRepository.save(movie);
    }
}

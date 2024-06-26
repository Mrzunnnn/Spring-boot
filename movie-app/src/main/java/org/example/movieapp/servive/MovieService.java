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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRespository countryRespository;
    private final DirectorRespository directorRespository;
    private final ActorRespository actorRespository;
    private final GenreRespository genreRespository;
    private final CloudinaryService cloudinaryService;

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

    public Movie updateMovie(Integer id, UpsertMovieRequest request) {
        Slugify slugify = Slugify.builder().build();
        Country country = countryRespository.findById(request.getCountryIds())
                .orElseThrow(()->new ResourceNotFoundException("Quốc gia không tồn tại"));
        Movie movie = movieRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy phim"));
        movie.setName(request.getName());
        movie.setTrailerURL(request.getTrailerUrl());
        movie.setDescription(request.getDescription());
        movie.setDirectors(directorRespository.findAllById(request.getDirectorIds()));
        movie.setType(request.getType());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setUpdatedAt(LocalDateTime.now());
        movie.setCountry(country);
        movie.setActors(actorRespository.findAllById(request.getActorIds()));
        movie.setStatus(request.getStatus());
        movie.setGenres(genreRespository.findAllById(request.getGenreIds()));
        movie.setPublishedAt(request.getStatus()?LocalDateTime.now() : null);

        return movieRepository.save(movie);
    }

    public Movie deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy phim"));
        movieRepository.delete(movie);
        return null;
    }

    public String uploadPoster(Integer id, MultipartFile file) {
        Movie movie = movieRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy phim"));

        try {
            Map result = cloudinaryService.uploadFile(file);
            movie.setPoster((String) result.get("url"));
            movieRepository.save(movie);
            return (String) result.get("url");
        }
        catch (Exception e){
            throw  new RuntimeException("Lỗi khi upload poster");
        }
    }
}

package org.example.movieapp;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.example.movieapp.entity.*;
import org.example.movieapp.model.enums.MovieType;
import org.example.movieapp.model.enums.UserType;
import org.example.movieapp.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MovieAppApplicationTests {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private CountryRespository countryRespository;
    @Autowired
    private GenreRespository genreRespository;
    @Autowired
    private DirectorRespository directorRespository;
    @Autowired
    private ActorRespository actorRespository;

    @Test
    void save_movies() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 100; i++) {
            String name = faker.book().title();
            Boolean status = faker.bool().bool();
            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400?text=" + name.substring(0, 1).toUpperCase())
                    .releaseYear(faker.number().numberBetween(2021, 2024))
                    .rating(faker.number().randomDouble(1, 1, 10))
                    .trailerURL("https://www.youtube.com/embed/YPY7J-flzE8?si=NIAaDGXL68JdDCux")
                    .type(MovieType.values()[faker.number().numberBetween(0, MovieType.values().length - 1)])
                    .status(status)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void save_users() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 30; i++) {
            String name = faker.name().username();
            String email = faker.internet().emailAddress();
            String avatar = faker.internet().avatar();
            String password = faker.internet().password();
            User user = User.builder()
                    .name(name)
                    .password(password)
                    .email(email)
                    .avatar("https://placehold.co/600x400?text=")
                    .updatedAt(LocalDate.now())
                    .type(UserType.values()[faker.number().numberBetween(0, UserType.values().length - 1)])
                    .updatedAt(LocalDate.now())
                    .build();
            userRespository.save(user);

        }
    }

    @Test
    void save_country(){
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 20; i++) {
            String name = faker.country().name();
            Country country = Country.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            countryRespository.save(country);
        }
    }

    @Test
    void save_genre(){
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 20; i++) {
            String genre = faker.book().genre();
            Genre genres = Genre.builder()
                    .genre(genre)
                    .slug(slugify.slugify(genre))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            genreRespository.save(genres);
        }
    }

    @Test
    void save_director(){
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 30; i++) {
            String name = faker.name().fullName();
            Director director = Director.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .avatar("https://placehold.co/600x400?text=")
                    .bio(faker.lorem().paragraph())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            directorRespository.save(director);
        }
    }
    @Test
    void save_actor(){
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 100; i++) {
            String name = faker.name().fullName();
            Actor actor = Actor.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .avatar("https://placehold.co/600x400?text=")
                    .bio(faker.lorem().paragraph())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            actorRespository.save(actor);
        }
    }

    @Test
    void movie_methods_test() {
        //SELECT
        //SELECT ALL
        List<Movie> movies = movieRepository.findAll();
        System.out.println("movies size " + movies.size());

        List<Movie> movieByIds = movieRepository.findAllById(List.of(1, 2, 3));

        Movie movie = movieRepository.findById(1).orElse(null);

        movie.setName("Đào,Phở,Piano");
        movieRepository.save(movie);


    }

}

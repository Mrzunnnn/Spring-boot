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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private EpisodeRespository episodeRespository;
    @Autowired
    private ReviewRespository reviewRespository;
    @Autowired
    private HistoryRespository historyRespository;
    @Autowired
    private FavoriteRespository favoriteRespository;

    @Test
    void save_movies() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        /// Lấy tất cả   đối tượng liên quan

        List<Country> countries = countryRespository.findAll();
        List<Genre> genres = genreRespository.findAll();
        List<Director> directors = directorRespository.findAll();
        List<Actor> actors = actorRespository.findAll();

        for (int i = 0; i < 100; i++) {
            //random 1 country
            Country rdCountry = countries.get(rd.nextInt(countries.size()));

            //random 2-3 thể loại
            List<Genre> rdGenres = new ArrayList<>();
            for (int j = 0; j < rd.nextInt(2)+1; j++) {
                Genre rdGenre = genres.get(rd.nextInt(genres.size()));
                if (!rdGenres.contains(rdGenre)) {
                    rdGenres.add(rdGenre);
                }
            }


            //5-7 dien vien
            List<Actor> rdActors = new ArrayList<>();
            for (int j = 0; j < rd.nextInt(3)+5; j++) {
                Actor rdActor = actors.get(rd.nextInt(actors.size()));
                if (!rdActors.contains(rdActor)) {
                    rdActors.add(rdActor);
                }
            }

            //1-2 dao dien
            List<Director> rdDirectors = new ArrayList<>();
            for (int j = 0; j < rd.nextInt(2)+1; j++) {
                Director rdDirector = directors.get(rd.nextInt(directors.size()));
                if (!rdDirectors.contains(rdDirector)) {
                    rdDirectors.add(rdDirector);
                }
            }


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
                    .country(rdCountry)
                    .genres(rdGenres)
                    .actors(rdActors)
                    .directors(rdDirectors)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void save_episode(){
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            if (movie.getType().equals(MovieType.PHIM_BO)){
                // tạo nhiều tập phim
                Random rd = new Random();
                for (int i = 0; i < rd.nextInt(6)+5; i++) {
                    Episode episode = Episode.builder()
                            .name("Tập " + (i+1))
                            .duration(50)
                            .trailerUrl("https://videos.pexels.com/video-files/3209828/3209828-hd_1280_720_25fps.mp4")
                            .displayOrder(i+1)
                            .status(true)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .publishedAt(LocalDateTime.now())
                            .movie(movie)
                            .build();
                    episodeRespository.save(episode);
                    }

            }
            else {
                Episode episode = Episode.builder()
                        .name("Tập Full")
                        .duration(90)
                        .trailerUrl("https://videos.pexels.com/video-files/3209828/3209828-hd_1280_720_25fps.mp4")
                        .displayOrder(1)
                        .status(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .publishedAt(LocalDateTime.now())
                        .movie(movie)
                        .build();
                episodeRespository.save(episode);

            }
        }
    }

    @Test
    void save_review(){
        Random rd = new Random();
        Faker faker = new Faker();
        List<Movie> movies = movieRepository.findAll();
        List<User> users = userRespository.findAll();

        for (Movie movie : movies) {
            // Random 5 -> 10 reviews
            for (int i = 0; i < rd.nextInt(6) + 5; i++) {
                User rdUser = users.get(rd.nextInt(users.size()));
                Review review = Review.builder()
                        .content(faker.lorem().paragraph())
                        .rating(rd.nextInt(6) + 5)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .movie(movie)
                        .user(rdUser)
                        .build();
                reviewRespository.save(review);
            }
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

//    @Test
//    void movie_methods_test() {
//        //SELECT
//        //SELECT ALL
//        List<Movie> movies = movieRepository.findAll();
//        System.out.println("movies size " + movies.size());
//
//        List<Movie> movieByIds = movieRepository.findAllById(List.of(1, 2, 3));
//
//        Movie movie = movieRepository.findById(1).orElse(null);
//
//        movie.setName("Đào,Phở,Piano");
//        movieRepository.save(movie);
//
//
//    }

    @Test
    void save_history(){
        Random rd = new Random();
        Faker faker = new Faker();
        List<Movie> movie = movieRepository.findAll();
        List<User> users = userRespository.findAll();
        List<Episode> episodes = episodeRespository.findAll();
        for(User user : users){
            for (int i = 0; i < rd.nextInt(2)+1; i++) {
                Episode rdepisode = episodes.get(rd.nextInt(episodes.size()));
                Movie rdMovie = movie.get(rd.nextInt(movie.size()));
                History history = History.builder()
                        .duration(rd.nextDouble())
                        .episode(rdepisode)
                        .createdAt(LocalDateTime.now())
                        .user(user)
                        .movie(rdMovie)
                        .build();
                historyRespository.save(history);

            }
        }

    }

    @Test
    void save_favorite(){
        Random rd = new Random();
        Faker faker = new Faker();
        List<Movie> movie = movieRepository.findAll();
        List<User> users = userRespository.findAll();
        for(User user : users){
            for (int i = 0; i < rd.nextInt(6)+5; i++) {
                Movie movies = movie.get(rd.nextInt(movie.size()));
                Favorite favorite = Favorite.builder()
                        .user(user)
                        .movie(movies)
                        .createdAt(LocalDateTime.now())
                        .build();
                favoriteRespository.save(favorite);
            }
        }
    }
}

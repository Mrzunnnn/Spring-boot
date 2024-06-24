package org.example.movieapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.entity.User;
import org.example.movieapp.repository.ActorRespository;
import org.example.movieapp.repository.CountryRespository;
import org.example.movieapp.repository.DirectorRespository;
import org.example.movieapp.repository.GenreRespository;
import org.example.movieapp.servive.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final DirectorRespository directorRespository;
    private final CountryRespository countryRespository;
    private final ActorRespository actorRespository;
    private final GenreRespository genreRespository;

    @GetMapping("/admin/movie")
    public String users(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/movie/index";
    }

    @GetMapping("/admin/movie/create")
    public String create(Model model) {
        model.addAttribute("directors", directorRespository.findAll());
        model.addAttribute("actors", actorRespository.findAll());
        model.addAttribute("countries", countryRespository.findAll());
        model.addAttribute("genres", genreRespository.findAll());
        return "admin/movie/create";
    }

    @GetMapping("/admin/movie/{id}/detail")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("directors", directorRespository.findAll());
        model.addAttribute("actors", actorRespository.findAll());
        model.addAttribute("countries", countryRespository.findAll());
        model.addAttribute("genres", genreRespository.findAll());
        return "admin/movie/detail";
    }

}


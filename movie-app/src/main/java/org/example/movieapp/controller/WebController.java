package org.example.movieapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.model.enums.MovieType;
import org.example.movieapp.repository.MovieRepository;
import org.example.movieapp.servive.WebService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final WebService webService;
    @GetMapping
    public String getHomPage(Model model) {
        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam (defaultValue = "1")Integer page,
                                @RequestParam (defaultValue = "12")Integer limit) {
        Page<Movie> pageData = webService.findByType(MovieType.PHIM_BO,true,page,limit);
        model.addAttribute("pageData", pageData);
        model.addAttribute("CurrentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(Model model,
                                @RequestParam (defaultValue = "1")Integer page,
                                @RequestParam (defaultValue = "12")Integer limit) {
        Page<Movie> pageData = webService.findByType(MovieType.PHIM_LE,true,page,limit);
        model.addAttribute("pageData", pageData);
        model.addAttribute("CurrentPage", page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model,
                                      @RequestParam (defaultValue = "1")Integer page,
                                      @RequestParam (defaultValue = "12")Integer limit) {
        Page<Movie> pageData = webService.findByType(MovieType.PHIM_LE,true,page,limit);
        model.addAttribute("pageData", pageData);
        model.addAttribute("CurrentPage", page);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "web/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "web/register";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDatailsPage(@PathVariable int id, @PathVariable String slug, Model model) {
        return "web/chi-tiet-phim";
    }
}

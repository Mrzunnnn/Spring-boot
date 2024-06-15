package org.example.movieapp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.*;
import org.example.movieapp.model.enums.MovieType;
import org.example.movieapp.repository.MovieRepository;
import org.example.movieapp.servive.FavoriteService;
import org.example.movieapp.servive.ReviewService;
import org.example.movieapp.servive.WebService;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final WebService webService;
    private final OrderedFormContentFilter formContentFilter;
    private final FavoriteService favoriteService;
    private final HttpSession session;

    @GetMapping("/")
    public String getHomPage(Model model) {
        List<Movie> dsPhimHot = webService.getHotMovie();
        List<Movie>dsPhimBo = webService.findByType(MovieType.PHIM_BO,true,1,6).getContent();
        List<Movie>dsPhimLe = webService.findByType(MovieType.PHIM_LE,true,1,6).getContent();
        List<Movie>dsPhimChieuRap = webService.findByType(MovieType.PHIM_CHIEU_RAP,true,1,6).getContent();
        model.addAttribute("dsphimBo",dsPhimBo);
        model.addAttribute("dsphimLe",dsPhimLe);
        model.addAttribute("dsPhimChieuRap",dsPhimChieuRap);
        model.addAttribute("dsPhimHot",dsPhimHot);
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
    public String getMovieDetailsPage(Model model,
                                      @PathVariable Integer id,
                                      @PathVariable String slug) {
        Movie movie = webService.findById(id,slug);
        User user = (User) session.getAttribute("currentUser");
        List<Movie> relatedMovies = webService.getRelatedMovies(movie);
        List<Episode> episodes = webService.getEpisodes(movie.getId(),true);
        List<Review> reviews = webService.getReviews(movie.getId());
        if (user != null){
            boolean isFavorite  = favoriteService.isFavorite(id);
            model.addAttribute("isFavorite",isFavorite);
        }
        model.addAttribute("episodes", episodes);
        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviews", reviews);
        return "web/chi-tiet-phim";
    }

    @GetMapping("/xem-phim/{id}/{slug}")
    public String getMovieStreamingDetailsPage(Model model,
                                      @PathVariable Integer id,
                                      @RequestParam String tap,
                                      @PathVariable String slug) {
        User user = (User) session.getAttribute("currentUser");
        // Trả về thông tin phim
        Movie movie = webService.findById(id,slug);


        // Lấy ra thông tin tập phim cần xem
        Optional<Episode> currentEpisode = webService.getEpisode(movie.getId(), true,tap);

        // trả về ds tập phim liên quan
        List<Movie> relatedMovies = webService.getRelatedMovies(movie);

        // trả về danh sách tập phim
        List<Episode> episodes = webService.getEpisodes(movie.getId(),true);

        //trả về danh sách bình luận
        List<Review> reviews = webService.getReviews(movie.getId());

        //

        if (user != null){
            boolean isFavorite  = favoriteService.isFavorite(id);
            model.addAttribute("isFavorite",isFavorite);
        }


        model.addAttribute("episodes", episodes);
        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviews", reviews);
        model.addAttribute("currentEpisode", currentEpisode.orElse(null));
        return "web/xem-phim";
    }

    @GetMapping("/phim-yeu-thich")
    public String getFavoritePage(Model model) {
        List<Favorite> favorites = favoriteService.getAllFavoritesByCurrentUser();
        model.addAttribute("favorites", favorites);
        return "web/phim-yeu-thich";
    }
    @GetMapping("/thong-tin-ca-nhan")
    public String getUser(Model model) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        return "web/thong-tin-ca-nhan";
    }

}

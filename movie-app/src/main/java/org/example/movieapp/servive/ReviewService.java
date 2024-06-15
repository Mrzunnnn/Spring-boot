package org.example.movieapp.servive;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.config.AuthenticationInterceptor;
import org.example.movieapp.entity.Movie;
import org.example.movieapp.entity.Review;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.request.CreatReviewRequest;
import org.example.movieapp.model.request.UpdateReviewRequest;
import org.example.movieapp.repository.MovieRepository;
import org.example.movieapp.repository.ReviewRespository;
import org.example.movieapp.repository.UserRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRespository reviewRespository;
    private final UserRespository userRespository;
    private final MovieRepository movieRepository;
    private final HttpSession session;


    //TODO : Validation hướng dẫn sau (SpringBoot Validation).
    public Review createReview(CreatReviewRequest request) {
        //TODO: Fix user. Về sau user chính là user đang đăng nhập.
        User user = (User) session.getAttribute("currentUser");
        Movie movie = movieRepository.findById(request.getMovieId()).orElseThrow(() -> new RuntimeException("movie not found"));
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .movie(movie)
                .build();
        reviewRespository.save(review);
        return review;
    }

    public Review updateReview(Integer id,UpdateReviewRequest request) {
        Review review = reviewRespository.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found"));
        User user = (User) session.getAttribute("currentUser");
        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("you can't update this review ");
        }
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        reviewRespository.save(review);

        return review;
    }

    public void deleteReview(Integer id) {
        Review review = reviewRespository.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found"));
        //TODO: Fix user. Về sau user chính là user đang đăng nhập.
        User user = (User) session.getAttribute("currentUser");
        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("you can't delete this review ");
        }
        reviewRespository.delete(review);

    }
}

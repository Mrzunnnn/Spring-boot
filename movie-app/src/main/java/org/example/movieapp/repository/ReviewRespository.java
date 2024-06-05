package org.example.movieapp.repository;

import org.example.movieapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRespository extends JpaRepository<Review,Integer> {
    List<Review> findByMovie_IdOrderByCreatedAtDesc(Integer movieId);
}

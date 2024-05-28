package org.example.movieapp.repository;

import org.example.movieapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRespository extends JpaRepository<Review,Integer> {
}

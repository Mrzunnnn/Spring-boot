package org.example.movieapp.repository;

import org.example.movieapp.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRespository extends JpaRepository<Genre,Integer> {
}

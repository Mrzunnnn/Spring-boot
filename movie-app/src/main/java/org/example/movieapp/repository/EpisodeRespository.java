package org.example.movieapp.repository;

import org.example.movieapp.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRespository extends JpaRepository<Episode,Integer> {
    List<Episode> findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer movieId,Boolean status);

}

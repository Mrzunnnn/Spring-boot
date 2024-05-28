package org.example.movieapp.repository;

import org.example.movieapp.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRespository extends JpaRepository<Episode,Integer> {
}

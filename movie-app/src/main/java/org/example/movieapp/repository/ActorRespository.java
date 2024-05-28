package org.example.movieapp.repository;

import org.example.movieapp.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRespository extends JpaRepository<Actor,Integer> {
}

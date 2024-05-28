package org.example.movieapp.repository;

import org.example.movieapp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRespository extends JpaRepository<Country,Integer> {
}

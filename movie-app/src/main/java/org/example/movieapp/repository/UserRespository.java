package org.example.movieapp.repository;

import org.example.movieapp.entity.User;
import org.example.movieapp.model.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByType(UserType type);

    List<User> findByNameContainingIgnoreCase(String keyword);

    List<User> findByAvatarNotNull();

    List<User> findByTypeOrderByNameDesc(UserType type);

    List<User> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    Page<User> findByTypeOrderByNameDesc(UserType type, Pageable pageable);


    Long countByType(UserType type);

    Boolean existsByEmail(String email);
}

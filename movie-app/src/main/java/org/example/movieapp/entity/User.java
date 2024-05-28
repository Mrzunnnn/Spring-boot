package org.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movieapp.model.enums.UserType;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, nullable = false)
    String name;


    @Column(unique = true, nullable = false)
    String email;


    String password;
    String avatar;

    @Enumerated(EnumType.STRING)
    UserType type;


    LocalDate createdAt;
    LocalDate updatedAt;
}

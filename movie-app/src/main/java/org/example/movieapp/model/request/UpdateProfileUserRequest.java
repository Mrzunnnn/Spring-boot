package org.example.movieapp.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UpdateProfileUserRequest {
    @NotEmpty(message = "Tên không được để trống")
    String name;
}



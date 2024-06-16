package org.example.movieapp.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UpdateProfileUserRequest {
    String name;
}



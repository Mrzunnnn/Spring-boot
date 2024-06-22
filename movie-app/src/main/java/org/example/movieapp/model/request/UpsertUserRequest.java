package org.example.movieapp.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movieapp.model.enums.UserType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertUserRequest {

    @NotEmpty(message = "Tên không được để trống")
    String name;

    @NotEmpty(message = "Role không được để trống")
    UserType type;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    String email;
}

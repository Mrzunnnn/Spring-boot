package org.example.movieapp.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePasswordRequest {
    @NotEmpty(message = "Mật khẩu cũ không thể để trống")
    @Length(min = 3, message = "Mật khẩu phải có ít nhất 3 kí tự")
    String oldPassword;
    @NotEmpty(message = "Mật khẩu mới không thể để trống")
    @Length(min = 3, message = "Mật khẩu phải có ít nhất 3 kí tự")
    String newPassword;
    @NotEmpty(message = "Mật khẩu xác nhận không thể để trống")
    @Length(min = 3, message = "Mật khẩu phải có ít nhất 3 kí tự")
    String confirmPassword;
}
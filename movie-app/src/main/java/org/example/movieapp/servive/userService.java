package org.example.movieapp.servive;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.exception.BadRequestException;
import org.example.movieapp.model.request.UpdatePasswordRequest;
import org.example.movieapp.model.request.UpdateProfileUserRequest;
import org.example.movieapp.model.request.UpdateReviewRequest;
import org.example.movieapp.repository.ReviewRespository;
import org.example.movieapp.repository.UserRespository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userService {
    private final HttpSession session;
    private final UserRespository userRespository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public  User updateName(@Valid UpdateProfileUserRequest request) {
        User user = (User) session.getAttribute("currentUser");
        user.setName(request.getName());
        session.setAttribute("currentUser", user);
        return userRespository.save(user);
    }


    public User updatePassword(@Valid UpdatePasswordRequest request) {
        User user = (User) session.getAttribute("currentUser");
        if (!bCryptPasswordEncoder.matches(request.getOldPassword(), user.getPassword())&&request.getNewPassword().length()>=3) {
           throw new BadRequestException("Mật khẩu cũ không chính xác");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())&&request.getNewPassword().length()>=3) {
            throw new BadRequestException("Mật khẩu xác nhận không khớp");
        }

        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        session.setAttribute("currentUser", user);
        return userRespository.save(user);
    }
}

package org.example.movieapp.servive;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
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

    public  User updateName(UpdateProfileUserRequest request) {
        User user = (User) session.getAttribute("currentUser");
        user.setName(request.getName());
        session.setAttribute("currentUser", user);
        return userRespository.save(user);
    }


    public User updatePassword(UpdatePasswordRequest request) {
        User user = (User) session.getAttribute("currentUser");
        if (!bCryptPasswordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return null;
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return null;
        }

        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        session.setAttribute("currentUser", user);
        return userRespository.save(user);
    }
}

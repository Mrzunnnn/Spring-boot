package org.example.movieapp.servive;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.request.LoginRequest;
import org.example.movieapp.repository.UserRespository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRespository userRespository;
    private final HttpSession session;
    public void login(LoginRequest request) {
        User user = userRespository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found"));

        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        session.setAttribute("CurrentUser", user);
    }
}

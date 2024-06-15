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
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRespository userRepository;
    private final HttpSession session;

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }

        // TODO: Luu thong tin user vao session, cookie, database, ...
        session.setAttribute("currentUser", user);
    }
}

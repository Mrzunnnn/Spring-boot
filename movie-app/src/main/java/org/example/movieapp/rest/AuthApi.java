package org.example.movieapp.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.model.request.LoginRequest;
import org.example.movieapp.servive.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginRequest request){
        authService.login(request);
        return ResponseEntity.ok().build();
    }
}

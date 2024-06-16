package org.example.movieapp.rest;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.request.UpdatePasswordRequest;
import org.example.movieapp.model.request.UpdateProfileUserRequest;
import org.example.movieapp.model.request.UpdateReviewRequest;
import org.example.movieapp.servive.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {
    private final userService userService;

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileUserRequest request) {
        User user = userService.updateName(request);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/update-Password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
      User user = userService.updatePassword(request);
      return ResponseEntity.ok(user);
    }
}

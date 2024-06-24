package org.example.movieapp.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.request.*;
import org.example.movieapp.servive.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAPI {
    private final userService userService;

    @PutMapping("/users/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileUserRequest request) {
        User user = userService.updateName(request);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/users/update-Password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
      User user = userService.updatePassword(request);
      return ResponseEntity.ok(user);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UpsertUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/admin/users/{id}/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @PathVariable Integer id, @RequestBody ResetPasswordRequest request){
        return ResponseEntity.ok(userService.resetPassword(id,request));
    }
    @PutMapping("/admin/users/{id}")
    ResponseEntity<?> adminUpdateProfile( @Valid @RequestBody UpsertUserRequest request,
                                          @PathVariable Integer id) {
        return ResponseEntity.ok(userService.updateProfile(id, request));
    }
}

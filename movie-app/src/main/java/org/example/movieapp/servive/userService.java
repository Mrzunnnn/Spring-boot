package org.example.movieapp.servive;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.exception.BadRequestException;
import org.example.movieapp.model.request.*;
import org.example.movieapp.repository.UserRespository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class userService {
    private final HttpSession session;
    private final UserRespository userRespository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRespository.findAll(Sort.by("createdAt").descending());
    }

    public User getUserById(int id) {
        return userRespository.findById(id).orElse(null);
    }

    public  User updateName( UpdateProfileUserRequest request) {
        User user = (User) session.getAttribute("currentUser");
        user.setName(request.getName());
        session.setAttribute("currentUser", user);
        return userRespository.save(user);
    }


    public User updatePassword( UpdatePasswordRequest request) {
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

    public User createUser(UpsertUserRequest request) {
        Optional<User> userOptional = userRespository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new BadRequestException("Email đã được sử dụng");
        }

        User user = User.builder()
                .name(request.getName())
                .avatar("https://placehold.co/600x400?text="+request.getName().substring(0,1).toUpperCase())
                .email(request.getEmail())
                .type(request.getType())
                .password(passwordEncoder.encode("123"))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
        return userRespository.save(user);
    }

    public User resetPassword(Integer id ){
        User user = userRespository.findById(id).orElse(null);
        user.setPassword(passwordEncoder.encode("123"));
        return userRespository.save(user);
    }

    public User updateProfile(Integer id,UpsertUserRequest request){
        User user = userRespository.findById(id).orElse(null);
        user.setName(request.getName());
        user.setType(request.getType());
        return userRespository.save(user);
    }

}

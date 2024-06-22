package org.example.movieapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final org.example.movieapp.servive.userService userService;

    @GetMapping("/admin/users")
    public String users(Model model) {
        List<User> users =  userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @GetMapping("/admin/users/create")
    public String create(Model model) {
        return "admin/user/create";
    }

    @GetMapping("/admin/users/{id}/detail")
    public String detail(@PathVariable Integer id, Model model) {
         model.addAttribute("user", userService.getUserById(id));
        return "admin/user/detail";
    }

}

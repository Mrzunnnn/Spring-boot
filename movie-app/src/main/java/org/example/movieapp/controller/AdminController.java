package org.example.movieapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("/admin/users")
    public String users(Model model) {
        return "admin/user/index";
    }

    @GetMapping("/admin/users/create")
    public String create(Model model) {
        return "admin/user/create";
    }

    @GetMapping("/admin/users/{id}/detail")
    public String detail(@PathVariable Integer id, Model model) {
        return "admin/user/detail";
    }

}

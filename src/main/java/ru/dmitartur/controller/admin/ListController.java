package ru.dmitartur.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

@Controller
@RequestMapping(value="/admin")
public class ListController {

    public final UserService userService;

    @Autowired
    public ListController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/list")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("Users", userService.getAll());
        model.addAttribute("roles", Role.values());
        return "list";
    }
}

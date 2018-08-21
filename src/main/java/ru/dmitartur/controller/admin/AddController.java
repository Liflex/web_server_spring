package ru.dmitartur.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/admin")
public class AddController {

    private final UserService userService;

    @Autowired
    public AddController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/add")
    public void addNewUser(@ModelAttribute("user") User user, HttpServletResponse response) {
        if (userService.get(user.getUsername()) == null) userService.add(user);
        try {
            response.sendRedirect("/admin/list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ru.dmitartur.controller.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@RequestMapping(value="/admin")
public class ListController {

    public final UserService userService;

    @Autowired
    public ListController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("Users", userService.getAll());
        model.addAttribute("User", new User());
        return "list";
    }
}

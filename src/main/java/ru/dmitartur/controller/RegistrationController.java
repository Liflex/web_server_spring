package ru.dmitartur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;
import ru.dmitartur.validator.UserValidatorRegistration;

import java.util.Iterator;

@Controller
public class RegistrationController {

    private final UserService userService;

    private final UserValidatorRegistration userValidatorRegistration;

    @Autowired
    public RegistrationController(UserService userService, UserValidatorRegistration userValidatorRegistration) {
        this.userService = userService;
        this.userValidatorRegistration = userValidatorRegistration;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(@ModelAttribute("user")User user, BindingResult bindingResult, Model model) {
        userValidatorRegistration.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", "user", user);
        }
        userService.add(user);
        //model.addAttribute("message", "Registered is successfully.");

        return new ModelAndView("login", "", new Object());
    }
}

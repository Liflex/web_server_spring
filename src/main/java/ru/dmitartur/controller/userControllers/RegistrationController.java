package ru.dmitartur.controller.userControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;
import ru.dmitartur.validator.UserValidatorImpl;

@Controller
public class RegistrationController {

    private final UserService userService;

    private final UserValidatorImpl userValidatorImpl;

    @Autowired
    public RegistrationController(UserService userService, UserValidatorImpl userValidatorImpl) {
        this.userService = userService;
        this.userValidatorImpl = userValidatorImpl;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(@ModelAttribute("user")User user, BindingResult bindingResult, Model model) {
        userValidatorImpl.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", "user", user);
        }

        return new ModelAndView("/", "user", user);
    }
}

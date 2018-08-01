package ru.dmitartur.controller.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/admin")
public class AddController {

    @Autowired
    public UserService userService;


    @PostMapping("/add")
    protected  String addUser(User user) {
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/list";
    }

}

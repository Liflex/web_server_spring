package ru.dmitartur.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.service.abstraction.UserService;

@Controller
@RequestMapping(value="/")
public class HomeController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
	public String test() {
		return "home";
	}
}

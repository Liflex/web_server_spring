package ru.dmitartur.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.service.interf.UserService;

@Controller
@RequestMapping(value="/")
public class HomeController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
	public String test() {
		return "home";
	}

	@GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("Users", userService.getAll());
        return "list";
    }
}

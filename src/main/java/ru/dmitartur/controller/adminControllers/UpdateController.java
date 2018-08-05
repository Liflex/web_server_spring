package ru.dmitartur.controller.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/admin")
public class UpdateController {

    public final UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update")
    protected  String updateUser(HttpServletRequest req) {
        try {
//            userService.update(new User(Integer.parseInt(req.getParameter("id")),
//                    req.getParameter("name"),
//                    req.getParameter("login"),
//                    req.getParameter("pass"),
//                    req.getParameter("role")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/list";
    }
}

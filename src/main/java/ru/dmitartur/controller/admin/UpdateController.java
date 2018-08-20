package ru.dmitartur.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class UpdateController {

    private final UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    protected  void updateUser(@ModelAttribute("user")User user, HttpServletResponse response, HttpServletRequest req) {
        try {
            if (req.getParameter("role_id").equals("ADMIN")) {
                user.setRoles(Collections.singletonList(new Role(2)));
            } else {
                user.setRoles(Collections.singletonList(new Role(1)));
            }
            userService.update(user);
            response.sendRedirect("/admin/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.dmitartur.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

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
            if (req.getParameter("roles").equals("ADMIN")) {
            } else {
                user.setRoles(Collections.singletonList(new Role(1)));
            }
            try {
                req.getParameter("active_status").equals("true");
                user.setActive(true);
            } catch (Exception ignored) {
                user.setActive(false);
            }
            userService.update(user);
            response.sendRedirect("/admin/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

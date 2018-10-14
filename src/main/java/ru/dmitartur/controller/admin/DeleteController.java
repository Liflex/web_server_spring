package ru.dmitartur.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmitartur.service.abstraction.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value="/admin")
public class DeleteController {

    private final UserService userService;

    @Autowired
    public DeleteController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete")
    public  void deleteUser(HttpServletRequest req, HttpServletResponse response) {
        try {
            userService.delete(Integer.parseInt(req.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("/admin/list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

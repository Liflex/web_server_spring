package ru.dmitartur.controller.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.dmitartur.service.abstraction.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/admin")
public class DeleteController {

    @Autowired
    public UserService userService;

    @PostMapping("/delete")
    protected  String deleteUser(HttpServletRequest req) {
        try {
            userService.delete(Integer.parseInt(req.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //resp.sendRedirect("/admin/list");
        return "redirect:/admin/list";
    }
}

package ru.dmitartur.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.dmitartur.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class  LoginController {

    @GetMapping (value = "/")
    public ModelAndView login() {
        return new ModelAndView("login", "user", new User());
    }

    @GetMapping(value = "/logout")
    public void logout(final HttpServletRequest request, final HttpServletResponse response, Model model) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        response.sendRedirect("/admin/list");
    }

    @GetMapping (value = "/oauth_login")
    public String logidfn() {
        return "ok";
    }
}

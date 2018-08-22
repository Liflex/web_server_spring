package ru.dmitartur.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role ="";
        for (GrantedAuthority s : auth.getAuthorities()) {
            role = s.getAuthority();
        }
        String targetUrl = "";
        if(role.contains("USER")) {
            targetUrl = "/user";
        } else if(role.contains("ADMIN")) {
            targetUrl = "/admin/list";
        }
        return targetUrl;
    }
}

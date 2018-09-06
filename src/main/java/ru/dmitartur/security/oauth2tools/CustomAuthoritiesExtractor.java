package ru.dmitartur.security.oauth2tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.dmitartur.service.abstraction.UserService;

import java.util.List;
import java.util.Map;

@Component ("CustomAuthoritiesExtractor")
public class CustomAuthoritiesExtractor implements AuthoritiesExtractor {

    private final UserService userService;

    @Autowired
    public CustomAuthoritiesExtractor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<GrantedAuthority> extractAuthorities
            (Map<String, Object> map) {
        List<GrantedAuthority> list = (List<GrantedAuthority>) userService.get((String)map.get("sub")).getAuthorities();
       return list;
    }
}

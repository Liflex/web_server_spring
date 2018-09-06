package ru.dmitartur.security.oauth2tools;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import java.util.Map;

@Component
public class CustomPrincipalExtractor implements PrincipalExtractor {

    private final UserService userService;

    public CustomPrincipalExtractor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
            User user = userService.get((String)map.get("sub"));
            if (user==null) {
                userService.add(new User((String)map.get("sub")));
                return map.get("sub");
            }
            return map.get("sub");
    }
}

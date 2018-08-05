package ru.dmitartur.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import java.util.ResourceBundle;

@Component
public class UserValidatorImpl implements Validator {

    private final UserService userService;


    @Autowired
    public UserValidatorImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmpty(errors, "username", "Required", "This field is required.");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username", "Username must be between 4 and 32 characters.");
        }

//        if (userService.get(user.getUsername()) != null) {
//            errors.rejectValue("username","Duplicate.userForm.username", "Such username already exists.");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required", "This field is required.");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password", "Password must be over 6 characters.");
        }

//        if (!user.getConfirmPassword().equals(user.getPassword())) {
//            errors.rejectValue("confirmPassword", "Different.userForm.password");
//        }
    }
}

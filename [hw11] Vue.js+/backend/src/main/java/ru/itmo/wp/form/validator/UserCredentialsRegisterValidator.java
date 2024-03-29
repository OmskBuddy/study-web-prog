package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserRegisterCredentials;
import ru.itmo.wp.service.UserService;

@Component
public class UserCredentialsRegisterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterCredentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserRegisterCredentials registerForm = (UserRegisterCredentials) target;
            if (!userService.isLoginVacant(registerForm.getLogin())) {
                errors.reject( "login.is-in-use", "login is in use already");
            }
        }
    }
}

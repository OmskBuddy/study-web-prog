package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserEnterCredentials;
import ru.itmo.wp.form.UserRegisterCredentials;
import ru.itmo.wp.service.UserService;

@Component
public class UserCredentialsEnterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsEnterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserEnterCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserEnterCredentials enterForm = (UserEnterCredentials) target;
            if (userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.reject("invalid-login-or-password", "Invalid login or password");
            }
        }
    }
}

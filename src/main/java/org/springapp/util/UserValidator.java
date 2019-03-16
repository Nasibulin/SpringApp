package org.springapp.util;

import org.springapp.entity.User;
import org.springapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass==User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (userService.findByEmail(user.getEmail()) != null) {
            errors
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (!EmailUtil.isEmailFormat(user.getEmail())) {
            errors
                    .rejectValue("email", "error.user",
                            "E-mail address you entered is incorrect or invalid");
        }

        if (!user.getPassword().equals(user.getRepassword())) {
            errors
                    .rejectValue("password", "error.user",
                            "Password does not match...");
        }

    }
}

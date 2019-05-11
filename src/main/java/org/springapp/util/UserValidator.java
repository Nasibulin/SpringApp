package org.springapp.util;

import org.springapp.entity.User;
import org.springapp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (userService.findByEmail(user.getEmail()) != null) {
            errors
                    .rejectValue("email", "error.user",
                                 Constant.ParamError.EMAIL_ADDRESS.getDesc());
        }
        if (!EmailUtil.isEmailFormat(user.getEmail())) {
            errors
                    .rejectValue("email", "error.user",
                                 Constant.ParamError.EMAIL_ADDRESS.getDesc());
        }

        if (!user.getPassword().equals(user.getRepassword())) {
            errors
                    .rejectValue("password", "error.user",
                                 Constant.ParamError.PASSWORD.getDesc());
        }

    }
}

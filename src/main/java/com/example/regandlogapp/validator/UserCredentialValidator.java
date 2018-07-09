package com.example.regandlogapp.validator;

import com.example.regandlogapp.model.User;
import com.example.regandlogapp.model.UserCredential;
import com.example.regandlogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserCredentialValidator implements Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCredential.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCredential userCredential = (UserCredential) target;

        User user = userService.findByUsername(userCredential.getUsername());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (user == null) {
            errors.rejectValue("username", "Wrong.userLoginForm.username");
            errors.rejectValue("password", "Wrong.userLoginForm.password");
        }
        else
            if (!bCryptPasswordEncoder.matches(userCredential.getPassword(), user.getPassword()))
                errors.rejectValue("password", "Wrong.userLoginForm.password");

    }

}

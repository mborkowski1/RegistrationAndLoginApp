package com.example.regandlogapp.validator;

import com.example.regandlogapp.model.User;
import com.example.regandlogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,32}";

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
        Matcher matcherEmail = patternEmail.matcher(user.getEmail());

        Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcherPassword = patternPassword.matcher(user.getPassword());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 2 || user.getUsername().length() > 32)
            errors.rejectValue("username", "Size.userRegistrationForm.username");
        if (userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "Duplicate.userRegistrationForm.username");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!matcherEmail.matches())
            errors.rejectValue("email", "Complex.userRegistrationForm.email");
        if (userService.findByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "Duplicate.userRegistrationForm.email");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
            errors.rejectValue("password", "Size.userRegistrationForm.password");
        if (!matcherPassword.matches())
            errors.rejectValue("password", "Complex.userRegistrationForm.password");
        if (!user.getPasswordConfirm().equals(user.getPassword()))
            errors.rejectValue("passwordConfirm", "Diff.userRegistrationForm.passwordConfirm");
    }

}

package com.goit.startup.validator;

import com.goit.startup.entity.User;
import com.goit.startup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Validator for {@link User} class,
 * implements {@link UserValidator} interface.
 *
 * @author Pavel Perevoznyk
 * @version 1.0
 */

@Component
@PropertySource(value = "classpath:validation_en_US.properties")
public class UserValidator implements Validator {

    private UserService userService;

    @Value("${Size.username.min}")
    private int minUsernameLength;

    @Value("${Size.username.max}")
    private int maxUsernameLength;


    @Value("${Size.password.min}")
    private int minPasswordLength;

    @Value("${Size.password.max}")
    private int maxPasswordLength;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUsername().contains(" ")) {
            errors.rejectValue("username", "Blanks.username");
        }

        if (user.getUsername().length() < minUsernameLength || user.getUsername().length() > maxUsernameLength) {
            errors.rejectValue("username", "Size.username");
        }

        try {
            if (userService.loadUserByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.username");
            }
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getPassword().contains(" ")) {
            errors.rejectValue("password", "Blanks.password");
        }

        if (user.getPassword().length() < minPasswordLength || user.getPassword().length() > maxPasswordLength) {
            errors.rejectValue("password", "Size.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.password");
        }
    }


}
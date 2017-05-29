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
 * implements {@link Validator} interface.
 *
 * @author Pavel Perevoznyk
 * @version 1.0
 */

@Component
@PropertySource(value = "classpath:validation.properties")
public class UserEditValidator implements Validator {

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * A minimal length of user`s name
     */
    @Value("${Size.user.username.min}")
    private int minUsernameLength;

    /**
     * A maximal length of user`s name
     */
    @Value("${Size.user.username.max}")
    private int maxUsernameLength;

    /**
     * Constructor
     *
     * @param userService an instance of implementation {@link UserService} interface.
     */
    @Autowired
    public UserEditValidator(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method determines which classes can support this {@link Validator}
     *
     * @param aClass the {@link Class} that this {@link Validator} is
     *               being asked if it can
     * @return true if this {@link Validator} can indeed instances of the
     * supplied aClass or false otherwise
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Method validates the supplied  object
     *
     * @param o      an instance of {@link User} that is to be validated
     * @param errors contextual state about the validation process
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUsername().contains(" ")) {
            errors.rejectValue("username", "Blanks.user.username");
        }

        if (user.getUsername().length() < minUsernameLength || user.getUsername().length() > maxUsernameLength) {
            errors.rejectValue("username", "Size.user.username");
        }

        try {
            if (userService.loadUserByUsername(user.getUsername()) != null
                    && user.getId() != userService.getByUsername(user.getUsername()).getId()) {
                errors.rejectValue("username", "Duplicate.user.username");
            }
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
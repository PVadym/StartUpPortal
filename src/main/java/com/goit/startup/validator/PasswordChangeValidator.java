package com.goit.startup.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * Validator for user's changed password
 *
 * @author Pavel Perevoznyk
 * @version 1.0
 */

@Component
@PropertySource(value = "classpath:validation.properties")
public class PasswordChangeValidator {

    /**
     * A minimal length of user`s password
     */
    @Value("${Size.user.password.min}")
    private int minPasswordLength;

    /**
     * A maximal length of user`s password
     */
    @Value("${Size.user.password.max}")
    private int maxPasswordLength;

    /**
     * No Blanks in password message
     */
    @Value("${Blanks.user.password}")
    private String blanks;

    /**
     * Length of password is incorrect message
     */
    @Value("${Size.user.password}")
    private String length;

    /**
     * Passwords don't match message
     */
    @Value("${Different.user.password}")
    private String different;

    /**
     * Method validates the supplied  object
     *
     * @param password        password to be validated
     * @param confirmPassword confirm password to be validated
     */
    public String validate(String password, String confirmPassword) {


        if (password.contains(" ")) {
            return blanks;
        }

        if (password.length() < minPasswordLength || password.length() > maxPasswordLength) {
            return length;
        }

        if (!password.equals(confirmPassword)) {
            return different;
        }
        return "";
    }
}
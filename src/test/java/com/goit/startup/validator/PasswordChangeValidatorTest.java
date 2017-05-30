package com.goit.startup.validator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 30.05.2017.
 */
@PropertySource(value = "classpath:validation.properties")
public class PasswordChangeValidatorTest {

    /**
     * An instance of {@link PasswordChangeValidator}
     */

    private static PasswordChangeValidator passwordChangeValidator;

    private String password;

    private String confirmPassword;

    private final String MESSAGE_BLANK = "Password must not contain blanks.";
    private final String MESSAGE_LENGTH = "Password must be between 4 and 32 characters.";
    private final String MESSAGE_DIFFERENT = "Passwords don't match.";

    @BeforeClass
    public static void setUp() throws Exception {
        passwordChangeValidator = new PasswordChangeValidator();
        Class refValidator = passwordChangeValidator.getClass();
        Field blanks = refValidator.getDeclaredField("blanks");
        Field minPasswordLength = refValidator.getDeclaredField("minPasswordLength");
        Field maxPasswordLength = refValidator.getDeclaredField("maxPasswordLength");
        Field length = refValidator.getDeclaredField("length");
        Field different = refValidator.getDeclaredField("different");
        blanks.setAccessible(true);
        minPasswordLength.setAccessible(true);
        maxPasswordLength.setAccessible(true);
        length.setAccessible(true);
        different.setAccessible(true);
        blanks.set(passwordChangeValidator,"Password must not contain blanks.");
        minPasswordLength.setInt(passwordChangeValidator,4);
        maxPasswordLength.setInt(passwordChangeValidator,32);
        length.set(passwordChangeValidator,"Password must be between 4 and 32 characters.");
        different.set(passwordChangeValidator,"Passwords don't match.");
    }



    @Test
    public void validateWhiteSpace() throws Exception {
        password = "  ";
        assertEquals(getValidate(), MESSAGE_BLANK);
    }

    @Test
    public void validateWrongLength() throws Exception {
        password = "1";
        assertEquals(getValidate(), MESSAGE_LENGTH);

        password = "zxcvbnm,.asdfghjqertyuioosdfghjkkdfjhdfififenjirghrgjrgjorgorgrej";
        assertEquals(getValidate(), MESSAGE_LENGTH);
    }

    @Test
    public void validateDifferent() throws Exception {
        password = "12345";
        confirmPassword = "1234567";

        assertEquals(getValidate(), MESSAGE_DIFFERENT);

    }

    @Test
    public void validateTest() throws Exception {
        password = "1234567";
        confirmPassword = "1234567";

        assertEquals(getValidate(), "");

    }

    private String getValidate() {
        return passwordChangeValidator.validate(password, confirmPassword);
    }


}
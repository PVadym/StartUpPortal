package com.goit.startup.validator;

import com.goit.startup.entity.Startup;
import com.goit.startup.service.StartupService;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Class for testing {@link StartupValidator}.
 *
 * @author Slava Makhinich.
 * Created on 29.05.2017.
 * @version 1.0
 */
@PropertySource(value = "classpath:validation.properties")
public class StartupValidatorTest {

    /**
     * An instance of implementation {@link StartupService} interface.
     */
    private StartupService startupService;

    /**
     * An instance of {@link StartupValidator}.
     */
    private StartupValidator validator;

    /**
     * An instance of  {@link Errors} class.
     */
    private Errors errors;

    /**
     * An instance of  {@link Startup} class.
     */
    private Startup startup;

    /**
     * Constructor
     */
    public StartupValidatorTest() {
        this.errors = mock(Errors.class);
        this.startupService = mock(StartupService.class);
        this.validator = new StartupValidator(startupService);
        this.startup = new Startup();
        this.startup.setName("some name");
        startup.setId(1);
    }

    @Test
    public void supports() throws Exception {
        assertTrue(validator.supports(Startup.class));
    }

    @Test
    public void validateEmptyOrWhitespace() throws Exception {
        validator.validate(startup, errors);
        verify(errors).rejectValue("name", "Required", null, null);
        verify(errors).rejectValue("needInvestment", "Required", null, null);
    }

    @Test
    public void validateExistNameAndSameId() throws Exception {
        when(startupService.getByName(startup.getName())).thenReturn(startup);
        validator.validate(startup, errors);
        verify(errors, never()).rejectValue("name", "Duplicate.startup.name");
    }

    @Test
    public void validateNewName() throws Exception {
        startup.setName("null");
        when(startupService.getByName(startup.getName())).thenThrow(new NullPointerException());
        validator.validate(startup, errors);
        verify(errors, never()).rejectValue("name", "Duplicate.startup.name");
    }

    @Test
    public void validateExistNameAndDifferentId() throws Exception {
        Startup newStartup = new Startup();
        newStartup.setName(this.startup.getName());
        newStartup.setId(2);
        when(startupService.getByName(startup.getName())).thenReturn(startup);
        validator.validate(newStartup, errors);
        verify(errors).rejectValue("name", "Duplicate.startup.name");
    }

    @Test
    public void validateNameLength() throws Exception {
        Class v = validator.getClass();
        Field minLenght = v.getDeclaredField("minNameLength");
        Field maxLenght = v.getDeclaredField("maxNameLength");
        minLenght.setAccessible(true);
        maxLenght.setAccessible(true);
        maxLenght.setInt(validator,16);
        minLenght.setInt(validator, 4);
        startup.setName("aaaa");
        validator.validate(startup, errors);
        startup.setName("aaaaaaaaaaaaaaaa");
        validator.validate(startup, errors);
        verify(errors, never()).rejectValue("name", "Size.startup.name");
        startup.setName("aaaaaaaaaaaaaaaaa");
        validator.validate(startup, errors);
        startup.setName("aaa");
        validator.validate(startup, errors);
        verify(errors, times(2)).rejectValue("name", "Size.startup.name");
    }

    @Test
    public void validateMinInvestment() throws Exception {
        startup.setNeedInvestment(10);
        startup.setMinInvestment(-1);
        validator.validate(startup, errors);
        verify(errors, times(1)).rejectValue("minInvestment", "Amount.startup.minInvestment");
        startup.setMinInvestment(0);
        validator.validate(startup, errors);
        verify(errors, times(2)).rejectValue("minInvestment", "Amount.startup.minInvestment");
        startup.setMinInvestment(1);
        validator.validate(startup, errors);
        verify(errors, times(2)).rejectValue("minInvestment", "Amount.startup.minInvestment");
        startup.setMinInvestment(11);
        validator.validate(startup, errors);
        verify(errors, times(3)).rejectValue("minInvestment", "Amount.startup.minInvestment");
    }

    @Test
    public void validateNeedInvestment() throws Exception {
        startup.setNeedInvestment(1);
        validator.validate(startup, errors);
        verify(errors, never()).rejectValue("needInvestment", "Amount.startup.needInvestment");
        startup.setNeedInvestment(0);
        validator.validate(startup, errors);
        startup.setNeedInvestment(-1);
        validator.validate(startup, errors);
        verify(errors, times(2)).rejectValue("needInvestment", "Amount.startup.needInvestment");
    }
}
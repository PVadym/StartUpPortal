package com.goit.startup.validator;

import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Validator for {@link User} class,
 * implements {@link StartupValidator} interface.
 *
 * @author Pavel Perevoznyk
 * @version 1.0
 */

@Component
@PropertySource(value = "classpath:validation.properties")
public class StartupValidator implements Validator {

    private StartupService startupService;

    @Value("${Size.startup.name.min}")
    private int minNameLength;

    @Value("${Size.startup.name.max}")
    private int maxNameLength;

    @Autowired
    public StartupValidator(StartupService startupService) {
        this.startupService = startupService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Startup.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Startup startup = (Startup) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");

        if (startup.getName().length() < minNameLength || startup.getName().length() > maxNameLength) {
            errors.rejectValue("name", "Size.startup.name");
        }

        try {
            Startup startupWithSameName = startupService.getByName(startup.getName());
            if (startupWithSameName != null && startupWithSameName.getId() != startup.getId()) {
                errors.rejectValue("name", "Duplicate.startup.name");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "needInvestment", "Required");

        if (startup.getNeedInvestment() <= 0) {
            errors.rejectValue("needInvestment", "Amount.startup.needInvestment");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minInvestment", "Required");
        if (startup.getMinInvestment() <= 0 || startup.getMinInvestment() > startup.getNeedInvestment()) {
            errors.rejectValue("minInvestment", "Amount.startup.minInvestment");
        }
    }
}
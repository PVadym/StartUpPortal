package com.goit.startup.validator;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.service.InvestmentService;
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
 * implements {@link Validator} interface.
 *
 * @author Pavel Perevoznyk
 * @version 1.0
 */

@Component
@PropertySource(value = "classpath:validation.properties")
public class InvestmentValidator implements Validator {

    /**
     * An instance of implementation {@link InvestmentService} interface.
     */
    private InvestmentService investmentService;

    /**
     * Constructor
     *
     * @param investmentService an instance of implementation {@link InvestmentService} interface.
     */
    @Autowired
    public InvestmentValidator(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    /**
     * Method determines which classes can support this {@link Validator}
     *
     * @param aClass the {@link Class} that this {@link Validator} is
     * being asked if it can
     * @return true if this {@link Validator} can indeed instances of the
     * supplied aClass or false otherwise
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Investment.class.equals(aClass);
    }

    /**
     * Method validates the supplied  object
     *
     * @param o an instance of {@link Investment}  that is to be validated
     * @param errors contextual state about the validation process
     */
    @Override
    public void validate(Object o, Errors errors) {
        Investment investment = (Investment) o;

        if (investment.getAmount() < investment.getStartup().getMinInvestment()) {
            errors.rejectValue("amount", "Amount.investment.minInvestment");
        }
    }
}
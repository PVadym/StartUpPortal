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
 * implements {@link InvestmentValidator} interface.
 *
 * @author Pavel Perevoznyk
 * @version 1.0
 */

@Component
@PropertySource(value = "classpath:validation.properties")
public class InvestmentValidator implements Validator {

    private InvestmentService investmentService;

    @Autowired
    public InvestmentValidator(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Investment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Investment investment = (Investment) o;


        if (investment.getAmount() < investment.getStartup().getMinInvestment()) {
            errors.rejectValue("amount", "Amount.investment.minInvestment");
        }

    }
}
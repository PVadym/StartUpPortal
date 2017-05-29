package com.goit.startup.validator;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.service.InvestmentService;
import org.junit.Test;
import org.springframework.validation.Errors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Вадим on 29.05.2017.
 */
public class InvestmentValidatorTest {

    /**
     * An instance of implementation {@link InvestmentService} interface.
     */
    private InvestmentService investmentService;

    /**
     * An instance of implementation {@link InvestmentValidator} interface.
     */
    private InvestmentValidator investmentValidator;

    /**
     * An instance of  {@link Errors} class.
     */
    private Errors errors;

    public InvestmentValidatorTest() {

        this.errors = mock(Errors.class);
        this.investmentService = mock(InvestmentService.class);
        this.investmentValidator = new InvestmentValidator(investmentService);

    }

    @Test
    public void supports() throws Exception {

        assertTrue(investmentValidator.supports(Investment.class));

    }

    @Test
    public void validate() throws Exception {

        Investment investment = new Investment();
        Startup startup = new Startup();
        startup.setMinInvestment(100);
        investment.setAmount(150);
        investment.setStartup(startup);
        investmentValidator.validate(investment,errors);
        verify(errors,never()).rejectValue("amount", "Amount.investment.minInvestment");

        investment.setAmount(50);
        investmentValidator.validate(investment,errors);
        verify(errors).rejectValue("amount", "Amount.investment.minInvestment");





    }

}
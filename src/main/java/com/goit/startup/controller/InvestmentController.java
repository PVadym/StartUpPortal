package com.goit.startup.controller;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.service.InvestmentService;
import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import com.goit.startup.validator.InvestmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class is responsible of processing {@link com.goit.startup.entity.Investment} entity related requests
 *
 * @author Perevoznyk Pavlo
 *         created on 14 may 2017
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/investments")
public class InvestmentController {

    /**
     * An instance of implementation {@link StartupService} interface.
     */
    private StartupService startupService;

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * An instance of implementation {@link InvestmentService} interface.
     */
    private InvestmentService investmentService;

    /**
     * An instance of {@link InvestmentValidator}.
     */
    private InvestmentValidator investmentValidator;

    /**
     * Constructor
     *
     * @param startupService      an instance of implementation {@link StartupService} interface.
     * @param userService         an instance of implementation {@link UserService} interface.
     * @param investmentService   An instance of implementation {@link InvestmentService} interface.
     * @param investmentValidator an instance of {@link InvestmentValidator}.
     */
    @Autowired
    public InvestmentController(StartupService startupService, UserService userService,
                                InvestmentService investmentService, InvestmentValidator investmentValidator) {
        this.startupService = startupService;
        this.userService = userService;
        this.investmentService = investmentService;
        this.investmentValidator = investmentValidator;
    }

    /**
     * The method defines models and view for page to making investment.
     *
     * @param startupId id of start-p to which user wants to invest.
     * @return models and view for page to making investment.
     */
    @RequestMapping(value = "/invest/{startupId}", method = RequestMethod.GET)
    public ModelAndView investPage(@PathVariable(name = "startupId") long startupId) {
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = startupService.get(startupId);

        String investorName = userService.getAuthenticatedUser().getUsername();

        Investment investment = new Investment();
        User investor = userService.getByUsername(investorName);
        investment.setInvestor(investor);
        investment.setStartup(startup);
        modelAndView.addObject("investment", investment);
        modelAndView.setViewName("makeInvestment");
        return modelAndView;
    }

    /**
     * The method adds a new investment into DB.
     *
     * @param investment    a new investment, instance of {@link Investment}.
     * @param bindingResult an instance of implementation {@link BindingResult}.
     * @return an address of startup if investment was added successfully, or address for making investment otherwise.
     */
    @RequestMapping(value = "/invest", method = RequestMethod.POST)
    public String investPage(Investment investment, BindingResult bindingResult) {
        long startupId = investment.getStartup().getId();
        Startup startup = startupService.get(startupId);
        User investor = userService.getAuthenticatedUser();
        investment.setStartup(startup);
        investment.setInvestor(investor);
        investmentValidator.validate(investment, bindingResult);
        if (bindingResult.hasErrors()) {
            return "makeInvestment";
        }
        investmentService.update(investment);
        return "redirect:/startups/" + startup.getId();
    }

    /**
     * Method for removing investment.
     *
     * @param investmentId an id of investment to removing.
     * @return an address of user's page.
     */
    @RequestMapping(value = "/delete/{investmentId}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "investmentId") long investmentId) {
        Startup startup = investmentService.get(investmentId).getStartup();
        if (investmentService.get(investmentId).getInvestor().equals(userService.getAuthenticatedUser())
                || userService.isAuthenticatedAdmin()) {
            startup.getInvestments().remove(investmentService.get(investmentId));
            startupService.update(startup);
        }
        return "redirect:/user/" + userService.getAuthenticatedUser().getUsername() + "/false";
    }
}

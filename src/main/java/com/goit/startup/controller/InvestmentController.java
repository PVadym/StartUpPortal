package com.goit.startup.controller;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.service.InvestmentService;
import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import org.springframework.stereotype.Controller;
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

    private StartupService startupService;

    private UserService userService;

    private InvestmentService investmentService;


    @RequestMapping(value = "/invest/{startupId}/{investorName}", method = RequestMethod.GET)
    public ModelAndView investPage(@PathVariable(name = "startupId") long startupId,
                                   @PathVariable(name = "investorName") String investorName){
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = startupService.get(startupId);
        Investment investment = new Investment();
        User investor = userService.getByUsername(investorName);
        investment.setAuthor(investor);
        investment.setStartup(startup);
        modelAndView.addObject("investment", new Investment());
        modelAndView.setViewName("makeInvestment");
        return modelAndView;
    }

    @RequestMapping(value = "/invest", method = RequestMethod.POST)
    public String investPage(Investment investment){
        System.out.println(investment.getAuthor().getId());
        System.out.println(investment.getStartup().getId());
        long startupId = investment.getStartup().getId();
        Startup startup = startupService.get(startupId);
//        investment.setStartup(startup);
//        startup.makeInvestment(investment);
//        startupService.update(startup);
        return "redirect:/startups/" + startup.getId();
    }
}

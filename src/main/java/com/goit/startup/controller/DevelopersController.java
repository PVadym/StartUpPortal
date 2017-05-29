package com.goit.startup.controller;

import com.goit.startup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class for directing to the developers page
 *
 * @author Slava Makhinich
 * created on 21.05.2017.
 * @version 1.0
 */
@Controller
public class DevelopersController {

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * Constructor
     *
     * @param userService an instance of implementation {@link UserService} interface
     */
    @Autowired
    public DevelopersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method creates ModelAndView for developers page.
     *
     * @return an ModelAndView for developers page.
     */
    @RequestMapping(value = "/developers")
    public ModelAndView developersPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
        modelAndView.setViewName("/developers");
        return modelAndView;
    }
}

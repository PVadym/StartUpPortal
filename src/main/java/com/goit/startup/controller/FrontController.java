package com.goit.startup.controller;

import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class for initial processing of user's requests
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
@Controller
@ComponentScan(basePackages = "com.goit.startup.service")
public class FrontController {

    /**
     * An instance of implementation {@link StartupService} interface
     */
    private StartupService startupService;

    /**
     * An instance of implementation {@link UserService} interface
     */
    private UserService userService;

    /**
     * Constructor
     *
     * @param startupService an instance of implementation {@link StartupService} interface
     * @param userService    an instance of implementation {@link UserService} interface
     */
    @Autowired
    public FrontController(StartupService startupService, UserService userService) {
        this.startupService = startupService;
        this.userService = userService;
    }

    /**
     * Method defines models and view for index page
     *
     * @return model and view of index page
     */
    @RequestMapping(
            value = {"", "/", "/index", "home"},
            method = RequestMethod.GET
    )
    public ModelAndView getIndexPage(@RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
        ModelAndView modelAndView = new ModelAndView();

        if (searchWord.equals("")) {
            modelAndView.addObject("startups", startupService.getAll());
        } else {
            modelAndView.addObject("startups", startupService.findAllByKeyWord(searchWord));
            modelAndView.addObject("searchWord", searchWord);
        }

        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
        modelAndView.setViewName("allStartups");
        return modelAndView;
    }

}

package com.goit.startup.controller;

import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            value = { "", "/", "/index", "home" },
            method = RequestMethod.GET
    )
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("startups", startupService.getAll());
        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
        modelAndView.setViewName("allStartups");
        return modelAndView;
    }

//
//    /**
//     * Method defines models and view for users page
//     *
//     * @return model and view of users page
//     */
//    @RequestMapping(
//            value = "/users",
//            method = RequestMethod.GET
//    )
//    public ModelAndView getUsersPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("users", userService.getAll());
//        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
//        modelAndView.setViewName("users");
//        return modelAndView;
//    }
//
//    /**
//     * Method defines models and view for product page
//     *
//     * @param id a unique identifier for product
//     * @return model and view for product info page
//     */
//    @RequestMapping(
//            value = "/product/{id}",
//            method = RequestMethod.GET
//    )
//    public ModelAndView getProductPage(@PathVariable(name = "id") long id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("product", productService.get(id));
//        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
//        modelAndView.setViewName("product");
//        return modelAndView;
//    }
}

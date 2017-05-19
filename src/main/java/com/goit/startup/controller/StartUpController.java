package com.goit.startup.controller;

import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import com.goit.startup.validator.StartupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class is responsible of processing {@link com.goit.startup.entity.Startup} entity related requests
 *
 * @author Perevoznyk Pavlo
 *         created on 14 may 2017
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/startups")
public class StartUpController {

    private StartupService startupService;

    private UserService userService;
    private StartupValidator startupValidator;

    @Autowired
    public StartUpController(StartupService startupService, UserService userService, StartupValidator startupValidator) {
        this.startupService = startupService;
        this.userService = userService;
        this.startupValidator = startupValidator;
    }

    @RequestMapping(value = "/add/{userId}", method = RequestMethod.GET)
    public ModelAndView addStartupPage(@PathVariable(name = "userId") long userId) {
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = new Startup();
        startup.setAuthor(userService.get(userId));
        modelAndView.addObject("startup", startup);
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("addStartUp");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStartup(Startup startup, BindingResult bindingResult) {
        startupValidator.validate(startup, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addStartUp";
        }
        long userId = startup.getAuthor().getId();
        User user = userService.get(userId);
        startup.setAuthor(user);
        startup.setImageId(1L);
        user.getStartups().add(startup);
        userService.update(user);
//        startupService.add(startup);
        return "redirect:/";
    }

    @RequestMapping(value = "/{startupId}", method = RequestMethod.GET)
    public ModelAndView startUpDetails(@PathVariable(name = "startupId") long startupId) {
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = startupService.get(startupId);
        modelAndView.addObject("startup", startup);
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("startUpDetails");
        return modelAndView;
    }

    @RequestMapping(value = "edit/{startupId}", method = RequestMethod.GET)
    public ModelAndView editStartupPage(@PathVariable(name = "startupId") long startupId) throws IllegalAccessException {
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = startupService.get(startupId);
        if (startup.getAuthor().equals(userService.getAuthenticatedUser()) || userService.isAuthenticatedAdmin()) {
            modelAndView.addObject("startup", startup);
            modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
            modelAndView.setViewName("editStartUp");
        } else {
            throw new IllegalAccessException("Only startup's author can edit the startup");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editStartup(Startup editedStartup, BindingResult bindingResult) {
        startupValidator.validate(editedStartup, bindingResult);
        if (bindingResult.hasErrors()) {
            return "editStartUp";
        }
        Startup oldStartup = startupService.get(editedStartup.getId());
        long userId = editedStartup.getAuthor().getId();
        User author = userService.get(userId);
        editedStartup.setAuthor(author);
        editedStartup.setInvestments(oldStartup.getInvestments());
        startupService.update(editedStartup);
        return "redirect:/";
    }

    @RequestMapping(value = "delete/{startupId}", method = RequestMethod.GET)
    public String deleteStartup(@PathVariable(name = "startupId") long startupId) throws IllegalAccessException {
        Startup startup = startupService.get(startupId);
        if (startup.getAuthor().equals(userService.getAuthenticatedUser()) || userService.isAuthenticatedAdmin()) {
            User user = userService.get(startup.getAuthor().getId());
            user.getStartups().remove(startup);
            userService.update(user);
        } else {
            throw new IllegalAccessException("Only startup's author can edit the startup");
        }
        //        startupService.remove(startup);
        return "redirect:/";
    }
}
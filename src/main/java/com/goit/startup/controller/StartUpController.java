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

    /**
     * An instance of implementation {@link StartupService} interface.
     */
    private StartupService startupService;

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * An instance of {@link StartupValidator}.
     */
    private StartupValidator startupValidator;

    /**
     * Constructor.
     *
     * @param startupService   an instance of implementation {@link StartupService} interface.
     * @param userService      an instance of implementation {@link UserService} interface.
     * @param startupValidator an instance of {@link StartupValidator}.
     */
    @Autowired
    public StartUpController(StartupService startupService, UserService userService, StartupValidator startupValidator) {
        this.startupService = startupService;
        this.userService = userService;
        this.startupValidator = startupValidator;
    }

    /**
     * The method defines models and view for page to making a new startup.
     *
     * @param userId id of user, that creates the startup.
     * @return models and view for page to making startup.
     */
    @RequestMapping(value = "/add/{userId}", method = RequestMethod.GET)
    public ModelAndView addStartupPage(@PathVariable(name = "userId") long userId) {
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = new Startup();
        startup.setAuthor(userService.get(userId));
        modelAndView.addObject("startup", startup);
        modelAndView.setViewName("addStartUp");
        return modelAndView;
    }

    /**
     * The method adds a new startup into DB.
     *
     * @param startup       a new investment, startup of {@link Startup}.
     * @param bindingResult an instance of implementation {@link BindingResult}.
     * @return an address of page with all startups if startup was added successfully,
     * or or address for making startup otherwise.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStartup(Startup startup, BindingResult bindingResult) {
        startupValidator.validate(startup, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addStartUp";
        }
        long userId = startup.getAuthor().getId();
        User user = userService.get(userId);
        startup.setImageId(1L);
        user.getStartups().add(startup);
        userService.update(user);
        return "redirect:/";
    }

    /**
     * The method defines models and view for page with information about startup.
     *
     * @param startupId id of {@link Startup}
     * @return models and view for page with information about startup.
     */
    @RequestMapping(value = "/{startupId}", method = RequestMethod.GET)
    public ModelAndView startUpDetails(@PathVariable(name = "startupId") long startupId) {
        ModelAndView modelAndView = new ModelAndView();
        Startup startup = startupService.get(startupId);
        modelAndView.addObject("startup", startup);
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("startUpDetails");
        return modelAndView;
    }

    /**
     * The method defines models and view for page to updating a startup.
     *
     * @param startupId id of {@link Startup}.
     * @return models and view for page to updating a startup.
     * @throws IllegalAccessException if user who wants to edit the startup is not author of the startup.
     */
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

    /**
     * The method updates startup in DB.
     *
     * @param editedStartup startup with new information.
     * @param bindingResult an instance of implementation {@link BindingResult}.
     * @return an address of page with all startups if startup was updated successfully,
     * or or address for updating startup otherwise.
     */
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

    /**
     * Method for removing startup.
     *
     * @param startupId an id of startup to removing.
     * @return address of page with all startups if startup was deleted successfully.
     * @throws IllegalAccessException if user who wants to delete the startup is not author of the startup.
     */
    @RequestMapping(value = "delete/{startupId}", method = RequestMethod.GET)
    public String deleteStartup(@PathVariable(name = "startupId") long startupId) throws IllegalAccessException {
        Startup startup = startupService.get(startupId);
        if (startup.getAuthor().equals(userService.getAuthenticatedUser()) || userService.isAuthenticatedAdmin()) {
            User user = userService.get(startup.getAuthor().getId());
            user.getStartups().remove(startup);
            userService.update(user);
        } else {
            throw new IllegalAccessException("Only startup's author can delete the startup");
        }
        return "redirect:/";
    }
}
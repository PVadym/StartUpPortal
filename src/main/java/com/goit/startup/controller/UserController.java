package com.goit.startup.controller;


import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.service.SecurityService;
import com.goit.startup.service.UserService;
import com.goit.startup.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * The class provides a set of methods for operations with User entity.
 *
 * @author Slava Makhinich, Pavel Perevoznyk.
 * @version 1.0
 */

@Controller
@ComponentScan(basePackages = "com.goit.startup.service")
@RequestMapping(value = "/user")
public class UserController {

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private final UserService userService;

    /**
     * An instance of implementation {@link SecurityService} interface.
     */
    private SecurityService securityService;

    /**
     * An instance of {@link UserValidator}.
     */
    private UserValidator userValidator;

    private PasswordEncoder passwordEncoder;

    /**
     * Constructor.
     *
     * @param userService     an instance of implementation {@link UserService} interface.
     * @param securityService an instance of implementation {@link SecurityService} interface.
     * @param userValidator   an instance of {@link UserValidator}.
     */
    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method defines models and view for page with information about all users.
     *
     * @return model and view for page with information about all users.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsersPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.addObject("users", this.userService.getAll());
        modelAndView.setViewName("allUsers");
        return modelAndView;
    }

    /**
     * Method defines models and view for page with information about user.
     *
     * @param userName  a name of user.
     * @param isStartUp boolean parameter on which depends what information will be shown,
     *                  the user's startups, or the user's investments.
     * @return model and view for page with information about user.
     */
    @RequestMapping(value = "/{userName}/{isStartUp}")
    public ModelAndView getUserPage(@PathVariable(name = "userName") String userName,
                                    @PathVariable(name = "isStartUp") boolean isStartUp) {
        ModelAndView modelAndView = new ModelAndView();
        User requestFromUser = userService.getByUsername(userName);
        User authenticatedUser = this.userService.getAuthenticatedUser();
        if (requestFromUser.equals(authenticatedUser)) {

            modelAndView.addObject("user", requestFromUser);
            modelAndView.addObject("isStartUps", isStartUp);
            if (isStartUp) {
                modelAndView.addObject("startups", requestFromUser.getStartups());
            } else {
                modelAndView.addObject("investments", requestFromUser.getInvestments());
            }
            modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
            modelAndView.setViewName("userPage");
        } else {
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    /**
     * Method defines models and view for page to add a new user.
     *
     * @return model and view for page to add a new user.
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * Method to add a new user.
     *
     * @param user          is a {@link User} entity from request.
     * @param bindingResult is a {@link BindingResult} with information about user.
     * @return an address of users page.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        user.setImageId(1L);
        String oldPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPassword));
        userService.add(user);
        securityService.autoLogin(user.getUsername(), oldPassword);
        return "redirect:/user/" + user.getUsername() + "/true";
    }

    /**
     * Method defines models and view for page to update user's data.
     *
     * @param userId id of user.
     * @return models and view for page to update user's data.
     */
    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public ModelAndView editUserInfoPage(@PathVariable(name = "userId") long userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.get(userId));
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    /**
     * Method to edit user.
     *
     * @param user          a {@link User} entity with new information.
     * @param bindingResult is a {@link BindingResult}.
     * @param isLocked      boolean value with information about locking user.
     * @return address of user's page if user was updating successfully, or address of page for updating user otherwise.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUserInfo(User user, BindingResult bindingResult,
                               @RequestParam(value = "locked", defaultValue = "false") boolean isLocked) {
        User oldUser = userService.get(user.getId());
        oldUser.setUsername(user.getUsername());
        oldUser.setContacts(user.getContacts());
        oldUser.setPassword(user.getPassword());
        oldUser.setConfirmPassword(user.getConfirmPassword());
        oldUser.setRole(user.getRole());
        oldUser.setLocked(isLocked);
        userValidator.validate(oldUser, bindingResult);
        if (bindingResult.hasErrors()) {
            return "editUser";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(oldUser);
        if (userService.getAuthenticatedUser().getId() == oldUser.getId()) {
            securityService.autoLogin(oldUser.getUsername(), oldUser.getPassword());
            return "redirect:/user/" + oldUser.getUsername() + "/true";
        }
        return "redirect:/user";
    }

    /**
     * Method for removing user.
     *
     * @param userId an id of user to removing.
     * @return an address of page with all users if user was deleted successfully.
     * @throws IllegalAccessException if user who wants to delete another user is not administrator.
     */
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(name = "userId") long userId) throws IllegalAccessException {
        if (userService.isAuthenticatedAdmin()) {
            userService.remove(userId);
        } else {
            throw new IllegalAccessException("Only administrator's can delete users");
        }
        return "redirect:/user";
    }

}

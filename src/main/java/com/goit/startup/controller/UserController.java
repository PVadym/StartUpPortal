package com.goit.startup.controller;


import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.service.SecurityService;
import com.goit.startup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * The class provides a set of methods for operations with User entity
 *
 * @author Slava Makhinich
 */

@Controller
@ComponentScan(basePackages = "com.goit.startup.service")
@RequestMapping(value = "/user")
public class UserController {

    /**
     * An instance of implementation {@link UserService} interface
     */
    private final UserService userService;


    /**
     * An instance of implementation {@link SecurityService} interface
     */
    private SecurityService securityService;

    /**
     * Constructor.
     *
     * @param userService An instance of implementation UserService interface.
     */
    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @RequestMapping(value = "/{userName}/{isStartUp}")
    public ModelAndView getUserPage(@PathVariable(name = "userName") String userName,
                                    @PathVariable(name = "isStartUp") boolean isStartUp) {
        ModelAndView modelAndView = new ModelAndView();
        User requestFromUser = userService.getByUsername(userName);
        User authenticatedUser = this.userService.getAuthenticatedUser();

        if (requestFromUser.equals(authenticatedUser)) {

            modelAndView.addObject("user", requestFromUser);
            modelAndView.addObject("isStartUps", isStartUp);
            if(isStartUp){
                modelAndView.addObject("startups", authenticatedUser.getStartups());
            }
            else {
                modelAndView.addObject("investments", authenticatedUser.getInvestments());
            }
            modelAndView.setViewName("userPage");
        } else {
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    /**
     * Method creates a page to add a new user
     *
     * @return a page to add a new user
     */
    @RequestMapping(
            value = "/register",
            method = RequestMethod.GET
    )
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * Method to add a new user
     *
     * @param username user's name
     * @param password user's password
     * @param role     user's role
     * @param isLocked information about locking user's account
     * @return an address of users page
     */
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    public String registerUser(
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password,
            @RequestParam(value = "role", defaultValue = "USER") UserRole role,
            @RequestParam(value = "locked", defaultValue = "false") boolean isLocked
    ) {
        User userToAdd = new User(username, password, role);
        userToAdd.setLocked(isLocked);
        userService.add(userToAdd);
        securityService.autoLogin(username, password);
        return "redirect:/";
    }


    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public ModelAndView editUserInfoPage(@PathVariable(name = "userId") long userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.get(userId));
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUserInfo(User user){
        User oldUser = userService.get(user.getId());
        oldUser.setUsername(user.getUsername());
        oldUser.setContacts(user.getContacts());
        oldUser.setPassword(user.getPassword());
        userService.update(oldUser);
        securityService.autoLogin(oldUser.getUsername(), oldUser.getPassword());
        return "redirect:/user/" + oldUser.getUsername() + "/true";
    }



}

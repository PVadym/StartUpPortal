package com.goit.startup.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class provides a set of methods for authorization of users in the system
 *
 * @author Вадим
 */
@Controller
public class AuthorizationController {

    /**
     * Method returns page for authorization
     *
     * @return an address  of page for authorization
     */
    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public String loginPage() {
        return "login";
    }

    /**
     * Method serves for logout of users from the system
     *
     * @param request  a Http request
     * @param response a Http response
     * @return an address of logout page
     */
    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(
                request, response,
                SecurityContextHolder.getContext().getAuthentication()
        );
        return "redirect:/login?logout";
    }
}

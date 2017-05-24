package com.goit.startup.controller;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing of {@link AuthorizationController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class AuthorizationControllerTest {

    /**
     * Instance of {@link AuthorizationController}.
     */
    private AuthorizationController authorizationController;

    /**
     * Instance of implementation {@link HttpServletRequest}.
     */
    private HttpServletRequest request;

    /**
     * Instance of implementation {@link HttpServletRequest}.
     */
    private HttpServletResponse response;

    /**
     * Constructor
     */
    public AuthorizationControllerTest() {
        this.authorizationController = new AuthorizationController();
        this.request = Mockito.mock(HttpServletRequest.class);
        this.response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void loginPageTest() {
        assertEquals(authorizationController.loginPage(), "login");
    }

    @Test
    public void logoutPageTest() {
        assertEquals(authorizationController.logoutPage(request, response), "redirect:/login?logout");
    }
}
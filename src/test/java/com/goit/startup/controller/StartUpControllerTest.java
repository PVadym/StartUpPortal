package com.goit.startup.controller;

import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import com.goit.startup.validator.StartupValidator;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class for testing {@link StartUpController}.
 *
 * @author Slava Makhinich
 * Created on 25.05.2017.
 * @version 1.0
 */
public class StartUpControllerTest {

    /**
     * Instance of {@link StartupValidator}.
     */
    private StartupValidator startupValidator;

    /**
     * Instance of implementation {@link StartupService}.
     */
    private StartupService startupService;

    /**
     * Instance of implementation {@link UserService}.
     */
    private UserService userService;

    /**
     * Instance of {@link User}.
     */
    private User user;

    /**
     * Instance of {@link StartUpController}.
     */
    private StartUpController controller;

    /**
     * Instance of {@link Startup}.
     */
    private Startup startup;

    /**
     * Constructor
     */
    public StartUpControllerTest() {
        this.user = new User("Test User", "Test PassWord", UserRole.ADMIN);
        this.user.setId(0);
        this.startup = new Startup();
        startup.setId(0);
        startup.setName("test startUp");
        this.userService = mock(UserService.class);
        when(userService.get(user.getId())).thenReturn(user);
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        this.startupService = mock(StartupService.class);
        when(startupService.get(startup.getId())).thenReturn(startup);
        this.startupValidator = mock(StartupValidator.class);
        controller = new StartUpController(startupService, userService, startupValidator);
    }

    @Test
    public void addStartupPage() throws Exception {
        ModelAndView modelAndView = controller.addStartupPage(user.getId());
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(modelAndView.getViewName(), "addStartUp");
        assertEquals(((Startup) models.get("startup")).getAuthor(), user);
    }

    @Test
    public void addStartup() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true).thenReturn(false);
        startup.setAuthor(user);
        assertEquals(controller.addStartup(startup, bindingResult), "addStartUp");
        verify(startupValidator).validate(startup, bindingResult);
        assertEquals(controller.addStartup(startup, bindingResult), "redirect:/");
        assertEquals(startup.getImageId(), 1l);
        assertTrue(user.getStartups().contains(startup));
    }

    @Test
    public void startUpDetails() throws Exception {
        ModelAndView modelAndView = controller.startUpDetails(startup.getId());
        assertEquals(modelAndView.getViewName(), "startUpDetails");
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("startup"), startup);
        assertTrue((boolean)models.get("is_admin"));
    }

    @Test
    public void editStartupPageAuthor() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(false);
        startup.setAuthor(this.user);
        ModelAndView modelAndView = controller.editStartupPage(startup.getId());
        Map<String, Object> models = modelAndView.getModel();
        assertFalse((boolean)models.get("is_admin"));
        assertEquals(startup, models.get("startup"));
        assertEquals(modelAndView.getViewName(), "editStartUp");
    }

    @Test
    public void editStartupPageAdmin() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        when(userService.getAuthenticatedUser()).thenReturn(new User());
        startup.setAuthor(this.user);
        ModelAndView modelAndView = controller.editStartupPage(startup.getId());
        Map<String, Object> models = modelAndView.getModel();
        assertTrue((boolean)models.get("is_admin"));
        assertEquals(startup, models.get("startup"));
        assertEquals(modelAndView.getViewName(), "editStartUp");
    }

    @Test(expected = IllegalAccessException.class)
    public void editStartupPageException() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(false);
        when(userService.getAuthenticatedUser()).thenReturn(new User());
        startup.setAuthor(this.user);
        ModelAndView modelAndView = controller.editStartupPage(startup.getId());
    }

        @Test
    public void editStartup() throws Exception {
        startup.setAuthor(user);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true).thenReturn(false);
        assertEquals(controller.editStartup(startup, bindingResult), "editStartUp");
        verify(startupValidator).validate(startup, bindingResult);
        assertEquals(controller.editStartup(startup, bindingResult), "redirect:/");
        assertEquals(startup.getAuthor(), user);
        verify(startupService).update(startup);
        }

    @Test
    public void deleteStartupAuthor() throws Exception {
        deleteCheck(false, this.user);
    }

    @Test
    public void deleteStartupAdmin() throws Exception {
        deleteCheck(true, new User());
    }

    @Test(expected = IllegalAccessException.class)
    public void deleteStartupException() throws Exception {
        deleteCheck(false, new User());
    }

    private void deleteCheck(boolean isAdmin, User loggedUser) throws IllegalAccessException {
        when(userService.isAuthenticatedAdmin()).thenReturn(isAdmin);
        when(userService.getAuthenticatedUser()).thenReturn(loggedUser);
        startup.setAuthor(this.user);
        this.user.getStartups().add(startup);
        assertEquals("redirect:/", controller.deleteStartup(startup.getId()));
        assertFalse(user.getStartups().contains(startup));
        verify(userService).update(this.user);
    }
}
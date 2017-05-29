package com.goit.startup.controller;

import com.goit.startup.service.ImageServiceImplTest;
import com.goit.startup.service.UserService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link DevelopersController}.
 *
 * @author Slava Makhinich
 * Created on 24.05.2017.
 * @version 1.0
 */
public class DevelopersControllerTest {
    @Test
    public void developersPage() throws Exception {
        UserService userService = mock(UserService.class);
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        DevelopersController controller = new DevelopersController(userService);
        ModelAndView modelAndView = controller.developersPage();
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(modelAndView.getViewName(), "/developers");
        assertTrue((boolean)model.get("is_admin"));
    }

}
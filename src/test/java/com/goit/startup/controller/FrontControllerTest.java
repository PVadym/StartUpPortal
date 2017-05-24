package com.goit.startup.controller;

import com.goit.startup.entity.Startup;
import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link FrontController}.
 *
 * @author Slava Makhinich
 * Created on 24.05.2017.
 * @version 1.0
 */
public class FrontControllerTest {

    /**
     * Instance of implementation {@link StartupService}.
     */
    private StartupService startupService;

    /**
     * Instance of implementation {@link UserService}.
     */
    private UserService userService;

    /**
     * A list of {@link Startup} for mock. It will be used when we will tray to find all startups.
     */
    private List<Startup> startupsAll;

    /**
     * A list of {@link Startup} for mock. It will be used when we will tray to find startups by key word.
     */
    private List<Startup> startupsSerch;

    /**
     * The key word on which we will look for startUps.
     */
    private String searchWord;

    /**
     * Constructor
     */
    public FrontControllerTest() {
        this.searchWord = "some word";
        this.startupService = mock(StartupService.class);
        this.userService = mock(UserService.class);
        this.startupsAll = new ArrayList<>();
        this.startupsAll.add(getStartup(1));
        this.startupsAll.add(getStartup(2));
        this.startupsSerch = new ArrayList<>();
        this.startupsSerch.add(getStartup(1));
        when(startupService.getAll()).thenReturn(this.startupsAll);
        when(startupService.findAllByKeyWord(searchWord)).thenReturn(startupsSerch);
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
    }

    @Test
    public void getIndexPageAll() throws Exception {
        FrontController controller = new FrontController(startupService, userService);
        ModelAndView modelAndView = controller.getIndexPage("");
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("startups"), startupsAll);
        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "allStartups");
    }

    @Test
    public void getIndexPageSearch() throws Exception {
        FrontController controller = new FrontController(startupService, userService);
        ModelAndView modelAndView = controller.getIndexPage(searchWord);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("startups"), startupsSerch);
        assertEquals(models.get("searchWord"), searchWord);
        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "allStartups");
    }

    /**
     * Method creates a new instance of {@link Startup}.
     *
     * @param i integer value.
     * @return a new instance of {@link Startup}.
     */
    private Startup getStartup(Integer i) {
        Startup startup = new Startup();
        startup.setId(i);
        startup.setMinInvestment(i);
        startup.setNeedInvestment(i);
        startup.setName(i.toString());
        startup.setDescription(i.toString());
        return startup;
    }

}
package com.goit.startup.controller;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.service.InvestmentService;
import com.goit.startup.service.StartupService;
import com.goit.startup.service.UserService;
import com.goit.startup.validator.InvestmentValidator;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Вадим on 24.05.2017.
 */
public class InvestmentControllerTest {

    /**
     * An instance of implementation {@link StartupService} interface.
     */
    private StartupService startupService;

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * An instance of implementation {@link InvestmentService} interface.
     */
    private InvestmentService investmentService;

    /**
     * An instance of {@link InvestmentValidator}.
     */
    private InvestmentValidator investmentValidator;

    /**
     * An instance of {@link InvestmentController}.
     */
    private InvestmentController investmentController;

    /**
     * An instance of {@link User}.
     */
    private User user;

    /**
     * An instance of {@link Investment}.
     */
    private Investment investment;

    /**
     * An instance of {@link Startup}.
     */
    private Startup startup;

    private Set<Investment> investmentSet;

    public InvestmentControllerTest() {
        this.user = new User("user1", "userpass", UserRole.USER);
        user.setId(1L);
        this.investment = new Investment();
        investment.setId(1L);
        investment.setAmount(100);
        this.investmentSet = new HashSet<>();
        this.startup = new Startup();
        startup.setName("startup1");
        startup.setId(1L);
        startup.setAuthor(user);
        startup.setInvestments(investmentSet);
        this.startupService = mock(StartupService.class);
        this.userService = mock(UserService.class);
        this.investmentService = mock(InvestmentService.class);
        this.investmentValidator = mock(InvestmentValidator.class);
        investmentController = new InvestmentController(startupService,userService,investmentService,investmentValidator);
    }

    @Test
    public void investPageString() throws Exception {

        investmentSet.add(investment);

        when(startupService.get(1L)).thenReturn(startup);
        when(investmentService.get(1L)).thenReturn(investment);
        when(userService.get(1L)).thenReturn(user);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(userService.getByUsername(user.getUsername())).thenReturn(user);

        ModelAndView modelAndView = investmentController.investPage(1L);
        Map<String, Object> models = modelAndView.getModel();
        Investment investment = (Investment) models.get("investment");
        verify(userService).getAuthenticatedUser();
        verify(startupService).get(1L);
        assertEquals(investment.getStartup(),startup);
        assertEquals(investment.getInvestor(),user);
        assertTrue(models.get("investment").getClass()== Investment.class);
        assertEquals(modelAndView.getViewName(), "makeInvestment");
    }

    @Test
    public void investPageModelAndView() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        when(startupService.get(1L)).thenReturn(startup);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(bindingResult.hasErrors()).thenReturn(true).thenReturn(false);
        investment.setInvestor(user);
        investment.setStartup(startup);
        assertEquals(investmentController.investPage(investment,bindingResult),"makeInvestment");
        assertEquals(investmentController.investPage(investment, bindingResult), "redirect:/startups/" + startup.getId());
        verify(investmentValidator,times(2)).validate(investment,bindingResult);
        verify(investmentService).update(investment);
    }

    @Test
    public void delete() throws Exception {
        when(userService.getAuthenticatedUser()).thenReturn(user,user,user,user).thenReturn(new User());
        when(userService.isAuthenticatedAdmin()).thenReturn(true, false).thenReturn(false, true);
        investment.setStartup(startup);
        investment.setInvestor(user);
        when(investmentService.get(1L)).thenReturn(investment);
        startup.setAuthor(user);
        startup.setInvestments(investmentSet);

        assertEquals(investmentController.delete(1L),"redirect:/user/" + user.getUsername() + "/false");
        for (int i = 0; i < 3; i++){
            investmentController.delete(1L);
        }
        verify(startupService,times(3)).update(startup);

    }

}
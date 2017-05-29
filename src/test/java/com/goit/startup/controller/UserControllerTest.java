package com.goit.startup.controller;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.service.SecurityService;
import com.goit.startup.service.UserService;
import com.goit.startup.validator.PasswordChangeValidator;
import com.goit.startup.validator.UserEditValidator;
import com.goit.startup.validator.UserRegisterValidator;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class for testing {@link UserController}.
 *
 * @author Slava Makhinich
 * Created on 26.05.2017.
 * @version 1.0
 */
public class UserControllerTest {

    /**
     * An instance of {@link UserController} class.
     */
    private UserController controller;

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * A list of {@link User}.
     */
    private List<User> userList;

    /**
     * An instance of {@link User} class.
     */
    private User user;

    /**
     * An instance of implementation {@link SecurityService} interface.
     */
    private SecurityService securityService;

    /**
     * An instance of {@link UserEditValidator} class.
     */
    private UserEditValidator userEditValidator;

    /**
     * An instance of {@link UserRegisterValidator} class.
     */
    private UserRegisterValidator userRegisterValidator;

    /**
     * An instance of implementation {@link PasswordEncoder} interface.
     */
    private PasswordEncoder passwordEncoder;

    /**
     * An instance of {@link PasswordChangeValidator} class.
     */
    private PasswordChangeValidator passwordChangeValidator;

    /**
     * An instance of implementation {@link BindingResult} interface.
     */
    private BindingResult bindingResult;

    /**
     * An instance of implementation {@link HttpServletRequest} interface.
     */
    HttpServletRequest request;

    /**
     * Constructor
     */
    public UserControllerTest() {
        this.user = createUser(0);
        this.userList = createUserList();
        this.userService = mock(UserService.class);
        this.securityService = mock(SecurityService.class);
        this.userRegisterValidator = mock(UserRegisterValidator.class);
        this.userEditValidator = mock(UserEditValidator.class);
        this.passwordChangeValidator = mock(PasswordChangeValidator.class);
        this.passwordEncoder = mock(PasswordEncoder.class);
        this.controller = new UserController(userService, securityService, userRegisterValidator,
                userEditValidator, passwordChangeValidator, passwordEncoder);
        this.bindingResult = mock(BindingResult.class);
        this.request = mock(HttpServletRequest.class);
    }

    @Test
    public void getAllUsersPage() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        when(userService.getAll()).thenReturn(userList);
        ModelAndView modelAndView = controller.getAllUsersPage();
        Map<String, Object> models = modelAndView.getModel();
        assertTrue((boolean)models.get("is_admin"));
        assertEquals(models.get("users"), userList);
        assertEquals("allUsers", modelAndView.getViewName());
    }

    @Test
    public void getUserPageWithStartups() throws Exception {
        user.setStartups(createStartupList());
        when(userService.getByUsername(user.getUsername())).thenReturn(user);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        ModelAndView modelAndView = controller.getUserPage(user.getUsername(), true);
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(model.get("user"), user);
        assertTrue((boolean)model.get("isStartUps"));
        assertEquals(user.getStartups(), model.get("startups"));
        assertTrue((boolean)model.get("is_admin"));
        assertEquals("userPage", modelAndView.getViewName());
    }

    @Test
    public void getUserPageWithInvestments() throws Exception {
        user.setInvestments(createInvestmentList());
        when(userService.getByUsername(user.getUsername())).thenReturn(user);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(userService.isAuthenticatedAdmin()).thenReturn(false);
        ModelAndView modelAndView = controller.getUserPage(user.getUsername(), false);
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(model.get("user"), user);
        assertFalse((boolean)model.get("isStartUps"));
        assertEquals(user.getInvestments(), model.get("investments"));
        assertFalse((boolean)model.get("is_admin"));
        assertEquals("userPage", modelAndView.getViewName());
    }

    @Test
    public void getUserPageNotAuthenticated() throws Exception {
        when(userService.getByUsername(user.getUsername())).thenReturn(user);
        when(userService.getAuthenticatedUser()).thenReturn(createUser(555));
        ModelAndView modelAndView = controller.getUserPage(user.getUsername(), false);
        assertEquals("redirect:/login", modelAndView.getViewName());
    }

    @Test
    public void getRegistrationPage() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        ModelAndView modelAndView = controller.getRegistrationPage();
        assertEquals(modelAndView.getViewName(), "registration");
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(model.get("user"), new User());
        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) model.get("roles")));
        assertTrue((boolean)model.get("is_admin"));
    }

    @Test
    public void registerUserInvalid() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);
        assertEquals(controller.registerUser(user, bindingResult), "registration");
        verify(userRegisterValidator).validate(user, bindingResult);
    }

    @Test
    public void registerUserValid() throws Exception {
        String oldPassword = user.getPassword();
        when(passwordEncoder.encode(user.getPassword())).thenReturn("coded");
        when(bindingResult.hasErrors()).thenReturn(false);
        assertEquals(
                controller.registerUser(user, bindingResult), "redirect:/user/" + user.getUsername() + "/true"
        );
        verify(userRegisterValidator).validate(user, bindingResult);
        assertTrue(user.getImageId() == 1);
        assertEquals(user.getPassword(), "coded");
        verify(userService).add(user);
        verify(securityService).autoLogin(user.getUsername(), oldPassword);
    }

    @Test
    public void editUserInfoPageForUser() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(false);
        when(userService.get(user.getId())).thenReturn(user);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        ModelAndView modelAndView = controller.editUserInfoPage(user.getId());
        assertEquals(modelAndView.getViewName(), "editUser");
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(model.get("user"), user);
        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) model.get("roles")));
        assertFalse((boolean)model.get("is_admin"));
    }

    @Test
    public void editUserInfoPageForAdmin() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        User userToEdit = createUser(55);
        when(userService.get(userToEdit.getId())).thenReturn(userToEdit);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        ModelAndView modelAndView = controller.editUserInfoPage(userToEdit.getId());
        assertEquals(modelAndView.getViewName(), "editUser");
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(model.get("user"), userToEdit);
        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) model.get("roles")));
        assertTrue((boolean)model.get("is_admin"));
    }

    @Test(expected = IllegalAccessException.class)
    public void editUserInfoPageInvalid() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(false);
        User userToEdit = createUser(55);
        when(userService.get(userToEdit.getId())).thenReturn(userToEdit);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        controller.editUserInfoPage(userToEdit.getId());
    }

    @Test
    public void editUserInfoError() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(userService.get(user.getId())).thenReturn(user);
        String pageName = controller.editUserInfo(user, bindingResult, false);
        assertEquals(pageName, "editUser");
    }

    @Test
    public void editUserInfoForUser() throws Exception {
        editUserInfo(false);
    }

    @Test
    public void editUserInfoForAdmin() throws Exception {
        editUserInfo(true);
    }

    @Test
    public void changePasswordWrongOldPassword() throws Exception {
        user.setPassword("$2a$11$Xqqi3yQ3N35uFYdCmr5zSOLcqG7kwOBAFZ6EDKuUyhBwSJvgOrdae");
        when(userService.getAuthenticatedUser()).thenReturn(user);
        String returnedPage =
                controller.changePassword("2222", "aaaa", "aaaa", request);
        verify(request).setAttribute("errorMessageOldPassword", "Wrong old password");
        assertEquals(returnedPage, "changePassword");
    }

    @Test
    public void changePasswordWrongConfirm() throws Exception {
        user.setPassword("$2a$11$Xqqi3yQ3N35uFYdCmr5zSOLcqG7kwOBAFZ6EDKuUyhBwSJvgOrdae");
        when(passwordChangeValidator.validate("aaaa", "bbbb")).thenReturn("error");
        when(userService.getAuthenticatedUser()).thenReturn(user);
        String returnedPage =
                controller.changePassword("1111", "aaaa", "bbbb", request);
        verify(request).setAttribute("errorMessagePassword", "error");
        assertEquals(returnedPage, "changePassword");
    }

    @Test
    public void changePasswordSuccessfully() throws Exception {
        user.setPassword("$2a$11$Xqqi3yQ3N35uFYdCmr5zSOLcqG7kwOBAFZ6EDKuUyhBwSJvgOrdae");
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(passwordChangeValidator.validate("2222", "2222")).thenReturn("");
        when(passwordEncoder.encode("2222")).thenReturn("222222222222222222222222222222");
        String returnedPage =
                controller.changePassword("1111", "2222", "2222", request);
        assertEquals(user.getPassword(), "222222222222222222222222222222");
        verify(userService).update(user);
        assertEquals(returnedPage, "redirect:/user/"+ user.getUsername() + "/true");
    }

    @Test(expected = IllegalAccessException.class)
    public void deleteUser() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        String returnedPage = controller.deleteUser(33333);
        verify(userService).remove(33333);
        assertEquals(returnedPage, "redirect:/user");
        when(userService.isAuthenticatedAdmin()).thenReturn(false);
        controller.deleteUser(33333);
    }

    @Test
    public void changePasswordPage() throws Exception {
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        ModelAndView modelAndView = controller.changePasswordPage();
        assertEquals(modelAndView.getViewName(), "changePassword");
        Map<String, Object> model = modelAndView.getModel();
        assertEquals(model.get("user"), user);
        assertTrue((boolean)model.get("is_admin"));
    }

    private User createUser(Integer i) {
        User user = new User(i.toString(), i.toString(), UserRole.USER);
        user.setId(i);
        user.setPassword(i.toString());
        user.setContacts(i.toString());
        if (i%2 == 0) {
            user.setRole(UserRole.ADMIN);
        }
        return user;
    }

    private List<User> createUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            userList.add(createUser(i));
        }
        return userList;
    }

    private Set<Startup> createStartupList() {
        Set<Startup> startupSet = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            Startup startup = new Startup();
            startup.setName(String.valueOf(i));
            startup.setDescription(String.valueOf(i));
            startup.setId(i);
            startup.setNeedInvestment(i + i);
            startup.setMinInvestment(i);
            startupSet.add(startup);
        }
        return startupSet;
    }

    private Set<Investment> createInvestmentList() {
        Set<Investment> investmentSet = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            Investment investment = new Investment();
            investment.setId(i);
            investment.setAmount(i);
        }
        return investmentSet;
    }

    private void editUserInfo(boolean isAdmin) {
        when(bindingResult.hasErrors()).thenReturn(false);
        if (isAdmin) {
            when(userService.getAuthenticatedUser()).thenReturn(createUser(88888));
        } else {
            when(userService.getAuthenticatedUser()).thenReturn(user);
        }
        when(userService.get(user.getId())).thenReturn(user);
        User newUser = createUser(11111);
        newUser.setId(user.getId());
        newUser.setPassword(user.getPassword());
        String pageName = controller.editUserInfo(newUser, bindingResult, false);
        assertEquals(user, newUser);
        if (isAdmin) {
            assertEquals(pageName, "redirect:/user");
        }
        else {
            assertEquals(pageName, "redirect:/user/" + newUser.getUsername() + "/true");
        }
    }

}
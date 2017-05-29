//package com.goit.startup.controller;
//
//import com.goit.startup.entity.Investment;
//import com.goit.startup.entity.Startup;
//import com.goit.startup.entity.User;
//import com.goit.startup.enums.UserRole;
//import com.goit.startup.service.SecurityService;
//import com.goit.startup.service.UserService;
//import com.goit.startup.validator.UserValidator;
//import org.junit.Test;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.*;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * Class for testing {@link UserController}.
// *
// * @author Slava Makhinich
// * Created on 26.05.2017.
// * @version 1.0
// */
//public class UserControllerTest {
//
//    private UserController controller;
//
//    private UserService userService;
//
//    private List<User> userList;
//
//    private User user;
//
//    private SecurityService securityService;
//
//    private UserValidator userValidator;
//
//    private PasswordEncoder passwordEncoder;
//
//    private BindingResult bindingResult;
//
//    public UserControllerTest() {
//        this.user = createUser(0);
//        this.userList = createUserList();
//        this.userService = mock(UserService.class);
//        this.securityService = mock(SecurityService.class);
//        this.userValidator = mock(UserValidator.class);
//        this.passwordEncoder = mock(PasswordEncoder.class);
//        this.controller = new UserController(userService, securityService, userValidator, passwordEncoder);
//        this.bindingResult = mock(BindingResult.class);
//    }
//
//    @Test
//    public void getAllUsersPage() throws Exception {
//        when(userService.isAuthenticatedAdmin()).thenReturn(true);
//        when(userService.getAll()).thenReturn(userList);
//        ModelAndView modelAndView = controller.getAllUsersPage();
//        Map<String, Object> models = modelAndView.getModel();
//        assertTrue((boolean)models.get("is_admin"));
//        assertEquals(models.get("users"), userList);
//        assertEquals("allUsers", modelAndView.getViewName());
//    }
//
//    @Test
//    public void getUserPage1() throws Exception {
//        user.setStartups(createStartupList());
//        when(userService.getByUsername(user.getUsername())).thenReturn(user);
//        when(userService.getAuthenticatedUser()).thenReturn(user);
//        when(userService.isAuthenticatedAdmin()).thenReturn(true);
//        ModelAndView modelAndView = controller.getUserPage(user.getUsername(), true);
//        Map<String, Object> model = modelAndView.getModel();
//        assertEquals(model.get("user"), user);
//        assertTrue((boolean)model.get("isStartUps"));
//        assertEquals(user.getStartups(), model.get("startups"));
//        assertTrue((boolean)model.get("is_admin"));
//        assertEquals("userPage", modelAndView.getViewName());
//    }
//
//    @Test
//    public void getUserPage2() throws Exception {
//        user.setInvestments(createInvestmentList());
//        when(userService.getByUsername(user.getUsername())).thenReturn(user);
//        when(userService.getAuthenticatedUser()).thenReturn(user);
//        when(userService.isAuthenticatedAdmin()).thenReturn(false);
//        ModelAndView modelAndView = controller.getUserPage(user.getUsername(), false);
//        Map<String, Object> model = modelAndView.getModel();
//        assertEquals(model.get("user"), user);
//        assertFalse((boolean)model.get("isStartUps"));
//        assertEquals(user.getInvestments(), model.get("investments"));
//        assertFalse((boolean)model.get("is_admin"));
//        assertEquals("userPage", modelAndView.getViewName());
//    }
//
//    @Test
//    public void getUserPage3() throws Exception {
//        when(userService.getByUsername(user.getUsername())).thenReturn(user);
//        when(userService.getAuthenticatedUser()).thenReturn(createUser(555));
//        ModelAndView modelAndView = controller.getUserPage(user.getUsername(), false);
//        assertEquals("redirect:/login", modelAndView.getViewName());
//    }
//
//    @Test
//    public void getRegistrationPage() throws Exception {
//        when(userService.isAuthenticatedAdmin()).thenReturn(true);
//        ModelAndView modelAndView = controller.getRegistrationPage();
//        assertEquals(modelAndView.getViewName(), "registration");
//        Map<String, Object> model = modelAndView.getModel();
//        assertEquals(model.get("user"), new User());
//        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) model.get("roles")));
//        assertTrue((boolean)model.get("is_admin"));
//    }
//
//    @Test
//    public void registerUserInvalid() throws Exception {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        assertEquals(controller.registerUser(user, bindingResult), "registration");
//        verify(userValidator).validate(user, bindingResult);
//    }
//
//    @Test
//    public void registerUserValid() throws Exception {
//        String oldPassword = user.getPassword();
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("coded");
//        when(bindingResult.hasErrors()).thenReturn(false);
//        assertEquals(
//                controller.registerUser(user, bindingResult), "redirect:/user/" + user.getUsername() + "/true"
//        );
//        verify(userValidator).validate(user, bindingResult);
//        assertTrue(user.getImageId() == 1);
//        assertEquals(user.getPassword(), "coded");
//        verify(userService).add(user);
//        verify(securityService).autoLogin(user.getUsername(), oldPassword);
//    }
//
//    @Test
//    public void editUserInfoPage() throws Exception {
//        when(userService.isAuthenticatedAdmin()).thenReturn(true);
//        when(userService.get(user.getId())).thenReturn(user);
//        ModelAndView modelAndView = controller.editUserInfoPage(user.getId());
//        assertEquals(modelAndView.getViewName(), "editUser");
//        Map<String, Object> model = modelAndView.getModel();
//        assertEquals(model.get("user"), user);
//        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) model.get("roles")));
//        assertTrue((boolean)model.get("is_admin"));
//    }
//
//    @Test
//    public void editUserInfo() throws Exception {
//
//    }
//
//    @Test
//    public void deleteUser() throws Exception {
//
//    }
//
//    private User createUser(Integer i) {
//        User user = new User(i.toString(), i.toString(), UserRole.USER);
//        user.setId(i);
//        if (i%2 == 0) {
//            user.setRole(UserRole.ADMIN);
//        }
//        return user;
//    }
//
//    private List<User> createUserList() {
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            userList.add(createUser(i));
//        }
//        return userList;
//    }
//
//    private Set<Startup> createStartupList() {
//        Set<Startup> startupSet = new HashSet<>();
//        for (int i = 0; i < 7; i++) {
//            Startup startup = new Startup();
//            startup.setName(String.valueOf(i));
//            startup.setDescription(String.valueOf(i));
//            startup.setId(i);
//            startup.setNeedInvestment(i + i);
//            startup.setMinInvestment(i);
//            startupSet.add(startup);
//        }
//        return startupSet;
//    }
//
//    private Set<Investment> createInvestmentList() {
//        Set<Investment> investmentSet = new HashSet<>();
//        for (int i = 0; i < 6; i++) {
//            Investment investment = new Investment();
//            investment.setId(i);
//            investment.setAmount(i);
//        }
//        return investmentSet;
//    }
//
//}
package com.goit.startup.service;

import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.repository.DataRepository;
import com.goit.startup.repository.UserRepository;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class for testing {@link UserServiceImpl}.
 *
 * @author Vadym Pylypchenko, Slava Makhinich
 * Created on 25.05.2017.
 * @version 1.0
 */
public class UserServiceImplTest extends DataServiceImplTest<User> {

    private UserService userService;

    private User user;

    private UserRepository repository;

    public UserServiceImplTest() {
        this.user = getNewUser(0l);
        this.repository = mock(UserRepository.class);
        this.userService = new UserServiceImpl(repository);
    }

    @Test(expected = NullPointerException.class)
    public void getByUsername() throws Exception {
        when(repository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(userService.getByUsername(user.getUsername()), user);
        when(repository.findByUsername("null")).thenReturn(null);
        userService.getByUsername("null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByUsernameBlank() throws Exception {
        userService.getByUsername(" ");
    }


    @Test
    public void removeByUsername() throws Exception {
        userService.removeByUsername(" ");
        verify(repository, never()).deleteByUsername(any());
        userService.removeByUsername(user.getUsername());
        verify(repository).deleteByUsername(user.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByRole() throws Exception {
        when(repository.findAllByRole(UserRole.USER)).thenReturn(getObjects());
        assertEquals(getObjects(), userService.getByRole(UserRole.USER));
        userService.getByRole(null);
    }

    @Test
    public void getAdmins() throws Exception {
        when(repository.findAllByRole(UserRole.ADMIN)).thenReturn(getObjects());
        assertEquals(getObjects(), userService.getAdmins());
    }

    @Test
    public void getUsers() throws Exception {
        when(repository.findAllByRole(UserRole.USER)).thenReturn(getObjects());
        assertEquals(getObjects(), userService.getUsers());
    }

    @Test
    public void getAuthenticatedUser() throws Exception {
        securityContextSetter();
        assertEquals(user, userService.getAuthenticatedUser());
        assertEquals(
                new User("anonymousUser", "", UserRole.USER), userService.getAuthenticatedUser()
        );
    }

    @Test
    public void isAuthenticatedAdmin() throws Exception {
        securityContextSetter();
        assertFalse(userService.isAuthenticatedAdmin());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername() throws Exception {
        when(repository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(user, userService.loadUserByUsername(user.getUsername()));
        when(repository.findByUsername("null")).thenReturn(null);
        userService.loadUserByUsername("null");
    }

    @Override
    protected DataService<User> getService() {
        return userService;
    }

    @Override
    protected User getObject() {
        return user;
    }

    @Override
    protected List<User> getObjects() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            users.add(getNewUser((long)i));
        }
        return users;
    }

    @Override
    protected DataRepository<User> getRepository() {
        return repository;
    }

    private User getNewUser(Long i) {
        User userNew = new User(i.toString(), i.toString(), UserRole.USER);
        userNew.setId(i);
        return userNew;
    }

    private void securityContextSetter() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user).thenReturn(new String());
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}

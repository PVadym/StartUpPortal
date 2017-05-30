package com.goit.startup.service;

import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;

/**
 * Created by Вадим on 24.05.2017.
 */
public class SecurityServiceImplTest {

    /**
     * An instance of {@link SecurityServiceImpl}
     */
    private SecurityServiceImpl securityService;

    /**
     * An instance of {@link AuthenticationManager}
     */
    private AuthenticationManager authenticationManager;

    /**
     * An instance of {@link UserService}
     */
    private UserService userService;

    private User user;


    public SecurityServiceImplTest() {

        this.authenticationManager = mock(AuthenticationManager.class, CALLS_REAL_METHODS);
        this.userService = mock(UserService.class);
        this.securityService = new SecurityServiceImpl(authenticationManager,userService);
        this.user = new User("Test", "test", UserRole.USER);
    }

    @Test
    public void autoLogin() throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user,
                        user.getPassword(),
                        user.getAuthorities());
        when(userService.loadUserByUsername("Test")).thenReturn(user);

        securityService.autoLogin("Test", "test");
        verify(userService).loadUserByUsername("Test");
        verify(authenticationManager).authenticate(authenticationToken);
    }

    @Test
    public void changeAuthenticatedUseTest() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        securityService.changeAuthenticatedUser(user);

        verify(authentication).getPrincipal();
    }
}
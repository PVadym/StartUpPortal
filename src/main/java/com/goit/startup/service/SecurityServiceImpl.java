package com.goit.startup.service;

import com.goit.startup.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * The class is responsible for autologin
 *
 * @author Perevoznyk Pavlo
 *         created on 15 may 2017
 * @version 1.0
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    /**
     * An instance of {@link AuthenticationManager}
     */
    private AuthenticationManager authenticationManager;

    /**
     * An instance of {@link UserService}
     */
    private UserService userService;

    /**
     * Constructor
     *
     * @param authenticationManager an instance of class that implements {@link AuthenticationManager} interface
     * @param userService           an instance of {@link UserService}
     */
    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    /**
     * Method performs autologin
     *
     * @param username a user`s name
     * @param password a user`s password
     */
    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    /**
     * Method performs username change for loggedin user
     *
     * @param user a user
     */
    public void changeAuthenticatedUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticationPrincipal = (User) authentication.getPrincipal();
        authenticationPrincipal.setUsername(user.getUsername());
        authenticationPrincipal.setContacts(user.getContacts());
        authenticationPrincipal.setRole(user.getRole());
        authenticationPrincipal.setLocked(user.isLocked());
    }
}

package com.goit.startup.service;

import com.goit.startup.entity.User;

/**
 * The interface is responsible for autologin
 *
 * @author Perevoznyk Pavlo
 *         created on 15 may 2017
 * @version 1.0
 */

public interface SecurityService {

    /**
     * Method performs autologin
     *
     * @param username a user`s name
     * @param password a user`s password
     */
    void autoLogin(String username, String password);

    /**
     * Method performs username change for loggedin user
     *
     * @param user is a user
     */
    void changeAuthenticatedUser(User user);
}

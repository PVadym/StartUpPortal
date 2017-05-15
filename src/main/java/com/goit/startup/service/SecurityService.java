package com.goit.startup.service;

import org.springframework.stereotype.Service;

/**
 * The class is responsible for autologin
 *
 * @author Perevoznyk Pavlo
 *         created on 15 may 2017
 * @version 1.0
 */

public interface SecurityService {

    public void autoLogin(String username, String password);
}

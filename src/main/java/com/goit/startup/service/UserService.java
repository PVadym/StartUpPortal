package com.goit.startup.service;

import com.goit.startup.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.goit.startup.entity.User;

import java.util.Collection;

/**
 * Interface provides a set of methods for the operation with {@link User}.
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public interface UserService extends DataService<User>, UserDetailsService {

    /**
     * The method founds user in database by username
     *
     * @param username a name of user
     * @return founded uses with entered name
     */
    User getByUsername(String username);

    /**
     * The method founds user in database by name and removes it
     *
     * @param username a name of user
     */
    void removeByUsername(String username);

    /**
     * The method finds all users in database with entered role
     *
     * @param userRole a users`s role
     * @return collection of users with entered role
     */
    Collection<User> getByRole(UserRole userRole);

    /**
     * The method finds all users in database with role ADMIN
     *
     * @return collection of users with role ADMIN
     */
    Collection<User> getAdmins();

    /**
     * The method finds all users in database with role USER
     *
     * @return collection of users with role USER
     */
    Collection<User> getUsers();

    /**
     * Method for getting authenticated user
     *
     * @return authenticated user
     */
    User getAuthenticatedUser();

    /**
     * Method to analyze role of authenticated user
     *
     * @return boolean value. If authenticated user has role ADMIN, the method returns true, and return false otherwise.
     */
    boolean isAuthenticatedAdmin();
}

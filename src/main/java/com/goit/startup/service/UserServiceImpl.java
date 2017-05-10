package com.goit.startup.service;
import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import com.goit.startup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class provides a set of methods for operations with Users.
 *
 * @author Вадим
 * @version 1.0
 */
@Service
public class UserServiceImpl extends DataServiceImpl<User> implements UserService {

    /**
     * An instance of UserRepository
     */
    private UserRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements
     *                   DataRepository interface for working with User
     */
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * The method founds user in database by username
     *
     * @param username a name of user
     * @return founded user with entered name
     * @throws IllegalArgumentException in case if user`s name is NULL or whitespaces
     * @throws NullPointerException     in case if user with entered name
     *                                  is not exist in database
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) throws IllegalArgumentException, NullPointerException {
        if (isBlank(username)) {
            throw new IllegalArgumentException("Incorrect name of product.");
        }
        final User user = repository.findByUsername(username);
        if (user == null) {
            throw new NullPointerException("User with name " + username + " is not exist in database.");
        }
        return user;
    }

    /**
     * The method removes user with entered name
     *
     * @param username a name of user
     */
    @Override
    @Transactional
    public void removeByUsername(String username) {
        if (isNotBlank(username)) {
            repository.deleteByUsername(username);
        }
    }

    /**
     * The method finds all users in database with entered role
     *
     * @param userRole a users`s role
     * @return collection of users with entered role
     * @throws IllegalArgumentException in case entered user`s role is NULL
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getByRole(UserRole userRole) throws IllegalArgumentException {
        if (userRole == null) {
            throw new IllegalArgumentException("Entered user`s role is 'null'.");
        }
        return repository.findAllByRole(userRole);
    }

    /**
     * The method finds all users in database with role ADMIN
     *
     * @return collection of users with role ADMIN
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdmins() {
        return repository.findAllByRole(UserRole.ADMIN);
    }

    /**
     * The method finds all users in database with role USER
     *
     * @return collection of users with role USER
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getUsers() {
        return repository.findAllByRole(UserRole.USER);
    }

    /**
     * Method for getting authenticated user
     *
     * @return authenticated user
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            user = new User();
            user.setUsername("anonymousUser");
        }
        return user;
    }

    /**
     * Method to analyze role of authenticated user
     *
     * @return boolean value. If authenticated user has role ADMIN, the method returns true, and return false otherwise.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isAuthenticatedAdmin() {
        return getAuthenticatedUser().getRole().equals(UserRole.ADMIN);
    }

    /**
     * The method load user from database by user`s name
     *
     * @param username a user`s name
     * @return user with entered name
     * @throws UsernameNotFoundException in case if user with entered name is not exist in database
     * @throws IllegalArgumentException  in case if user`s name is NULL or whitespaces
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws IllegalArgumentException, UsernameNotFoundException {
        User user;
        try {
            user = getByUsername(username);
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return user;
    }
}


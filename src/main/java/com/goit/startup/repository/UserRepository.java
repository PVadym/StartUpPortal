package com.goit.startup.repository;

import com.goit.startup.entity.User;
import com.goit.startup.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


/**
 * Interface provides a set of methods for the operation with entity {@link User}
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public interface UserRepository extends DataRepository<User> {

    /**
     * The method finds user in database by name
     *
     * @param username a users`s name
     * @return user with entered name
     */
    User findByUsername(String username);
    /**
     * The method removes user from database
     *
     * @param username a name of user, which must be removed
     */
    void deleteByUsername(String username);

    /**
     * The method finds all users in database with entered role
     *
     * @param userRole a users`s role
     * @return collection of users with entered role
     */
    Collection<User> findAllByRole(UserRole userRole);

}

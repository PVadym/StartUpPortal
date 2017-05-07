package com.goit.startup.repository;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Interface provides a set of methods for the operation with entity {@link Investment}
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    /**
     * The method finds all investments in database thad were made by entered user
     *
     * @param user the user whose investments we are looking for
     * @return a collection of investments that were made by entered user
     */
    Collection<Investment> findAllByUser(User user);

    /**
     * The method finds all investments in database thad were made into entered startup
     *
     * @param startup a startup, the investments we are looking for
     * @return a collection of investments thad were made into entered startup
     */
    Collection<Investment> findAllByStartup(Startup startup);

}

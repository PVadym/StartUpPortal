package com.goit.startup.repository;

import com.goit.startup.entity.Investment;
import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * TODO
 */
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    Collection<Investment> findAllByUser(User user);

    Collection<Investment> findAllByUser(Startup startup);

}

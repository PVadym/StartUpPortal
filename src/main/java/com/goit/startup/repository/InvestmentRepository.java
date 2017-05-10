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
public interface InvestmentRepository extends DataRepository<Investment> {

}

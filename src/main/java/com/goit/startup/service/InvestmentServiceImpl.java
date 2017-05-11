package com.goit.startup.service;

import com.goit.startup.entity.Investment;
import com.goit.startup.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A class provides a set of methods for the operation with investments
 * implements {@link InvestmentService}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Service
public class InvestmentServiceImpl extends DataServiceImpl<Investment> implements InvestmentService {

    /**
     * An instance of InvestmentRepository
     */
    private InvestmentRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements
     *                   DataRepository interface for working with investments
     */
    @Autowired
    public InvestmentServiceImpl(InvestmentRepository repository) {
        super(repository);
        this.repository = repository;
    }
}

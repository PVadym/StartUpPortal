package com.goit.startup.service;

import com.goit.startup.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 */
@Service
public class InvestmentServiceImpl implements InvestmentService {

    private InvestmentRepository repository;

    @Autowired
    public InvestmentServiceImpl(InvestmentRepository repository) {
        this.repository = repository;
    }
}

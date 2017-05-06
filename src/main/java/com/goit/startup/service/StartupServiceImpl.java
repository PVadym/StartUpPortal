package com.goit.startup.service;

import com.goit.startup.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 */
@Service
public class StartupServiceImpl implements StartupService {

    private StartupRepository repository;

    @Autowired
    public StartupServiceImpl(StartupRepository repository) {
        this.repository = repository;
    }
}

package com.goit.startup.service;

import com.goit.startup.entity.Startup;
import com.goit.startup.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * A class provides a set of methods for the operation with startups
 * implements {@link StartupService}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */

@Service
public class StartupServiceImpl extends DataServiceImpl<Startup> implements StartupService {

    /**
     * An instance of StartupRepository
     */
    private StartupRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements
     *                   DataRepository interface for working with startups
     */
    @Autowired
    public StartupServiceImpl(StartupRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * The method founds startup in database by name
     *
     * @param name a title of startup
     * @return founded startup
     * @throws IllegalArgumentException in case if startup name is NULL or whitespaces
     * @throws NullPointerException     in case if startup with entered name
     *                                  is not exist in database
     */
    @Override
    @Transactional(readOnly = true)
    public Startup getByName(String name) throws IllegalArgumentException, NullPointerException {
        if (isBlank(name)) {
            throw new IllegalArgumentException("Incorrect name of startup.");
        }
        Startup startup = repository.findByName(name);
        if (startup == null) {
            throw new NullPointerException("Startup with name " + name + " is not exist in database.");
        }
        return startup;
    }

    /**
     * The method founds startup in database by name and removes it
     *
     * @param name a name of startup
     */
    @Override
    @Transactional
    public void removeByName(String name) {
        if (isNotBlank(name)) {
            repository.deleteByName(name);
        }
    }

    /**
     * The method finds all startups in database that contain in their name or description entered keyWord
     *
     * @param keyWord a word that we are trying to find in startups name and description
     * @return a collection of startups that contain entered word in their name or description,
     *          if entered word is NULL or whitespaces returns all startups from data base
     */
    @Override
    @Transactional (readOnly = true)
    public Collection<Startup> findAllByKeyWord(String keyWord) {
        Collection<Startup> startups;
        if (isBlank(keyWord)) {
            startups = super.getAll();
        } else {
            startups = repository.findAllByNameOrDescriptionIgnoreCaseContaining(keyWord);
        }
        return startups;
    }

}

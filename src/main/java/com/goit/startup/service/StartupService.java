package com.goit.startup.service;

import com.goit.startup.entity.Startup;

import java.util.Collection;

/**
 * Interface provides a set of methods for the operation with {@link Startup}.
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public interface StartupService extends DataService<Startup> {

    /**
     * The method founds startup in database by name.
     *
     * @param name a name of startup.
     * @return founded startup.
     */
    Startup getByName(String name);

    /**
     * The method founds startup in database by name and removes it.
     *
     * @param name a name of startup.
     */
    void removeByName(String name);

    /**
     * The method finds all startups in database that contain in their name or description entered keyWord
     *
     * @param keyWord a word that we are trying to find in startups name and description
     * @return a collection of startups that contain entered word in their name or description
     */
    Collection<Startup> findAllByKeyWord(String keyWord);
}

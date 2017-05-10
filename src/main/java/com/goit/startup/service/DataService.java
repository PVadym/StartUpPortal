package com.goit.startup.service;

import com.goit.startup.entity.Model;

import java.util.Collection;

/**
 * Interface provides a set of methods for the operation with entities.
 *
 * @param <T> Entity type, extends {@link Model}.
 * @author Вадим
 * @version 1.0
 */
public interface DataService<T extends Model> {

    /**
     * The method adds entity to database
     *
     * @param t entity for adding to database
     * @return entity which was add to database
     */
    T add(T t);

    /**
     * The method adds all collection of entities to database
     *
     * @param collection collection of entities for adding to database
     * @return collection of entities which was add to database
     */
    Collection<T> addAll(Collection<T> collection);

    /**
     * The method updates entity in a database
     *
     * @param t an entity with new parameters for updating
     * @return entity which was update in database
     */
    T update(T t);

    /**
     * The method updates all entities from collection in database
     *
     * @param collection a collection of entities with new parameters for updating
     * @return collection of entities which was update in database
     */
    Collection<T> updateAll(Collection<T> collection);

    /**
     * The method find entity by unique identifier in database
     *
     * @param id a unique identifier of entity
     * @return entity with entered id from database
     */
    T get(long id);

    /**
     * The method finds all entities from database
     *
     * @return collection of entities
     */
    Collection<T> getAll();

    /**
     * The method removes entity from database by unique identifier
     *
     * @param id a unique identifier of entity
     */
    void remove(long id);

    /**
     * The method removes entity from database
     *
     * @param t entity that should be removed
     */
    void remove(T t);

    /**
     * The method removes collection of entities from database
     *
     * @param collection collection of entities that should be removed
     */
    void remove(Collection<T> collection);

    /**
     * The method removes all entities from database
     */
    void removeAll();

    /**
     * The method checks the existence of an entity in the database by unique identifier
     *
     * @param id a unique identifier of entity
     * @return true if entity is exist in the database or false if it is not exist
     */
    boolean exist(long id);

    /**
     * The method checks the existence of an entity in the database
     *
     * @param t entity that should be checked
     * @return true if entity is exist in the database or false if it is not exist
     */
    boolean exist(T t);
}

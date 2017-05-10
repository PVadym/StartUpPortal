package com.goit.startup.service;
import com.goit.startup.entity.Model;
import com.goit.startup.repository.DataRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * provides a set of methods for operations with entities.
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public abstract class DataServiceImpl<T extends Model> implements DataService<T> {

    /**
     * An instance of class that implements DataRepository interface
     * for working with T entity
     */
    private DataRepository<T> repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements DataRepository
     *                   interface for working with T entity
     */
    public DataServiceImpl(DataRepository<T> repository) {
        this.repository = repository;
    }

    /**
     * Method for adding a model into database
     *
     * @param model a model to add
     * @return added model
     * @throws IllegalArgumentException if the method gets 'null' model
     */
    @Override
    @Transactional
    public T add(T model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Trying to save 'null'");
        }
        return repository.save(model);
    }

    /**
     * Method for updating a model in database
     *
     * @param model a model to update
     * @return updated model
     */
    @Override
    @Transactional
    public T update(T model) {
        return this.add(model);
    }

    /**
     * Method for adding a collection of models into database
     *
     * @param collection a collection of models to add
     * @return a collection of added models
     */
    @Override
    @Transactional
    public Collection<T> addAll(Collection<T> collection) {
        Collection<T> addedModels = new ArrayList<>();
        if (collection != null) {
            collection.forEach(model -> addedModels.add(this.add(model)));
        }
        return addedModels;
    }

    /**
     * Method for updating a collection of models in database
     *
     * @param collection a collection of models to update
     * @return a collection of updated models
     */
    @Override
    public Collection<T> updateAll(Collection<T> collection) {
        return this.addAll(collection);
    }

    /**
     * Method for founding model id database by id
     *
     * @param id a unique identifier of model, that need to be founded
     * @return founded model
     * @throws NullPointerException if model with entered id isn't founded
     */
    @Override
    @Transactional(readOnly = true)
    public T get(long id) throws NullPointerException {
        T foundedModel = repository.findOne(id);
        if (foundedModel == null) {
            throw new NullPointerException("Can't find model with id = " + id);
        }
        return foundedModel;
    }

    /**
     * Method for getting all models of a particular type from database
     *
     * @return a collection of all models of a particular type from database
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll() {
        return repository.findAll();
    }

    /**
     * Method for removing model from database by id
     *
     * @param id a unique identifier of model, that needs to be removed
     */
    @Override
    @Transactional
    public void remove(long id) {
        repository.delete(id);
    }

    /**
     * Method for removing model from database
     *
     * @param model a model, that needs to be removed
     * @throws IllegalArgumentException if the method gets 'null' model
     */
    @Override
    @Transactional
    public void remove(T model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Trying to remove 'null'");
        }
        repository.delete(model);
    }

    /**
     * Method for removing collection of models from database
     *
     * @param collection a collection of models, that need to be removed
     * @throws IllegalArgumentException if the method gets 'null' collection
     */
    @Override
    @Transactional
    public void remove(Collection<T> collection) throws IllegalArgumentException {
        if (collection == null) {
            throw new IllegalArgumentException("Collection to remove is 'null'");
        }
        collection.forEach(this::remove);
    }

    /**
     * Method for removing all models of a particular type from database
     */
    @Override
    @Transactional
    public void removeAll() {
        repository.deleteAll();
    }

    /**
     * Method checks if model with entered id exists in database, or not
     *
     * @param id a unique identifier of model
     * @return boolean value, 'true' if model with entered id exists in database, or 'false' otherwise
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exist(long id) {
        return repository.exists(id);
    }

    /**
     * Method for checking existence of model in database
     *
     * @param model a model, the existence of which needs to be checked
     * @return boolean value, 'true' if model exists in database, or 'false' otherwise
     * @throws IllegalArgumentException if the method gets 'null' model
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exist(T model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Trying to check existing of 'null' model");
        }
        return this.exist(model.getId());
    }
}
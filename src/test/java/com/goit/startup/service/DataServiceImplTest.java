package com.goit.startup.service;

import com.goit.startup.entity.Model;
import com.goit.startup.repository.DataRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Abstract lass for testing {@link DataService}.
 *
 * @author Slava Makhinich
 * Created on 23.05.2017.
 *
 */
public abstract class DataServiceImplTest<T extends Model> {

    /**
     * Instance of implementation {@link DataService}.
     */
    private DataService<T> dataService;

    /**
     * Instance of implementation {@link DataRepository}.
     */
    private DataRepository<T> repository;

    /**
     * Instance of a class that extends {@link Model}.
     */
    private T object;

    @Before
    public void initialize() {
        this.dataService = getService();
        this.repository = getRepository();
        this.object = getObject();
    }

    @Test(expected = IllegalArgumentException.class)
    public void add() throws Exception {
        when(repository.save(object)).thenReturn(object);
        assertEquals(object, dataService.add(object));
        verify(repository).save(object);
        dataService.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void get() throws Exception {
        when(repository.findOne(object.getId())).thenReturn(object);
        assertEquals(object, dataService.get(object.getId()));
        when(repository.getOne(456l)).thenReturn(null);
        dataService.get(456);
    }

    @Test
    public void update() throws Exception {
        when(repository.save(object)).thenReturn(object);
        assertEquals(object, dataService.update(object));
        verify(repository).save(object);
    }

    @Test
    public void addAll() throws Exception {
        Collection<T> collection = null;
        dataService.addAll(collection);
        for (T model: getObjects()) {
            when(repository.save(model)).thenReturn(model);
        }
        assertEquals(getObjects(), dataService.addAll(getObjects()));
        for (T model: getObjects()) {
            verify(repository).save(model);
        }
    }

    @Test
    public void updateAll() throws Exception {
        for (T model: getObjects()) {
            when(repository.save(model)).thenReturn(model);
        }
        assertEquals(getObjects(), dataService.updateAll(getObjects()));
        for (T model: getObjects()) {
            verify(repository).save(model);
        }
    }

    @Test
    public void getAll() throws Exception {
        when(repository.findAll()).thenReturn(getObjects());
        assertEquals(getObjects(), dataService.getAll());
    }

    @Test(expected = IllegalArgumentException.class)
    public void remove() throws Exception {
        long id = 33;
        dataService.remove(id);
        verify(repository).delete(id);
        dataService.remove(object);
        verify(repository).delete(object);
        T modelNull = null;
        dataService.remove(modelNull);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCollection() throws Exception {
        dataService.remove(getObjects());
        for (T object : getObjects()) {
            verify(repository).delete(object);
        }
        Collection<T> collection = null;
        dataService.remove(collection);
    }

    @Test
    public void removeAll() throws Exception {
        dataService.removeAll();
        verify(repository).deleteAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void exist() throws Exception {
        long id = 555;
        when(repository.exists(id)).thenReturn(true);
        assertTrue(dataService.exist(id));
        verify(repository).exists(id);
        when(repository.exists(object.getId())).thenReturn(true);
        assertTrue(dataService.exist(object));
        verify(repository).exists(object.getId());
        T model = null;
        dataService.exist(model);
    }

    /**
     * Abstract getter for field dataService.
     *
     * @return field dataService.
     */
    protected abstract DataService<T> getService();

    /**
     * Abstract getter for field object.
     *
     * @return field object.
     */
    protected abstract T getObject();

    /**
     * Abstract method to get list of objects.
     *
     * @return list of objects.
     */
    protected abstract List<T> getObjects();

    /**
     * Abstract getter for field repository.
     *
     * @return field repository.
     */
    protected abstract DataRepository<T> getRepository();

}
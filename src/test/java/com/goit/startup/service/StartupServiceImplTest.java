package com.goit.startup.service;

import com.goit.startup.entity.Startup;
import com.goit.startup.repository.DataRepository;
import com.goit.startup.repository.StartupRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class for testing {@link StartupServiceImpl}.
 *
 * @author Slava Makhinich
 * Created on 23.05.2017.
 * @version 1.0
 */
public class StartupServiceImplTest extends DataServiceImplTest<Startup> {

    /**
     * An instance of {@link StartupServiceImpl}
     */
    private StartupServiceImpl startupService;

    /**
     * An instance of {@link Startup}.
     */
    private Startup startup;

    /**
     * An instance of implementation {@link StartupRepository}.
     */
    private StartupRepository repository;

    /**
     * Constructor
     */
    public StartupServiceImplTest() {
        this.startup = new Startup();
        this.startup.setId(1);
        this.startup.setName("for test");
        this.startup.setDescription("description for test");
        this.startup.setMinInvestment(1);
        this.startup.setNeedInvestment(1);
        this.repository = mock(StartupRepository.class);
        this.startupService = new StartupServiceImpl(repository);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getByName() throws Exception {
        when(repository.findByName(startup.getName())).thenReturn(startup);
        assertEquals(startup, startupService.getByName(startup.getName()));
        verify(repository).findByName(startup.getName());
        startupService.getByName(" ");
    }

    @Test(expected = NullPointerException.class)
    public void getByNameNull() throws Exception {
        when(repository.findByName("not exist")).thenReturn(null);
        startupService.getByName("not exist");
    }

    @Test
    public void removeByName() throws Exception {
        startupService.removeByName(" ");
        verify(repository, never()).deleteByName(any());
        startupService.removeByName("to delete");
        verify(repository).deleteByName("to delete");
    }

    @Test
    public void findAllByKeyWord() throws Exception {
        when(repository.findAllByKeyWord("testFind")).thenReturn(getObjects());
        assertEquals(startupService.findAllByKeyWord("testFind"), getObjects());
    }

    @Test
    public void findAllByKeyWordBlank() throws Exception {
        when(repository.findAll()).thenReturn(getObjects());
        assertEquals(startupService.findAllByKeyWord(" "), getObjects());
    }

    /**
     * Getter for field startupService.
     *
     * @return field startupService.
     */
    @Override
    protected DataService<Startup> getService() {
        return this.startupService;
    }

    /**
     * Getter for field startup.
     *
     * @return field startup.
     */
    @Override
    protected Startup getObject() {
        return this.startup;
    }

    /**
     * Getter for list of startups.
     *
     * @return list of startups.
     */
    @Override
    protected List<Startup> getObjects() {
        List<Startup> startups = new ArrayList<>();
        startups.add(startup);
        Startup startup1 = new Startup();
        startup1.setId(2);
        startup1.setName("for test 1");
        startup1.setDescription("description for test 1");
        startup1.setMinInvestment(2);
        startup1.setNeedInvestment(2);
        startups.add(startup1);
        return startups;
    }

    /**
     * Getter for field repository.
     *
     * @return field repository.
     */
    @Override
    protected DataRepository<Startup> getRepository() {
        return this.repository;
    }
}
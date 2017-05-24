package com.goit.startup.controller;

import com.goit.startup.service.ImageServiceImplTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for testing {@link DevelopersController}.
 *
 * @author Slava Makhinich
 * Created on 24.05.2017.
 * @version 1.0
 */
public class DevelopersControllerTest {
    @Test
    public void developersPage() throws Exception {
        DevelopersController controller = new DevelopersController();
        assertEquals(controller.developersPage(), "/developers");
    }

}
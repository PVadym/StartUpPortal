package com.goit.startup.controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for testing {@link FaviconController}.
 *
 * @author Slava Makhinich
 * Created on 24.05.2017.
 * @version 1.0
 */
public class FaviconControllerTest {
    @Test
    public void favicon() throws Exception {
        FaviconController controller = new FaviconController();
        assertEquals(controller.favicon(), "forward:/resources/favicon.ico");
    }

}
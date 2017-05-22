package com.goit.startup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for website ico
 *
 * @author Perevoznyk Pavlo
 *         created on 16 may 2017
 * @version 1.0
 */
@Controller
public class FaviconController {

    /**
     * Method returns a location of website logo
     *
     * @return an address of location a website ico
     */
    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/resources/favicon.ico";
    }
}

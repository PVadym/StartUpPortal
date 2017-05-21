package com.goit.startup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class for directing to the developers page
 *
 * @author Slava Makhinich on 21.05.2017.
 * @version 1.0
 */
@Controller
public class DevelopersController {

    @RequestMapping(value = "/developers")
    public String developersPage() {
        return "/developers";
    }
}

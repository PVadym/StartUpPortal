package com.goit.startup.controller;

import com.goit.startup.entity.Startup;
import com.goit.startup.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Contended;

/**
 * The class is responsible of processing {@link com.goit.startup.entity.Startup} entity related requests
 *
 * @author Perevoznyk Pavlo
 *         created on 14 may 2017
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/startups")
public class StartUpController {

    private StartupService startupService;

    @Autowired
    public StartUpController(StartupService startupService) {
        this.startupService = startupService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStartupPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("startUp", new Startup());
        modelAndView.setViewName("addStartUp");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStartup(Startup startup){
        startupService.add(startup);
        return "redirect:/";
    }
}

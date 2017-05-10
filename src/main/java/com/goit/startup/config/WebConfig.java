package com.goit.startup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Spring MVC configuration class
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.goit.startup.controller", "com.goit.startup.config" })
@PropertySource("classpath:content.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * a parameter of content type
     */
    @Value("${view.type}")
    private String contentType;

    /**
     * a parameter of URL path to locate the view
     */
    @Value("${view.name-prefix}")
    private String viewPrefix;

    /**
     * a parameter type file of view
     */
    @Value("${view.name-suffix}")
    private String viewSuffix;

    /**
     * a parameter, that allows to use bean as a attribute
     */
    @Value("${view.expose_beans_as_attributes}")
    private boolean exposeContextBeansAsAttributes;

    /**
     * a location of resource handler
     */
    @Value("${resources.handler}")
    private String resourcesHandler;

    /**
     * a location of configuration file
     */
    @Value("${resources.location}")
    private String resourcesLocation;

    /**
     * The method configures a views
     *
     * @return an instance of {@link ViewResolver}
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setContentType(contentType);
        viewResolver.setPrefix(viewPrefix);
        viewResolver.setSuffix(viewSuffix);
        viewResolver.setExposeContextBeansAsAttributes(exposeContextBeansAsAttributes);
        return viewResolver;
    }

    /**
     * The method configures an instance of {@link ResourceHandlerRegistry}
     *
     * @param resourceHandlerRegistry an instance of {@link ResourceHandlerRegistry}
     */
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(resourcesHandler)
                .addResourceLocations(resourcesLocation);
    }

}

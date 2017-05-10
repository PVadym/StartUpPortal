package com.goit.startup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * The class is a configuration class for Application initialization
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Configuration
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * The field defines type of encoding to be used
     */
    private static final String ENCODING = "UTF-8";

    /**
     * The field defines type of force encoding to be used
     */
    private static final String FORCE_ENCODING = "true";

    /**
     * If true
     */
    private static final boolean MAPPING_FOR_URL_IS_MATCH_AFTER = true;

    /**
     * The field defines mapping pattern for URL
     */
    private static final String MAPPING_FOR_URL_PATTERNS = "/*";

    /**
     * If true exception will be thrown in case handler hasn't been found
     */
    private static final boolean THROW_EXCEPTION_IF_NO_HANDLER_FOUND = true;

    /**
     * The method returns classes of root config
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                RootConfig.class,
                SecurityConfig.class
        };
    }

    /**
     * The method returns servlet mappings
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /**
     * The method is executed on startup and configure filter
     *
     * @param servletContext accepts related {@link ServletContext}
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic dynamic = servletContext
                .addFilter("encodingFilter", new CharacterEncodingFilter());
        dynamic.setInitParameter("encoding", ENCODING);
        dynamic.setInitParameter("forceEncoding", FORCE_ENCODING);
        dynamic.addMappingForUrlPatterns(
                null,
                MAPPING_FOR_URL_IS_MATCH_AFTER,
                MAPPING_FOR_URL_PATTERNS
        );
    }

    /**
     * The method returns {@link DispatcherServlet} instance
     *
     * @param servletAppContext accepts related {@link WebApplicationContext}
     */
    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(THROW_EXCEPTION_IF_NO_HANDLER_FOUND);
        return dispatcherServlet;
    }

    /**
     * The method returns servlet configuration classes
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }
}

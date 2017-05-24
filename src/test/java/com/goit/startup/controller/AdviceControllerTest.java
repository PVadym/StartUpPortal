package com.goit.startup.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Class for testing {@link AdviceController}.
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class AdviceControllerTest {

    /**
     * An instance of {@link AdviceController}
     */
    private AdviceController adviceController;

    /**
     * An instance of implementation {@link HttpServletRequest}
     */
    private HttpServletRequest request;

    /**
     * Constructor
     */
    public AdviceControllerTest() {
        this.adviceController =  new AdviceController();
        this.request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void noHandlerFoundException() throws Exception {
        ModelAndView modelAndView = adviceController.NoHandlerFoundException(new NoHandlerFoundException("theMethod()", "test URL", new HttpHeaders()), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "NoHandlerFoundException : No handler found for theMethod() test URL");
        assertEquals(models.get("status"), HttpStatus.NOT_FOUND.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void nullPointerException() throws Exception {
        ModelAndView modelAndView = adviceController.NullPointerException(new NullPointerException("test"), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "NullPointerException : test");
        assertEquals(models.get("status"), HttpStatus.BAD_REQUEST.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void illegalArgumentException() throws Exception {
        ModelAndView modelAndView = adviceController.IllegalArgumentException(new IllegalArgumentException("test"), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "IllegalArgumentException : test");
        assertEquals(models.get("status"), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void httpRequestMethodNotSupportedException() throws Exception {
        ModelAndView modelAndView = adviceController.HttpRequestMethodNotSupportedException(new HttpRequestMethodNotSupportedException("test"), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "HttpRequestMethodNotSupportedException : Request method 'test' not supported");
        assertEquals(models.get("status"), HttpStatus.UNAUTHORIZED.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void illegalAccessException() throws Exception {
        ModelAndView modelAndView = adviceController.IllegalAccessException(new IllegalAccessException("test"), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "IllegalAccessException : test");
        assertEquals(models.get("status"), HttpStatus.FORBIDDEN.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void illegalMappingException() throws Exception {
        ModelAndView modelAndView = adviceController.IllegalMappingException(new IllegalMappingException("test"), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "IllegalMappingException : test");
        assertEquals(models.get("status"), HttpStatus.METHOD_NOT_ALLOWED.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void exception() throws Exception {
        ModelAndView modelAndView = adviceController.Exception(new Exception("test"), request);
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("message"), "Exception : test");
        assertEquals(models.get("status"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

}
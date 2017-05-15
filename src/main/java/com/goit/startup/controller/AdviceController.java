package com.goit.startup.controller;

import org.apache.log4j.Logger;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Class intercepts exceptions that are thrown by another classes methods
 */
@ControllerAdvice
public class AdviceController {

    /**
     * An instance of Logger for logging information
     */
    private static final Logger logger = Logger.getLogger(AdviceController.class);

    /**
     * Intercepts and handles NoHandlerFoundException.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView NoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    /**
     * Intercepts and handles NullPointerException.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView NullPointerException(
            NullPointerException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }

    /**
     * Intercepts and handles IllegalArgumentException.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ModelAndView IllegalArgumentException(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Intercepts and handles HttpRequestMethodNotSupportedException.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ModelAndView HttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Intercepts and handles IllegalAccessException.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView IllegalAccessException(
            IllegalAccessException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.FORBIDDEN);
    }

    /**
     * Intercepts and handles IllegalMappingException.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(IllegalMappingException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView IllegalMappingException(
            IllegalMappingException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Intercepts and handles all other Exceptions.
     *
     * @param ex      an intercepted exception.
     * @param request HTTP servlets request.
     * @return new ModelAndView object with information about exception.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView Exception(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * The method creates and returns ModelAndView object.
     *
     * @param status  a http status.
     * @param message an exception's message.
     * @return new ModelAndView object with information about exception.
     */
    private ModelAndView prepareModelAndView(HttpStatus status, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.addObject("status", status.value());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    /**
     * Handles all Exceptions.
     *
     * @param ex      an exception.
     * @param request HTTP servlets request.
     * @param status  a http status.
     * @return new ModelAndView object with information about exception.
     */
    private ModelAndView handleException(
            Exception ex,
            HttpServletRequest request,
            HttpStatus status
    ) {
        logRequest(request);
        logException(ex);
        return prepareModelAndView(status, ex.getClass().getSimpleName() + " : " + ex.getMessage());
    }

    /**
     * Error logging
     *
     * @param ex intercepted exception
     */
    private static void logException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    /**
     * Request logging
     *
     * @param request HTTP servlets request
     */
    private static void logRequest(final HttpServletRequest request) {
        if (request != null) {
            logger.error(request.getRemoteAddr() + " : " + request.getRequestURI());
        }
    }
}

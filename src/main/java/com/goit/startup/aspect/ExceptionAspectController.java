package com.goit.startup.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * A Class intercepts exceptions that throw out during
 * the work of methods of other classes.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Aspect
@Component
public class ExceptionAspectController {

    /**
     * An instance of Logger for logging information
     */
    private static final Logger LOGGER = Logger.getLogger(ExceptionAspectController.class);

    /**
     * Method register information about the exception:
     *
     * @param ex a exception, which will be register in method
     */
    @AfterThrowing(
            pointcut = "execution(* com.goit.startup..controller..*(..))",
            throwing = "ex"
    )
    public void afterThrowingAdvice(Exception ex) {
        LOGGER.error("EXCEPTION IN METHOD " + ex.getClass());
    }
}

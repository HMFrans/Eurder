package com.switchfully.eurder.service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerEurder extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerEurder.class);

    @ExceptionHandler({IllegalArgumentException.class, UserNotFoundException.class})
    protected void badRequestExceptions(Exception exception, HttpServletResponse response) throws IOException {
        log.error(exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}

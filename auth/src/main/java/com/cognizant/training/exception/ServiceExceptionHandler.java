package com.cognizant.training.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Custom response entity exception handler for handling custom exceptions.
 *
 * @author William Simpson
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return new ServiceError(HttpStatus.UNAUTHORIZED, ex.getMessage()).buildResponseEntity();
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException ex) {
        return new ServiceError(HttpStatus.UNAUTHORIZED, ex.getMessage()).buildResponseEntity();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleConstrainViolation(SQLIntegrityConstraintViolationException ex) {
        return new ServiceError(HttpStatus.BAD_REQUEST, ex.getMessage()).buildResponseEntity();
    }
}

package com.cognizant.training.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * Represents and error within the service generated anywhere through the request and response lifecycle.
 *
 * @author William Simpson
 */
public class ServiceError {

    /**
     * Local date time stamp that the error was created.
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;

    /**
     * HTTP status code of the Error
     */
    @Getter
    private HttpStatus status;

    /**
     * Customizable error code message
     */
    @Getter
    private String message;

    /**
     * Java localized debug string if from a throwable error
     */
    @Getter
    private String debugMessage;

    /**
     * Setups up the timestamp of the error
     */
    private ServiceError() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Creates an error from a given HTTP status response code
     *
     * @param status status of the response
     */
    public ServiceError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Creates an error from a given HTTP status response code and the throwable exception that caused the error
     *
     * @param status status of the response
     * @param ex exception that caused the error
     */
    public ServiceError(HttpStatus status, Throwable ex) {
        this(status);
        this.debugMessage = ex.getLocalizedMessage();
        this.message = "Unexpected error";
    }

    /**
     * Creates an error from a given HTTP status response code and a given message
     *
     * @param status status of the response
     * @param message message response
     */
    public ServiceError(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }

    /**
     * Creates an error from a given HTTP status response code, a customized error message, and the exception that
     * caused the error.
     *
     * @param status status of the response
     * @param message customized error message
     * @param ex exception that caused the error
     */
    public ServiceError(HttpStatus status, String message, Throwable ex) {
        this(status, ex);
        this.message = message;
    }

    public ResponseEntity<Object> buildResponseEntity() {
        return new ResponseEntity<>(this, this.status);
    }
}

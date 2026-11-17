package com.college.eventmgmt.exception;

/**
 * Thrown when an invalid rating or feedback parameter is submitted.
 */
public class InvalidFeedbackException extends Exception {
    public InvalidFeedbackException(String message) {
        super(message);
    }
}

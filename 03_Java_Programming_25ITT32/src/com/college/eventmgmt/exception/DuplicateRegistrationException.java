package com.college.eventmgmt.exception;

/**
 * Thrown when a student attempts to register more than once for the same event.
 * Enforces Functional Requirement FR-03.
 */
public class DuplicateRegistrationException extends Exception {
    public DuplicateRegistrationException(String message) {
        super(message);
    }

    public DuplicateRegistrationException(String studentId, String eventId) {
        super(String.format("Registration Rejected: Student [%s] is already registered for Event [%s].", studentId, eventId));
    }
}

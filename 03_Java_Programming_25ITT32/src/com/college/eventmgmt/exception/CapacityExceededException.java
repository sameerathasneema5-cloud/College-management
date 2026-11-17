package com.college.eventmgmt.exception;

/**
 * Thrown when an event registration exceeds the pre-allocated event capacity.
 * Enforces Functional Requirement FR-03.
 */
public class CapacityExceededException extends Exception {
    public CapacityExceededException(String message) {
        super(message);
    }

    public CapacityExceededException(String eventId, int capacity) {
        super(String.format("Registration Rejected: Event [%s] has reached maximum capacity (%d seats). No vacancies remain.", eventId, capacity));
    }
}

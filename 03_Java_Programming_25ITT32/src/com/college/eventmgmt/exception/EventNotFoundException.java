package com.college.eventmgmt.exception;

/**
 * Thrown when an invalid or non-existent event ID is referenced.
 */
public class EventNotFoundException extends Exception {
    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(String eventId, boolean isId) {
        super(String.format("Event Not Found: No event exists with ID [%s].", eventId));
    }
}

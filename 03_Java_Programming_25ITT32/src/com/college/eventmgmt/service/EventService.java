package com.college.eventmgmt.service;

import com.college.eventmgmt.dao.EventDAO;
import com.college.eventmgmt.exception.CapacityExceededException;
import com.college.eventmgmt.exception.DuplicateRegistrationException;
import com.college.eventmgmt.exception.EventNotFoundException;
import com.college.eventmgmt.model.Event;
import com.college.eventmgmt.model.Feedback;
import com.college.eventmgmt.model.Registration;

import java.time.LocalDate;
import java.util.List;

/**
 * Service encapsulating core event management workflows.
 */
public class EventService {
    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Event createEvent(String eventId, String title, String description, LocalDate date, String venue, int capacity, String category) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        Event event = new Event(eventId, title, description, date, venue, capacity, category);
        eventDAO.addEvent(event);
        return event;
    }

    public Registration registerStudent(String studentId, String eventId)
            throws DuplicateRegistrationException, CapacityExceededException, EventNotFoundException {
        return eventDAO.registerStudentForEvent(studentId, eventId);
    }

    public boolean recordAttendance(String registrationId, boolean attended) {
        return eventDAO.markAttendance(registrationId, attended ? "ATTENDED" : "ABSENT");
    }

    public void submitFeedback(String studentId, String eventId, int rating, String comments) {
        String feedbackId = "FB-" + String.format("%03d", eventDAO.getAllFeedback().size() + 1);
        Feedback fb = new Feedback(feedbackId, studentId, eventId, rating, comments);
        eventDAO.submitFeedback(fb);
    }

    public List<Event> listAllEvents() {
        return eventDAO.getAllEvents();
    }

    public List<Event> listActiveEvents() {
        return eventDAO.getActiveEvents();
    }

    public Event getEventDetails(String eventId) throws EventNotFoundException {
        return eventDAO.getEvent(eventId);
    }
}

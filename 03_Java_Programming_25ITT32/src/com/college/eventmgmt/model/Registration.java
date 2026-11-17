package com.college.eventmgmt.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the M:N associative entity mapping a Student to an Event.
 * UML Class Diagram mapping: Registration
 */
public class Registration {
    private String registrationId;
    private String studentId;
    private String eventId;
    private LocalDateTime registrationDate;
    private String status; // CONFIRMED, WAITLISTED, CANCELLED

    public Registration(String registrationId, String studentId, String eventId, LocalDateTime registrationDate, String status) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.eventId = eventId;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public Registration(String registrationId, String studentId, String eventId) {
        this(registrationId, studentId, eventId, LocalDateTime.now(), "CONFIRMED");
    }

    // UML Methods
    public boolean checkEligibility(Student student, Event event) {
        return student != null && event != null && "ACTIVE".equalsIgnoreCase(event.getStatus());
    }

    public void confirmRegistrations() {
        this.status = "CONFIRMED";
    }

    public void cancelRegistration() {
        this.status = "CANCELLED";
    }

    // Getters and Setters
    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%-10s | Student: %-10s | Event: %-10s | Date: %s | Status: %s",
            registrationId, studentId, eventId, registrationDate.format(dtf), status);
    }
}

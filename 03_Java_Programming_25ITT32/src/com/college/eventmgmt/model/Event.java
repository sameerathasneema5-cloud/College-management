package com.college.eventmgmt.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an academic or co-curricular event organized by the college.
 * UML Class Diagram mapping: Event
 */
public class Event {
    private String eventId;
    private String title;
    private String description;
    private LocalDate date;
    private String venue;
    private int capacity;
    private String category;
    private int registeredCount;
    private String status; // ACTIVE, COMPLETED, CANCELLED

    public Event(String eventId, String title, String description, LocalDate date, String venue, int capacity, String category) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.capacity = capacity;
        this.category = category;
        this.registeredCount = 0;
        this.status = "ACTIVE";
    }

    public Event(String eventId, String title, String description, LocalDate date, String venue, int capacity, String category, int registeredCount, String status) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.capacity = capacity;
        this.category = category;
        this.registeredCount = registeredCount;
        this.status = status;
    }

    // UML Methods
    public synchronized boolean canAcceptRegistration() {
        return "ACTIVE".equalsIgnoreCase(this.status) && this.registeredCount < this.capacity;
    }

    public synchronized void incrementRegistration() {
        if (this.registeredCount < this.capacity) {
            this.registeredCount++;
        }
    }

    public synchronized void decrementRegistration() {
        if (this.registeredCount > 0) {
            this.registeredCount--;
        }
    }

    public String getEventDetails() {
        return String.format(
            "Event ID: %s%nTitle: %s%nCategory: %s%nDate: %s%nVenue: %s%nCapacity: %d | Registered: %d | Vacancies: %d%nStatus: %s%nDescription: %s",
            eventId, title, category, date, venue, capacity, registeredCount, (capacity - registeredCount), status, description
        );
    }

    public void updateEvent(String title, String venue, LocalDate date, int capacity) {
        this.title = title;
        this.venue = venue;
        this.date = date;
        this.capacity = capacity;
    }

    public void cancelEvent() {
        this.status = "CANCELLED";
    }

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRegisteredCount() {
        return registeredCount;
    }

    public void setRegisteredCount(int registeredCount) {
        this.registeredCount = registeredCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCapacityUtilization() {
        return capacity > 0 ? (registeredCount * 100.0) / capacity : 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-32s | %-18s | %-10s | %-18s | Cap: %3d | Reg: %3d | %s",
            eventId, title, category, date, venue, capacity, registeredCount, status);
    }
}

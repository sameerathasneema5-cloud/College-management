package com.college.eventmgmt.model;

import java.time.LocalDateTime;

/**
 * Represents post-event ratings and reviews submitted by student attendees.
 * UML Class Diagram mapping: Feedback
 */
public class Feedback {
    private String feedbackId;
    private String studentId;
    private String eventId;
    private String comments;
    private int rating; // 1 to 5
    private LocalDateTime submittedAt;

    public Feedback(String feedbackId, String studentId, String eventId, int rating, String comments, LocalDateTime submittedAt) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars.");
        }
        this.feedbackId = feedbackId;
        this.studentId = studentId;
        this.eventId = eventId;
        this.rating = rating;
        this.comments = comments;
        this.submittedAt = submittedAt;
    }

    public Feedback(String feedbackId, String studentId, String eventId, int rating, String comments) {
        this(feedbackId, studentId, eventId, rating, comments, LocalDateTime.now());
    }

    public String viewFeedback() {
        return String.format("[%s] Rating: %d/5 | Student: %s | Event: %s%nComments: \"%s\"",
            feedbackId, rating, studentId, eventId, comments);
    }

    // Getters and Setters
    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars.");
        }
        this.rating = rating;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public String toString() {
        return String.format("%-10s | Event: %-10s | Student: %-10s | Stars: %d/5 | \"%s\"",
            feedbackId, eventId, studentId, rating, comments);
    }
}

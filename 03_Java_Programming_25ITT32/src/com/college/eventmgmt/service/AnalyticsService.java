package com.college.eventmgmt.service;

import com.college.eventmgmt.dao.EventDAO;
import com.college.eventmgmt.model.Event;
import com.college.eventmgmt.model.Feedback;
import com.college.eventmgmt.model.ParticipationAnalysis;
import com.college.eventmgmt.model.Registration;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Analytical service utilizing Java Streams, Lambdas, and Collectors
 * Corresponds to Course Code: 25ITT32 - Unit 5 (Generics, Collections, Lambdas & Streams)
 */
public class AnalyticsService {
    private final EventDAO eventDAO;

    public AnalyticsService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    /**
     * Compute comprehensive ParticipationAnalysis for a specific event
     */
    public ParticipationAnalysis analyzeEvent(String eventId) {
        List<Registration> allRegs = eventDAO.getAllRegistrations().stream()
                .filter(r -> r.getEventId().equalsIgnoreCase(eventId) && !"CANCELLED".equalsIgnoreCase(r.getStatus()))
                .collect(Collectors.toList());

        int totalReg = allRegs.size();
        long attendedCount = allRegs.stream()
                .filter(r -> "ATTENDED".equalsIgnoreCase(eventDAO.getAttendanceStatus(r.getRegistrationId())))
                .count();

        List<Feedback> fbs = eventDAO.getFeedbackForEvent(eventId);
        double avgRating = fbs.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        String insights = String.format("Attendance: %d/%d (%.1f%%). Average feedback: %.2f/5.0 across %d reviews.",
                attendedCount, totalReg, (totalReg > 0 ? (attendedCount * 100.0 / totalReg) : 0.0), avgRating, fbs.size());

        String analysisId = "ANA-" + eventId.replace("EVT-", "");
        return new ParticipationAnalysis(analysisId, eventId, totalReg, (int) attendedCount, avgRating, insights);
    }

    /**
     * Top-ranked events by capacity utilization percentage using Java Streams
     */
    public List<Event> getTopUtilizedEvents(int limit) {
        return eventDAO.getAllEvents().stream()
                .sorted(Comparator.comparingDouble(Event::getCapacityUtilization).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Department-wise participant registration aggregation using Java Collectors.groupingBy
     */
    public Map<String, Long> getDepartmentRegistrationBreakdown() {
        return eventDAO.getAllRegistrations().stream()
                .map(r -> eventDAO.getStudent(r.getStudentId()))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        s -> s.getDepartment(),
                        Collectors.counting()
                ));
    }

    /**
     * Category-wise event count using Java Streams
     */
    public Map<String, Long> getCategoryEventDistribution() {
        return eventDAO.getAllEvents().stream()
                .collect(Collectors.groupingBy(
                        Event::getCategory,
                        Collectors.counting()
                ));
    }

    /**
     * Average rating across all events
     */
    public double getOverallAverageRating() {
        return eventDAO.getAllFeedback().stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);
    }
}

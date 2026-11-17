package com.college.eventmgmt.service;

import com.college.eventmgmt.model.ParticipationAnalysis;

import java.util.List;

/**
 * Background worker thread demonstrating Multithreading in Java.
 * Corresponds to Course Code: 25ITT32 - Unit 3 (Multithreaded Programming)
 */
public class ReportGeneratorThread extends Thread {
    private final AnalyticsService analyticsService;
    private final List<String> eventIds;
    private volatile boolean completed = false;

    public ReportGeneratorThread(AnalyticsService analyticsService, List<String> eventIds) {
        super("Report-Worker-Thread");
        this.analyticsService = analyticsService;
        this.eventIds = eventIds;
    }

    @Override
    public void run() {
        System.out.println("[THREAD] Starting background asynchronous report generation on: " + Thread.currentThread().getName());
        try {
            for (String eventId : eventIds) {
                // Simulate compute-heavy statistical aggregation
                Thread.sleep(150);
                ParticipationAnalysis analysis = analyticsService.analyzeEvent(eventId);
                System.out.printf("[THREAD-PROGRESS] Compiled analysis for %s -> Attendance Rate: %.2f%%%n",
                        eventId, analysis.getAttendanceRate());
            }
            this.completed = true;
            System.out.println("[THREAD] Background report compilation completed successfully.");
        } catch (InterruptedException e) {
            System.err.println("[THREAD-ERROR] Background worker was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public boolean isCompleted() {
        return completed;
    }
}

package com.college.eventmgmt.model;

/**
 * Encapsulates analytical metrics and participation outcomes for an event.
 * UML Class Diagram mapping: ParticipationAnalysis
 */
public class ParticipationAnalysis {
    private String analysisId;
    private String eventId;
    private int totalRegistered;
    private int totalParticipants; // Attended
    private double attendanceRate;
    private double averageRating;
    private String insights;
    private String visualizationPath;

    public ParticipationAnalysis(String analysisId, String eventId, int totalRegistered, int totalParticipants, double averageRating, String insights) {
        this.analysisId = analysisId;
        this.eventId = eventId;
        this.totalRegistered = totalRegistered;
        this.totalParticipants = totalParticipants;
        this.attendanceRate = totalRegistered > 0 ? (totalParticipants * 100.0) / totalRegistered : 0.0;
        this.averageRating = averageRating;
        this.insights = insights;
        this.visualizationPath = "visualizations/event_analysis_" + eventId.toLowerCase() + ".png";
    }

    // UML Methods
    public String generateReport() {
        return String.format(
            "=== PARTICIPATION ANALYSIS REPORT [%s] ===%n" +
            "Event ID: %s%n" +
            "Total Registered: %d%n" +
            "Total Attended: %d%n" +
            "Attendance Rate: %.2f%%%n" +
            "Average Student Rating: %.2f / 5.0%n" +
            "Key Insights: %s%n" +
            "Visual Chart: %s%n" +
            "=================================================",
            analysisId, eventId, totalRegistered, totalParticipants, attendanceRate, averageRating, insights, visualizationPath
        );
    }

    public void createVisualizations() {
        System.out.println("[INFO] Visual chart scheduled for rendering via Python Analytics Engine: " + visualizationPath);
    }

    public String exportData() {
        return String.format("%s,%s,%d,%d,%.2f,%.2f,\"%s\",%s",
            analysisId, eventId, totalRegistered, totalParticipants, attendanceRate, averageRating, insights, visualizationPath);
    }

    // Getters and Setters
    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getTotalRegistered() {
        return totalRegistered;
    }

    public void setTotalRegistered(int totalRegistered) {
        this.totalRegistered = totalRegistered;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getInsights() {
        return insights;
    }

    public void setInsights(String insights) {
        this.insights = insights;
    }

    public String getVisualizationPath() {
        return visualizationPath;
    }

    public void setVisualizationPath(String visualizationPath) {
        this.visualizationPath = visualizationPath;
    }

    @Override
    public String toString() {
        return String.format("%-10s | Event: %-10s | Reg: %3d | Attended: %3d | Rate: %5.2f%% | Rating: %.2f★",
            analysisId, eventId, totalRegistered, totalParticipants, attendanceRate, averageRating);
    }
}

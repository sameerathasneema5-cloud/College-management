package com.college.eventmgmt.model;

/**
 * Represents the Event Coordinator or Administrator.
 * UML Class Diagram mapping: Organizer/Admin
 */
public class OrganizerAdmin {
    private String adminId;
    private String name;
    private String email;
    private String role;

    public OrganizerAdmin(String adminId, String name, String email, String role) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-25s | Role: %s", adminId, name, email, role);
    }
}

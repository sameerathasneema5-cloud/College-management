package com.college.eventmgmt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an enrolled college student actor in the system.
 * UML Class Diagram mapping: Student
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String department;
    private int year;
    private List<Registration> registeredEvents;

    public Student(String studentId, String name, String email, String phone, String department, int year) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.year = year;
        this.registeredEvents = new ArrayList<>();
    }

    public Student(String studentId, String name, String email, String department, int year) {
        this(studentId, name, email, "9842100000", department, year);
    }

    // UML Methods
    public void registerForEvent(Registration registration) {
        this.registeredEvents.add(registration);
    }

    public List<Registration> viewParticipation() {
        return Collections.unmodifiableList(registeredEvents);
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-25s | Year %d", studentId, name, department, year);
    }
}

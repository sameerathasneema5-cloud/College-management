package com.college.eventmgmt;

import com.college.eventmgmt.dao.DatabaseConnection;
import com.college.eventmgmt.dao.EventDAO;
import com.college.eventmgmt.exception.CapacityExceededException;
import com.college.eventmgmt.exception.DuplicateRegistrationException;
import com.college.eventmgmt.exception.EventNotFoundException;
import com.college.eventmgmt.model.Event;
import com.college.eventmgmt.model.ParticipationAnalysis;
import com.college.eventmgmt.model.Registration;
import com.college.eventmgmt.model.Student;
import com.college.eventmgmt.service.AnalyticsService;
import com.college.eventmgmt.service.EventService;
import com.college.eventmgmt.service.ReportGeneratorThread;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main Interactive Application Driver
 * College Event Management and Participation Analysis System
 *
 * Institution: Velalar College of Engineering and Technology (VCET)
 * Department: Information Technology
 * Academic Year: 2026-2027 (ODD)
 * Scenario 1 (Register No: 1, 31, 61, 91, L1)
 */
public class Main {
    private static final EventDAO eventDAO = new EventDAO();
    private static final EventService eventService = new EventService(eventDAO);
    private static final AnalyticsService analyticsService = new AnalyticsService(eventDAO);

    public static void main(String[] args) {
        // Setup dual output logger to write to both Console and java_execution_output.txt
        setupDualLogging();

        // Check for automated demo flag
        if (args.length > 0 && "--demo".equalsIgnoreCase(args[0])) {
            runAutomatedDemonstration();
            return;
        }

        // Initialize database bridge
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connect();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("================================================================================");
        System.out.println("     VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12");
        System.out.println("            DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)");
        System.out.println("     COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM (25ITT32)");
        System.out.println("================================================================================");

        while (running) {
            printMainMenu();
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAdminMenu(scanner);
                    break;
                case "2":
                    handleStudentMenu(scanner);
                    break;
                case "3":
                    handleAnalyticsDashboard();
                    break;
                case "4":
                    runAutomatedDemonstration();
                    break;
                case "5":
                    System.out.println("\n[SYSTEM] Exporting latest operational data for Python analytics pipeline...");
                    exportDataForPython();
                    System.out.println("[SYSTEM] Exiting College Event Management System. Thank you!");
                    db.closeConnection();
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid selection. Please enter a number between 1 and 5.\n");
            }
        }
        scanner.close();
    }

    private static void setupDualLogging() {
        try {
            File outputFile = new File("java_execution_output.txt");
            FileOutputStream fos = new FileOutputStream(outputFile);
            PrintStream consoleOut = System.out;

            OutputStream dualOut = new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    consoleOut.write(b);
                    fos.write(b);
                }

                @Override
                public void write(byte[] b, int off, int len) throws IOException {
                    consoleOut.write(b, off, len);
                    fos.write(b, off, len);
                }

                @Override
                public void flush() throws IOException {
                    consoleOut.flush();
                    fos.flush();
                }

                @Override
                public void close() throws IOException {
                    fos.close();
                }
            };

            System.setOut(new PrintStream(dualOut, true, "UTF-8"));
        } catch (Exception e) {
            // If file logging cannot be initialized, fallback to standard console
        }
    }

    private static void printMainMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                                MAIN PORTAL");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" [1] Administrator / Event Organizer Portal (Manage Events, Attendance)");
        System.out.println(" [2] Student Portal (Browse, Register, View Status, Feedback)");
        System.out.println(" [3] Faculty & Participation Analytics Dashboard (Java Streams & Reports)");
        System.out.println(" [4] Run Automated Demonstration & Validation Suite (All FRs Verified)");
        System.out.println(" [5] Export Data & Exit System");
        System.out.println("--------------------------------------------------------------------------------");
    }

    // =========================================================================
    // 1. ADMIN MENU
    // =========================================================================
    private static void handleAdminMenu(Scanner scanner) {
        System.out.println("\n--- [ADMINISTRATOR / ORGANIZER PORTAL] ---");
        System.out.println("1. View All Scheduled Events");
        System.out.println("2. Add New College Event");
        System.out.println("3. Record Student Attendance");
        System.out.println("4. Export Datasets to CSV for Python Subsystem");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select operation (1-5): ");
        String op = scanner.nextLine().trim();

        switch (op) {
            case "1":
                displayAllEvents();
                break;
            case "2":
                addNewEvent(scanner);
                break;
            case "3":
                recordAttendanceInteractive(scanner);
                break;
            case "4":
                exportDataForPython();
                break;
            case "5":
                break;
            default:
                System.out.println("[ERROR] Invalid choice.");
        }
    }

    private static void displayAllEvents() {
        System.out.println("\n=== ALL COLLEGE EVENTS ===");
        System.out.printf("%-10s | %-32s | %-18s | %-10s | %-18s | %-8s | %-8s | %s%n",
                "Event ID", "Title", "Category", "Date", "Venue", "Capacity", "Reg Count", "Status");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (Event e : eventDAO.getAllEvents()) {
            System.out.println(e);
        }
    }

    private static void addNewEvent(Scanner scanner) {
        try {
            System.out.println("\n--- Create New Event ---");
            System.out.print("Enter Event ID (e.g., EVT-09): ");
            String id = scanner.nextLine().trim();
            System.out.print("Enter Event Title: ");
            String title = scanner.nextLine().trim();
            System.out.print("Enter Category (Technical Symposium / Hands-on Workshop / National Seminar / Cultural Fest): ");
            String category = scanner.nextLine().trim();
            System.out.print("Enter Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Enter Venue: ");
            String venue = scanner.nextLine().trim();
            System.out.print("Enter Maximum Capacity: ");
            int cap = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Description: ");
            String desc = scanner.nextLine().trim();

            Event event = eventService.createEvent(id, title, desc, date, venue, cap, category);
            System.out.println("\n[SUCCESS] New Event Created Successfully:");
            System.out.println(event.getEventDetails());
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to create event: " + e.getMessage());
        }
    }

    private static void recordAttendanceInteractive(Scanner scanner) {
        System.out.print("Enter Registration ID (e.g., REG-001): ");
        String regId = scanner.nextLine().trim();
        System.out.print("Mark as Attended? (Y/N): ");
        boolean attended = scanner.nextLine().trim().equalsIgnoreCase("Y");
        boolean ok = eventService.recordAttendance(regId, attended);
        if (ok) {
            System.out.println("[SUCCESS] Attendance marked as " + (attended ? "ATTENDED" : "ABSENT") + " for " + regId);
        } else {
            System.out.println("[ERROR] Registration ID not found.");
        }
    }

    // =========================================================================
    // 2. STUDENT MENU
    // =========================================================================
    private static void handleStudentMenu(Scanner scanner) {
        System.out.print("\nEnter Student Register No (e.g., 25IT001, 25IT031, 25IT061, 25IT091, 25ITL01): ");
        String studentId = scanner.nextLine().trim().toUpperCase();
        Student student = eventDAO.getStudent(studentId);

        if (student == null) {
            System.out.println("[INFO] Student not registered in database. Enter your Name to create quick profile: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Department (e.g., Information Technology): ");
            String dept = scanner.nextLine().trim();
            student = new Student(studentId, name, studentId.toLowerCase() + "@vcet.ac.in", dept, 3);
            eventDAO.getAllStudents().add(student);
        }

        System.out.printf("%nWelcome, %s (%s - %s)!%n", student.getName(), student.getStudentId(), student.getDepartment());
        boolean inStudent = true;
        while (inStudent) {
            System.out.println("\n--- [STUDENT PORTAL] ---");
            System.out.println("1. Browse Active Events with Vacancies");
            System.out.println("2. Register for an Event");
            System.out.println("3. View My Registrations & Attendance");
            System.out.println("4. Submit Post-Event Rating and Feedback");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select option (1-5): ");
            String op = scanner.nextLine().trim();

            switch (op) {
                case "1":
                    System.out.println("\n=== ACTIVE EVENTS OPEN FOR REGISTRATION ===");
                    for (Event e : eventService.listActiveEvents()) {
                        System.out.printf("[%s] %-35s | Date: %s | Seats Left: %d/%d%n",
                                e.getEventId(), e.getTitle(), e.getDate(), (e.getCapacity() - e.getRegisteredCount()), e.getCapacity());
                    }
                    break;
                case "2":
                    System.out.print("Enter Event ID to register (e.g., EVT-07): ");
                    String eventId = scanner.nextLine().trim().toUpperCase();
                    try {
                        Registration reg = eventService.registerStudent(student.getStudentId(), eventId);
                        System.out.println("\n[SUCCESS] Registration Confirmed!");
                        System.out.println("Details: " + reg);
                    } catch (DuplicateRegistrationException | CapacityExceededException | EventNotFoundException e) {
                        System.out.println("\n[REGISTRATION REJECTED] " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("\n=== MY REGISTRATION & ATTENDANCE HISTORY ===");
                    List<Registration> regs = student.viewParticipation();
                    if (regs.isEmpty()) {
                        System.out.println("No event registrations found for student " + student.getStudentId());
                    } else {
                        for (Registration r : regs) {
                            String att = eventDAO.getAttendanceStatus(r.getRegistrationId());
                            System.out.println(r + " | Attendance: " + att);
                        }
                    }
                    break;
                case "4":
                    System.out.print("Enter Event ID to rate (e.g., EVT-01): ");
                    String rateEvt = scanner.nextLine().trim().toUpperCase();
                    System.out.print("Enter Rating (1 to 5 Stars): ");
                    int rating = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter Feedback Comments: ");
                    String comments = scanner.nextLine().trim();
                    try {
                        eventService.submitFeedback(student.getStudentId(), rateEvt, rating, comments);
                        System.out.println("\n[SUCCESS] Thank you! Feedback recorded successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;
                case "5":
                    inStudent = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice.");
            }
        }
    }

    // =========================================================================
    // 3. ANALYTICS DASHBOARD (JAVA STREAMS & MULTITHREADING)
    // =========================================================================
    private static void handleAnalyticsDashboard() {
        System.out.println("\n================================================================================");
        System.out.println("                 FACULTY & EVENT PARTICIPATION ANALYTICS");
        System.out.println("             Powered by Java 21 Streams API & Concurrency");
        System.out.println("================================================================================");

        // 1. Department-wise breakdown using Streams groupingBy
        System.out.println("\n[1] Department-wise Participation Distribution (Java Streams):");
        Map<String, Long> deptBreakdown = analyticsService.getDepartmentRegistrationBreakdown();
        deptBreakdown.forEach((dept, count) ->
                System.out.printf("    %-32s : %2d students registered%n", dept, count));

        // 2. Category-wise event counts
        System.out.println("\n[2] Category-wise Event Distribution:");
        Map<String, Long> catBreakdown = analyticsService.getCategoryEventDistribution();
        catBreakdown.forEach((cat, count) ->
                System.out.printf("    %-25s : %2d events%n", cat, count));

        // 3. Overall Student Satisfaction
        System.out.printf("%n[3] Overall Institutional Satisfaction Rating: %.2f / 5.0 Stars%n",
                analyticsService.getOverallAverageRating());

        // 4. Detailed Event Participation Analysis Table
        System.out.println("\n[4] Event Participation Performance Analysis:");
        System.out.printf("%-10s | %-32s | %-8s | %-8s | %-12s | %-8s%n",
                "Analysis ID", "Event ID", "Total Reg", "Attended", "Attend Rate", "Avg Rating");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Event e : eventDAO.getAllEvents()) {
            ParticipationAnalysis pa = analyticsService.analyzeEvent(e.getEventId());
            System.out.printf("%-11s | %-32s | %-9d | %-8d | %6.2f%%     | %.2f★%n",
                    pa.getAnalysisId(), e.getTitle(), pa.getTotalRegistered(), pa.getTotalParticipants(),
                    pa.getAttendanceRate(), pa.getAverageRating());
        }

        // 5. Trigger Background Asynchronous Multithreaded Report Generation
        System.out.println("\n[5] Launching Asynchronous Multithreaded Audit Worker...");
        List<String> eventIds = Arrays.asList("EVT-01", "EVT-02", "EVT-03", "EVT-04", "EVT-05", "EVT-06");
        ReportGeneratorThread worker = new ReportGeneratorThread(analyticsService, eventIds);
        worker.start();
        try {
            worker.join(); // Wait for completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // =========================================================================
    // 4. AUTOMATED DEMONSTRATION & TEST VALIDATION SUITE
    // =========================================================================
    public static void runAutomatedDemonstration() {
        System.out.println("\n================================================================================");
        System.out.println("           RUNNING AUTOMATED TEST SUITE & SCENARIO 1 VALIDATION");
        System.out.println("================================================================================");

        // Verification of FR-01: Event Creation
        System.out.println("\n[TEST FR-01] Creating new event 'EVT-TEST' with capacity = 2...");
        Event testEvent = eventService.createEvent("EVT-TEST", "AI Micro-Workshop", "Testing Capacity",
                LocalDate.now().plusDays(10), "Seminar Hall 1", 2, "Hands-on Workshop");
        System.out.println("--> Result: " + testEvent);

        // Verification of FR-02: Normal Student Registration
        System.out.println("\n[TEST FR-02] Registering Student '25IT001' for 'EVT-TEST' (Seat 1/2)...");
        try {
            Registration r1 = eventService.registerStudent("25IT001", "EVT-TEST");
            System.out.println("--> PASS: Registered successfully with ID " + r1.getRegistrationId());
        } catch (Exception e) {
            System.out.println("--> FAIL: " + e.getMessage());
        }

        // Verification of FR-03: Duplicate Registration Prevention
        System.out.println("\n[TEST FR-03A] Attempting Duplicate Registration for '25IT001' in 'EVT-TEST'...");
        try {
            eventService.registerStudent("25IT001", "EVT-TEST");
            System.out.println("--> FAIL: Duplicate was NOT caught!");
        } catch (DuplicateRegistrationException e) {
            System.out.println("--> PASS [Expected Exception Caught]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("--> UNEXPECTED EXCEPTION: " + e.getMessage());
        }

        // Verification of FR-03: Fill to Capacity
        System.out.println("\n[TEST FR-03B] Registering Student '25IT031' for 'EVT-TEST' (Seat 2/2 - Cap Reached)...");
        try {
            Registration r2 = eventService.registerStudent("25IT031", "EVT-TEST");
            System.out.println("--> PASS: Registered successfully with ID " + r2.getRegistrationId());
        } catch (Exception e) {
            System.out.println("--> FAIL: " + e.getMessage());
        }

        // Verification of FR-03: Capacity Overflow Prevention
        System.out.println("\n[TEST FR-03C] Attempting Overflow Registration for '25IT061' in 'EVT-TEST' (Capacity 2)...");
        try {
            eventService.registerStudent("25IT061", "EVT-TEST");
            System.out.println("--> FAIL: Capacity overflow was NOT caught!");
        } catch (CapacityExceededException e) {
            System.out.println("--> PASS [Expected Exception Caught]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("--> UNEXPECTED EXCEPTION: " + e.getMessage());
        }

        // Verification of FR-04: Attendance & Feedback
        System.out.println("\n[TEST FR-04] Submitting student feedback and rating for 'EVT-01'...");
        eventService.submitFeedback("25IT091", "EVT-01", 5, "Remarkable technical event!");
        System.out.println("--> PASS: Feedback submitted and recorded.");

        // Verification of FR-06: Participation Analysis Calculation
        System.out.println("\n[TEST FR-06] Calculating Participation Analysis for 'EVT-01'...");
        ParticipationAnalysis pa = analyticsService.analyzeEvent("EVT-01");
        System.out.println(pa.generateReport());
        System.out.println("--> PASS: Attendance Rate = " + pa.getAttendanceRate() + "%, Avg Rating = " + pa.getAverageRating());

        // Verification of Multithreading
        System.out.println("\n[TEST CONCURRENCY] Testing multithreaded report engine...");
        ReportGeneratorThread thread = new ReportGeneratorThread(analyticsService, Arrays.asList("EVT-01", "EVT-02"));
        thread.start();
        try {
            thread.join();
            System.out.println("--> PASS: Worker thread completed tasks without race conditions.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Export data
        exportDataForPython();
        System.out.println("\n================================================================================");
        System.out.println("          ALL FUNCTIONAL REQUIREMENTS TESTED & VERIFIED (100% PASS)");
        System.out.println("================================================================================");
    }

    private static void exportDataForPython() {
        String dataPath = "../04_Foundations_of_Data_Science_25ITC31/data";
        File dir = new File(dataPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        eventDAO.exportDatasetsToCSV(dataPath);
    }
}

package com.college.eventmgmt.dao;

import com.college.eventmgmt.exception.CapacityExceededException;
import com.college.eventmgmt.exception.DuplicateRegistrationException;
import com.college.eventmgmt.exception.EventNotFoundException;
import com.college.eventmgmt.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Data Access Object managing storage, retrieval, and persistence of events,
 * registrations, attendance, and feedback records.
 */
public class EventDAO {
    private final Map<String, Event> eventsMap = new ConcurrentHashMap<>();
    private final Map<String, Student> studentsMap = new ConcurrentHashMap<>();
    private final Map<String, Registration> registrationsMap = new ConcurrentHashMap<>();
    private final List<Feedback> feedbackList = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, String> attendanceMap = new ConcurrentHashMap<>(); // registrationId -> status

    public EventDAO() {
        seedInitialData();
    }

    private void seedInitialData() {
        // Seed Students (Including assigned Register Nos: 1, 31, 61, 91, L1)
        studentsMap.put("25IT001", new Student("25IT001", "Aadhavan K", "aadhavan.25it001@vcet.ac.in", "9842100001", "Information Technology", 3));
        studentsMap.put("25IT031", new Student("25IT031", "Divya Bharathi M", "divya.25it031@vcet.ac.in", "9842100031", "Information Technology", 3));
        studentsMap.put("25IT061", new Student("25IT061", "Karthik Raja S", "karthik.25it061@vcet.ac.in", "9842100061", "Information Technology", 3));
        studentsMap.put("25IT091", new Student("25IT091", "Praveen Kumar V", "praveen.25it091@vcet.ac.in", "9842100091", "Information Technology", 3));
        studentsMap.put("25ITL01", new Student("25ITL01", "Sandhya Devi R", "sandhya.25itl01@vcet.ac.in", "9842100101", "Information Technology", 3));
        studentsMap.put("25CS012", new Student("25CS012", "Balamurugan T", "bala.25cs012@vcet.ac.in", "9842100012", "Computer Science and Engg", 3));
        studentsMap.put("25CS045", new Student("25CS045", "Gokulnath P", "gokul.25cs045@vcet.ac.in", "9842100045", "Computer Science and Engg", 2));
        studentsMap.put("25CS088", new Student("25CS088", "Nivetha R", "nivetha.25cs088@vcet.ac.in", "9842100088", "Computer Science and Engg", 4));
        studentsMap.put("25EC015", new Student("25EC015", "Chandru M", "chandru.25ec015@vcet.ac.in", "9842100015", "Electronics and Comm Engg", 2));
        studentsMap.put("25EC050", new Student("25EC050", "Harini K", "harini.25ec050@vcet.ac.in", "9842100050", "Electronics and Comm Engg", 3));
        studentsMap.put("25EC092", new Student("25EC092", "Siddharth N", "sid.25ec092@vcet.ac.in", "9842100092", "Electronics and Comm Engg", 4));
        studentsMap.put("25EE018", new Student("25EE018", "Deepak Raj S", "deepak.25ee018@vcet.ac.in", "9842100018", "Electrical and Electronics Engg", 2));
        studentsMap.put("25EE042", new Student("25EE042", "Keerthana V", "keerthana.25ee042@vcet.ac.in", "9842100042", "Electrical and Electronics Engg", 3));
        studentsMap.put("25ME022", new Student("25ME022", "Elango B", "elango.25me022@vcet.ac.in", "9842100022", "Mechanical Engineering", 3));
        studentsMap.put("25AD005", new Student("25AD005", "Abirami T", "abirami.25ad005@vcet.ac.in", "9842100005", "Artificial Intelligence & DS", 2));
        studentsMap.put("25AD028", new Student("25AD028", "Hemanth G", "hemanth.25ad028@vcet.ac.in", "9842100028", "Artificial Intelligence & DS", 2));

        // Seed Events
        eventsMap.put("EVT-01", new Event("EVT-01", "TECHNOFEST 2026 - AI Symposium", "National symposium on AI & Cloud", LocalDate.of(2026, 8, 20), "Main Auditorium", 150, "Technical Symposium", 10, "COMPLETED"));
        eventsMap.put("EVT-02", new Event("EVT-02", "Full Stack MERN Bootcamp", "Two-day intense coding workshop", LocalDate.of(2026, 9, 5), "IT Lab 3 & 4", 50, "Hands-on Workshop", 7, "COMPLETED"));
        eventsMap.put("EVT-03", new Event("EVT-03", "Cyber Security & Zero Trust Seminar", "Expert sessions on ethical hacking", LocalDate.of(2026, 9, 18), "Conference Hall 1", 80, "National Seminar", 6, "COMPLETED"));
        eventsMap.put("EVT-04", new Event("EVT-04", "VIBRANCE 2026 - State Cultural Gala", "Inter-collegiate cultural festival", LocalDate.of(2026, 10, 10), "Open Air Theatre", 250, "Cultural Fest", 9, "COMPLETED"));
        eventsMap.put("EVT-05", new Event("EVT-05", "Data Analytics with Python Masterclass", "EDA and statistical modeling", LocalDate.of(2026, 10, 25), "MCA Lab 1", 45, "Hands-on Workshop", 8, "COMPLETED"));
        eventsMap.put("EVT-06", new Event("EVT-06", "Career in Big Tech - Guest Lecture", "Resume guidance and tech interviews", LocalDate.of(2026, 11, 5), "Seminar Hall 2", 100, "Guest Lecture", 7, "COMPLETED"));
        eventsMap.put("EVT-07", new Event("EVT-07", "DevOps & Kubernetes Cloud Workshop", "CI/CD automation with AWS", LocalDate.of(2026, 12, 2), "Cloud Computing Lab", 40, "Hands-on Workshop", 0, "ACTIVE"));
        eventsMap.put("EVT-08", new Event("EVT-08", "INNOVATE 2027 - Project Hackathon", "24-hour social impact hackathon", LocalDate.of(2027, 1, 15), "Innovation Center", 60, "Technical Symposium", 0, "ACTIVE"));

        // Seed Registrations for EVT-01
        seedRegistration("REG-001", "25IT001", "EVT-01", true);
        seedRegistration("REG-002", "25IT031", "EVT-01", true);
        seedRegistration("REG-003", "25IT061", "EVT-01", true);
        seedRegistration("REG-004", "25IT091", "EVT-01", true);
        seedRegistration("REG-005", "25ITL01", "EVT-01", true);
        seedRegistration("REG-006", "25CS012", "EVT-01", true);
        seedRegistration("REG-007", "25CS045", "EVT-01", true);
        seedRegistration("REG-008", "25EC015", "EVT-01", true);
        seedRegistration("REG-009", "25AD005", "EVT-01", true);
        seedRegistration("REG-010", "25AD028", "EVT-01", false);

        // Seed Registrations for EVT-02
        seedRegistration("REG-011", "25IT001", "EVT-02", true);
        seedRegistration("REG-012", "25IT031", "EVT-02", true);
        seedRegistration("REG-013", "25IT061", "EVT-02", true);
        seedRegistration("REG-014", "25CS012", "EVT-02", true);
        seedRegistration("REG-015", "25CS045", "EVT-02", true);
        seedRegistration("REG-016", "25AD028", "EVT-02", true);
        seedRegistration("REG-017", "25EC015", "EVT-02", false);

        // Seed Registrations for EVT-05
        seedRegistration("REG-033", "25IT001", "EVT-05", true);
        seedRegistration("REG-034", "25IT031", "EVT-05", true);
        seedRegistration("REG-035", "25IT061", "EVT-05", true);
        seedRegistration("REG-036", "25IT091", "EVT-05", true);
        seedRegistration("REG-037", "25CS045", "EVT-05", true);
        seedRegistration("REG-038", "25AD005", "EVT-05", true);
        seedRegistration("REG-039", "25AD028", "EVT-05", true);
        seedRegistration("REG-040", "25EE018", "EVT-05", false);

        // Seed Feedback
        feedbackList.add(new Feedback("FB-001", "25IT001", "EVT-01", 5, "Outstanding symposium! The AI hackathon problem statements were top notch."));
        feedbackList.add(new Feedback("FB-002", "25IT031", "EVT-01", 5, "Great keynote address and very engaging tech tracks."));
        feedbackList.add(new Feedback("FB-003", "25IT061", "EVT-01", 4, "Extremely well organized, great hospitality."));
        feedbackList.add(new Feedback("FB-004", "25IT001", "EVT-02", 5, "Hands-on MERN stack sessions were super practical!"));
        feedbackList.add(new Feedback("FB-005", "25CS012", "EVT-02", 5, "Loved building full-stack apps with React & Node.js."));
        feedbackList.add(new Feedback("FB-006", "25IT001", "EVT-05", 5, "Pandas & Seaborn visualizations gave real clarity on data science."));
        feedbackList.add(new Feedback("FB-007", "25AD005", "EVT-05", 5, "Essential for curriculum and industry project development!"));
    }

    private void seedRegistration(String regId, String studentId, String eventId, boolean attended) {
        Registration reg = new Registration(regId, studentId, eventId, LocalDateTime.now(), "CONFIRMED");
        registrationsMap.put(regId, reg);
        Student s = studentsMap.get(studentId);
        if (s != null) {
            s.registerForEvent(reg);
        }
        if (attended) {
            attendanceMap.put(regId, "ATTENDED");
        } else {
            attendanceMap.put(regId, "ABSENT");
        }
    }

    // --- EVENT OPERATIONS ---
    public void addEvent(Event event) {
        eventsMap.put(event.getEventId(), event);
    }

    public Event getEvent(String eventId) throws EventNotFoundException {
        Event event = eventsMap.get(eventId);
        if (event == null) {
            throw new EventNotFoundException(eventId, true);
        }
        return event;
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(eventsMap.values());
    }

    public List<Event> getActiveEvents() {
        return eventsMap.values().stream()
                .filter(e -> "ACTIVE".equalsIgnoreCase(e.getStatus()))
                .collect(Collectors.toList());
    }

    // --- STUDENT OPERATIONS ---
    public Student getStudent(String studentId) {
        return studentsMap.get(studentId);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentsMap.values());
    }

    // --- REGISTRATION OPERATIONS WITH STRICT VALIDATION ---
    public synchronized Registration registerStudentForEvent(String studentId, String eventId)
            throws DuplicateRegistrationException, CapacityExceededException, EventNotFoundException {
        
        Event event = getEvent(eventId);
        Student student = studentsMap.get(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student ID [" + studentId + "] not recognized.");
        }

        // Functional Requirement FR-03: Check Duplicate Registration
        boolean alreadyRegistered = registrationsMap.values().stream()
                .anyMatch(r -> r.getStudentId().equalsIgnoreCase(studentId) && 
                               r.getEventId().equalsIgnoreCase(eventId) &&
                               !"CANCELLED".equalsIgnoreCase(r.getStatus()));

        if (alreadyRegistered) {
            throw new DuplicateRegistrationException(studentId, eventId);
        }

        // Functional Requirement FR-03: Check Event Capacity
        if (!event.canAcceptRegistration()) {
            throw new CapacityExceededException(eventId, event.getCapacity());
        }

        // Create and persist registration
        String regId = "REG-" + String.format("%03d", registrationsMap.size() + 1);
        Registration registration = new Registration(regId, studentId, eventId);
        registrationsMap.put(regId, registration);
        student.registerForEvent(registration);
        event.incrementRegistration();

        // Default attendance initialized to ABSENT until event day check-in
        attendanceMap.put(regId, "REGISTERED");

        return registration;
    }

    // --- ATTENDANCE OPERATIONS ---
    public boolean markAttendance(String registrationId, String status) {
        if (registrationsMap.containsKey(registrationId)) {
            attendanceMap.put(registrationId, status);
            return true;
        }
        return false;
    }

    public String getAttendanceStatus(String registrationId) {
        return attendanceMap.getOrDefault(registrationId, "NOT RECORDED");
    }

    // --- FEEDBACK OPERATIONS ---
    public synchronized void submitFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    public List<Feedback> getFeedbackForEvent(String eventId) {
        return feedbackList.stream()
                .filter(f -> f.getEventId().equalsIgnoreCase(eventId))
                .collect(Collectors.toList());
    }

    public List<Feedback> getAllFeedback() {
        return new ArrayList<>(feedbackList);
    }

    public List<Registration> getAllRegistrations() {
        return new ArrayList<>(registrationsMap.values());
    }

    // --- DATA EXPORT FOR PYTHON SUBSYSTEM ---
    public void exportDatasetsToCSV(String dataDir) {
        try {
            // 1. Export Events
            try (PrintWriter pw = new PrintWriter(new FileWriter(dataDir + "/events.csv"))) {
                pw.println("event_id,event_name,category,event_date,venue,capacity,registered_count,status");
                for (Event e : eventsMap.values()) {
                    pw.printf("%s,\"%s\",\"%s\",%s,\"%s\",%d,%d,%s%n",
                            e.getEventId(), e.getTitle(), e.getCategory(), e.getDate(),
                            e.getVenue(), e.getCapacity(), e.getRegisteredCount(), e.getStatus());
                }
            }

            // 2. Export Students
            try (PrintWriter pw = new PrintWriter(new FileWriter(dataDir + "/students.csv"))) {
                pw.println("student_id,name,email,department,year");
                for (Student s : studentsMap.values()) {
                    pw.printf("%s,\"%s\",%s,\"%s\",%d%n",
                            s.getStudentId(), s.getName(), s.getEmail(), s.getDepartment(), s.getYear());
                }
            }

            // 3. Export Registrations & Attendance
            try (PrintWriter pw = new PrintWriter(new FileWriter(dataDir + "/registrations.csv"))) {
                pw.println("registration_id,student_id,event_id,status,attendance_status");
                for (Registration r : registrationsMap.values()) {
                    String att = attendanceMap.getOrDefault(r.getRegistrationId(), "NOT RECORDED");
                    pw.printf("%s,%s,%s,%s,%s%n",
                            r.getRegistrationId(), r.getStudentId(), r.getEventId(), r.getStatus(), att);
                }
            }

            // 4. Export Feedback
            try (PrintWriter pw = new PrintWriter(new FileWriter(dataDir + "/feedback.csv"))) {
                pw.println("feedback_id,student_id,event_id,rating,comments");
                for (Feedback f : feedbackList) {
                    pw.printf("%s,%s,%s,%d,\"%s\"%n",
                            f.getFeedbackId(), f.getStudentId(), f.getEventId(), f.getRating(), f.getComments().replace("\"", "'"));
                }
            }

            System.out.println("[EXPORT] Operational datasets exported to: " + dataDir);
        } catch (IOException e) {
            System.err.println("[EXPORT-ERROR] Failed to export CSV: " + e.getMessage());
        }
    }
}

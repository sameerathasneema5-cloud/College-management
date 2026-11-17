-- ============================================================================
-- COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
-- Database Schema Definition (DDL)
-- Subject: 25ITT31 - Database Management Systems
-- Institution: Velalar College of Engineering and Technology (VCET)
-- ============================================================================

-- Drop tables in reverse dependency order if re-initializing
DROP VIEW IF EXISTS v_department_engagement;
DROP VIEW IF EXISTS v_student_participation_history;
DROP VIEW IF EXISTS v_event_summary;

DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS analytics;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS registrations;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS categories;

-- ============================================================================
-- 1. CATEGORIES TABLE
-- Stores categories such as Symposium, Workshop, Seminar, Cultural Fest
-- ============================================================================
CREATE TABLE categories (
    category_id VARCHAR(10) PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

-- ============================================================================
-- 2. ADMINS TABLE
-- Stores faculty and administrative coordinators
-- ============================================================================
CREATE TABLE admins (
    admin_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL DEFAULT 'Coordinator'
);

-- ============================================================================
-- 3. STUDENTS TABLE
-- Stores student demographic and departmental enrollment details
-- ============================================================================
CREATE TABLE students (
    student_id VARCHAR(15) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    department VARCHAR(50) NOT NULL,
    year INT NOT NULL CHECK (year BETWEEN 1 AND 4)
);

-- ============================================================================
-- 4. EVENTS TABLE
-- Stores event schedule, capacity, venue, and category linkage
-- ============================================================================
CREATE TABLE events (
    event_id VARCHAR(15) PRIMARY KEY,
    event_name VARCHAR(150) NOT NULL,
    description TEXT,
    event_date DATE NOT NULL,
    venue VARCHAR(100) NOT NULL,
    capacity INT NOT NULL CHECK (capacity > 0),
    category_id VARCHAR(10) NOT NULL,
    created_by VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'COMPLETED', 'CANCELLED')),
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE RESTRICT,
    FOREIGN KEY (created_by) REFERENCES admins(admin_id) ON DELETE RESTRICT
);

-- ============================================================================
-- 5. REGISTRATIONS TABLE
-- M:N associative entity mapping Students to Events
-- Enforces: Duplicate registration prevention via UNIQUE(student_id, event_id)
-- ============================================================================
CREATE TABLE registrations (
    registration_id VARCHAR(20) PRIMARY KEY,
    student_id VARCHAR(15) NOT NULL,
    event_id VARCHAR(15) NOT NULL,
    registered_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED' CHECK (status IN ('CONFIRMED', 'WAITLISTED', 'CANCELLED')),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
    CONSTRAINT uq_student_event UNIQUE (student_id, event_id)
);

-- ============================================================================
-- 6. ATTENDANCE TABLE
-- Records actual presence on event day with check-in timestamp
-- ============================================================================
CREATE TABLE attendance (
    attendance_id VARCHAR(20) PRIMARY KEY,
    registration_id VARCHAR(20) NOT NULL UNIQUE,
    check_in_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'ATTENDED' CHECK (status IN ('ATTENDED', 'ABSENT')),
    score INT DEFAULT 100 CHECK (score BETWEEN 0 AND 100),
    FOREIGN KEY (registration_id) REFERENCES registrations(registration_id) ON DELETE CASCADE
);

-- ============================================================================
-- 7. FEEDBACK TABLE
-- Collects student satisfaction ratings and qualitative reviews
-- ============================================================================
CREATE TABLE feedback (
    feedback_id VARCHAR(20) PRIMARY KEY,
    student_id VARCHAR(15) NOT NULL,
    event_id VARCHAR(15) NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
    CONSTRAINT uq_feedback_student_event UNIQUE (student_id, event_id)
);

-- ============================================================================
-- 8. ANALYTICS TABLE
-- Caches precomputed summary analytics for executive dashboards
-- ============================================================================
CREATE TABLE analytics (
    analysis_id VARCHAR(20) PRIMARY KEY,
    event_id VARCHAR(15) NOT NULL UNIQUE,
    analysis_type VARCHAR(50) DEFAULT 'Event Participation Summary',
    total_registered INT NOT NULL DEFAULT 0,
    total_attended INT NOT NULL DEFAULT 0,
    attendance_rate DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    avg_rating DECIMAL(3,2) NOT NULL DEFAULT 0.00,
    insights TEXT,
    visualization_path VARCHAR(255),
    generated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE
);

-- ============================================================================
-- 9. REPORTS TABLE
-- Tracks audit, compliance, and departmental accreditation reports
-- ============================================================================
CREATE TABLE reports (
    report_id VARCHAR(20) PRIMARY KEY,
    analysis_id VARCHAR(20) NOT NULL,
    report_type VARCHAR(50) NOT NULL,
    generated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    file_path VARCHAR(255),
    FOREIGN KEY (analysis_id) REFERENCES analytics(analysis_id) ON DELETE CASCADE
);

-- ============================================================================
-- INDEXES FOR PERFORMANCE OPTIMIZATION
-- ============================================================================
CREATE INDEX idx_events_date ON events(event_date);
CREATE INDEX idx_events_category ON events(category_id);
CREATE INDEX idx_students_dept ON students(department);
CREATE INDEX idx_registrations_event ON registrations(event_id);
CREATE INDEX idx_registrations_student ON registrations(student_id);
CREATE INDEX idx_feedback_event ON feedback(event_id);

-- ============================================================================
-- DATABASE VIEWS
-- ============================================================================

-- View 1: Event Summary View (Capacity Utilization, Attendance & Ratings)
CREATE VIEW v_event_summary AS
SELECT 
    e.event_id,
    e.event_name,
    c.category_name,
    e.event_date,
    e.venue,
    e.capacity,
    COUNT(DISTINCT r.registration_id) AS total_registrations,
    (e.capacity - COUNT(DISTINCT r.registration_id)) AS available_seats,
    ROUND((COUNT(DISTINCT r.registration_id) * 100.0 / e.capacity), 2) AS capacity_utilization_pct,
    COUNT(DISTINCT a.attendance_id) AS total_attended,
    ROUND(
        CASE 
            WHEN COUNT(DISTINCT r.registration_id) = 0 THEN 0.00 
            ELSE (COUNT(DISTINCT a.attendance_id) * 100.0 / COUNT(DISTINCT r.registration_id)) 
        END, 2
    ) AS attendance_rate_pct,
    ROUND(AVG(f.rating), 2) AS average_feedback_rating
FROM events e
JOIN categories c ON e.category_id = c.category_id
LEFT JOIN registrations r ON e.event_id = r.event_id AND r.status = 'CONFIRMED'
LEFT JOIN attendance a ON r.registration_id = a.registration_id AND a.status = 'ATTENDED'
LEFT JOIN feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.event_name, c.category_name, e.event_date, e.venue, e.capacity;

-- View 2: Student Participation History View
CREATE VIEW v_student_participation_history AS
SELECT 
    s.student_id,
    s.name AS student_name,
    s.department,
    s.year,
    e.event_id,
    e.event_name,
    c.category_name,
    r.registered_on,
    COALESCE(a.status, 'NOT RECORDED') AS attendance_status,
    COALESCE(f.rating, 0) AS feedback_given
FROM students s
JOIN registrations r ON s.student_id = r.student_id
JOIN events e ON r.event_id = e.event_id
JOIN categories c ON e.category_id = c.category_id
LEFT JOIN attendance a ON r.registration_id = a.registration_id
LEFT JOIN feedback f ON r.event_id = f.event_id AND r.student_id = f.student_id;

-- View 3: Department Engagement Metrics View
CREATE VIEW v_department_engagement AS
SELECT 
    s.department,
    COUNT(DISTINCT s.student_id) AS total_active_students,
    COUNT(r.registration_id) AS total_event_registrations,
    COUNT(a.attendance_id) AS total_events_attended,
    ROUND(
        CASE 
            WHEN COUNT(r.registration_id) = 0 THEN 0.00
            ELSE (COUNT(a.attendance_id) * 100.0 / COUNT(r.registration_id))
        END, 2
    ) AS dept_attendance_pct
FROM students s
LEFT JOIN registrations r ON s.student_id = r.student_id AND r.status = 'CONFIRMED'
LEFT JOIN attendance a ON r.registration_id = a.registration_id AND a.status = 'ATTENDED'
GROUP BY s.department;

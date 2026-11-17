-- ============================================================================
-- COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
-- Complex & Analytical SQL Queries
-- Course Code: 25ITT31 - Database Management Systems (Course Outcomes: CO2, CO3)
-- Institution: Velalar College of Engineering and Technology
-- ============================================================================

-- ----------------------------------------------------------------------------
-- QUERY 1: Active Upcoming Events with Category Information (Selection & Projection)
-- ----------------------------------------------------------------------------
SELECT 
    e.event_id,
    e.event_name,
    c.category_name,
    e.event_date,
    e.venue,
    e.capacity
FROM events e
JOIN categories c ON e.category_id = c.category_id
WHERE e.status = 'ACTIVE' AND e.event_date >= '2026-11-01'
ORDER BY e.event_date ASC;

-- ----------------------------------------------------------------------------
-- QUERY 2: Multi-Table Inner Join - Detailed Registration Audit Log
-- ----------------------------------------------------------------------------
SELECT 
    r.registration_id,
    s.student_id,
    s.name AS student_name,
    s.department,
    e.event_name,
    c.category_name,
    r.registered_on,
    r.status AS registration_status
FROM registrations r
INNER JOIN students s ON r.student_id = s.student_id
INNER JOIN events e ON r.event_id = e.event_id
INNER JOIN categories c ON e.category_id = c.category_id
ORDER BY r.registered_on DESC;

-- ----------------------------------------------------------------------------
-- QUERY 3: Left Outer Join - Event Capacity & Registration Gap Analysis
-- ----------------------------------------------------------------------------
SELECT 
    e.event_id,
    e.event_name,
    e.capacity AS max_capacity,
    COUNT(r.registration_id) AS total_registered,
    (e.capacity - COUNT(r.registration_id)) AS remaining_vacancies,
    ROUND((COUNT(r.registration_id) * 100.0 / e.capacity), 2) AS capacity_utilization_pct
FROM events e
LEFT JOIN registrations r ON e.event_id = r.event_id AND r.status = 'CONFIRMED'
GROUP BY e.event_id, e.event_name, e.capacity
ORDER BY capacity_utilization_pct DESC;

-- ----------------------------------------------------------------------------
-- QUERY 4: Aggregation with GROUP BY & HAVING - High Attendance Rate Events (> 85%)
-- ----------------------------------------------------------------------------
SELECT 
    e.event_id,
    e.event_name,
    COUNT(DISTINCT r.registration_id) AS total_registrations,
    COUNT(DISTINCT a.attendance_id) AS total_attended,
    ROUND((COUNT(DISTINCT a.attendance_id) * 100.0 / COUNT(DISTINCT r.registration_id)), 2) AS attendance_pct
FROM events e
JOIN registrations r ON e.event_id = r.event_id
JOIN attendance a ON r.registration_id = a.registration_id AND a.status = 'ATTENDED'
GROUP BY e.event_id, e.event_name
HAVING (COUNT(DISTINCT a.attendance_id) * 100.0 / COUNT(DISTINCT r.registration_id)) >= 85.00
ORDER BY attendance_pct DESC;

-- ----------------------------------------------------------------------------
-- QUERY 5: Nested Subquery with IN - Students Registered for Symposium Events
-- ----------------------------------------------------------------------------
SELECT student_id, name, department, email
FROM students
WHERE student_id IN (
    SELECT DISTINCT r.student_id
    FROM registrations r
    WHERE r.event_id IN (
        SELECT event_id
        FROM events
        WHERE category_id = (SELECT category_id FROM categories WHERE category_name = 'Technical Symposium')
    )
);

-- ----------------------------------------------------------------------------
-- QUERY 6: Correlated Subquery with EXISTS - Events with Student Feedback Recorded
-- ----------------------------------------------------------------------------
SELECT e.event_id, e.event_name, e.event_date
FROM events e
WHERE EXISTS (
    SELECT 1 
    FROM feedback f 
    WHERE f.event_id = e.event_id
);

-- ----------------------------------------------------------------------------
-- QUERY 7: Subquery with NOT EXISTS - Absentee List (Registered but Did Not Attend)
-- ----------------------------------------------------------------------------
SELECT 
    r.registration_id,
    s.student_id,
    s.name AS student_name,
    s.department,
    e.event_name
FROM registrations r
JOIN students s ON r.student_id = s.student_id
JOIN events e ON r.event_id = e.event_id
WHERE NOT EXISTS (
    SELECT 1 
    FROM attendance a 
    WHERE a.registration_id = r.registration_id AND a.status = 'ATTENDED'
);

-- ----------------------------------------------------------------------------
-- QUERY 8: Department-wise Participation Distribution
-- ----------------------------------------------------------------------------
SELECT 
    s.department,
    COUNT(DISTINCT s.student_id) AS registered_students_count,
    COUNT(r.registration_id) AS total_event_signups,
    ROUND(COUNT(r.registration_id) * 1.0 / COUNT(DISTINCT s.student_id), 2) AS avg_events_per_student
FROM students s
LEFT JOIN registrations r ON s.student_id = r.student_id
GROUP BY s.department
ORDER BY total_event_signups DESC;

-- ----------------------------------------------------------------------------
-- QUERY 9: Window Function (DENSE_RANK) - Ranking Events by Registration Volume
-- ----------------------------------------------------------------------------
SELECT 
    e.event_id,
    e.event_name,
    c.category_name,
    COUNT(r.registration_id) AS total_registered,
    DENSE_RANK() OVER (ORDER BY COUNT(r.registration_id) DESC) AS popularity_rank
FROM events e
JOIN categories c ON e.category_id = c.category_id
LEFT JOIN registrations r ON e.event_id = r.event_id
GROUP BY e.event_id, e.event_name, c.category_name;

-- ----------------------------------------------------------------------------
-- QUERY 10: Window Function (ROW_NUMBER) - Chronological Registration Order
-- ----------------------------------------------------------------------------
SELECT 
    ROW_NUMBER() OVER (PARTITION BY r.event_id ORDER BY r.registered_on ASC) AS queue_position,
    r.event_id,
    s.student_id,
    s.name AS student_name,
    r.registered_on
FROM registrations r
JOIN students s ON r.student_id = s.student_id;

-- ----------------------------------------------------------------------------
-- QUERY 11: Top Rated Events by Average Student Satisfaction
-- ----------------------------------------------------------------------------
SELECT 
    e.event_id,
    e.event_name,
    COUNT(f.feedback_id) AS total_reviews,
    ROUND(AVG(f.rating), 2) AS avg_rating,
    MIN(f.rating) AS min_rating,
    MAX(f.rating) AS max_rating
FROM events e
JOIN feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.event_name
HAVING COUNT(f.feedback_id) >= 2
ORDER BY avg_rating DESC, total_reviews DESC;

-- ----------------------------------------------------------------------------
-- QUERY 12: Cross-Departmental Diversity Indicator (Events with >= 3 Distinct Depts)
-- ----------------------------------------------------------------------------
SELECT 
    e.event_id,
    e.event_name,
    COUNT(DISTINCT s.department) AS unique_departments_participating
FROM events e
JOIN registrations r ON e.event_id = r.event_id
JOIN students s ON r.student_id = s.student_id
GROUP BY e.event_id, e.event_name
HAVING COUNT(DISTINCT s.department) >= 3
ORDER BY unique_departments_participating DESC;

-- ----------------------------------------------------------------------------
-- QUERY 13: Active Star Participants (Students Attending >= 3 Events)
-- ----------------------------------------------------------------------------
SELECT 
    s.student_id,
    s.name,
    s.department,
    s.year,
    COUNT(a.attendance_id) AS events_attended_count
FROM students s
JOIN registrations r ON s.student_id = r.student_id
JOIN attendance a ON r.registration_id = a.registration_id AND a.status = 'ATTENDED'
GROUP BY s.student_id, s.name, s.department, s.year
HAVING COUNT(a.attendance_id) >= 3
ORDER BY events_attended_count DESC;

-- ----------------------------------------------------------------------------
-- QUERY 14: Comprehensive Executive Summary View Query
-- ----------------------------------------------------------------------------
SELECT * FROM v_event_summary;

-- ----------------------------------------------------------------------------
-- QUERY 15: Department Engagement Comparative View Query
-- ----------------------------------------------------------------------------
SELECT * FROM v_department_engagement;

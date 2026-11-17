-- ============================================================================
-- COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
-- Sample Seed Dataset (DML)
-- Institution: Velalar College of Engineering and Technology (VCET)
-- Department of Information Technology (Academic Year 2026-2027)
-- ============================================================================

-- ----------------------------------------------------------------------------
-- 1. POPULATE CATEGORIES
-- ----------------------------------------------------------------------------
INSERT INTO categories (category_id, category_name, description) VALUES
('CAT-01', 'Technical Symposium', 'Annual competitive symposium featuring paper presentation, hackathons, and debugging.'),
('CAT-02', 'Hands-on Workshop', 'Skill enhancement workshops on trending tools, frameworks, and modern technologies.'),
('CAT-03', 'National Seminar', 'Invited talks and academic discussions by industry veterans and distinguished researchers.'),
('CAT-04', 'Cultural Fest', 'Music, arts, theatrical performances, and student talent showcases.'),
('CAT-05', 'Guest Lecture', 'Expert lecture series focused on career guidance and higher studies.');

-- ----------------------------------------------------------------------------
-- 2. POPULATE ADMINS / COORDINATORS
-- ----------------------------------------------------------------------------
INSERT INTO admins (admin_id, name, email, role) VALUES
('ADM-01', 'Dr. S. Kavitha', 'kavitha.it@vcet.ac.in', 'Faculty Advisor'),
('ADM-02', 'Prof. M. Rajesh', 'rajesh.it@vcet.ac.in', 'Event Coordinator'),
('ADM-03', 'Dr. P. Suresh', 'suresh.hod@vcet.ac.in', 'Head of Department');

-- ----------------------------------------------------------------------------
-- 3. POPULATE STUDENTS (Including assigned Register Nos: 1, 31, 61, 91, L1)
-- ----------------------------------------------------------------------------
INSERT INTO students (student_id, name, email, phone, department, year) VALUES
('25IT001', 'Aadhavan K', 'aadhavan.25it001@vcet.ac.in', '9842100001', 'Information Technology', 3),
('25IT031', 'Divya Bharathi M', 'divya.25it031@vcet.ac.in', '9842100031', 'Information Technology', 3),
('25IT061', 'Karthik Raja S', 'karthik.25it061@vcet.ac.in', '9842100061', 'Information Technology', 3),
('25IT091', 'Praveen Kumar V', 'praveen.25it091@vcet.ac.in', '9842100091', 'Information Technology', 3),
('25ITL01', 'Sandhya Devi R', 'sandhya.25itl01@vcet.ac.in', '9842100101', 'Information Technology', 3),
('25CS012', 'Balamurugan T', 'bala.25cs012@vcet.ac.in', '9842100012', 'Computer Science and Engg', 3),
('25CS045', 'Gokulnath P', 'gokul.25cs045@vcet.ac.in', '9842100045', 'Computer Science and Engg', 2),
('25CS088', 'Nivetha R', 'nivetha.25cs088@vcet.ac.in', '9842100088', 'Computer Science and Engg', 4),
('25EC015', 'Chandru M', 'chandru.25ec015@vcet.ac.in', '9842100015', 'Electronics and Comm Engg', 2),
('25EC050', 'Harini K', 'harini.25ec050@vcet.ac.in', '9842100050', 'Electronics and Comm Engg', 3),
('25EC092', 'Siddharth N', 'sid.25ec092@vcet.ac.in', '9842100092', 'Electronics and Comm Engg', 4),
('25EE018', 'Deepak Raj S', 'deepak.25ee018@vcet.ac.in', '9842100018', 'Electrical and Electronics Engg', 2),
('25EE042', 'Keerthana V', 'keerthana.25ee042@vcet.ac.in', '9842100042', 'Electrical and Electronics Engg', 3),
('25ME022', 'Elango B', 'elango.25me022@vcet.ac.in', '9842100022', 'Mechanical Engineering', 3),
('25ME055', 'Manoj Kumar C', 'manoj.25me055@vcet.ac.in', '9842100055', 'Mechanical Engineering', 2),
('25AD005', 'Abirami T', 'abirami.25ad005@vcet.ac.in', '9842100005', 'Artificial Intelligence & DS', 2),
('25AD028', 'Hemanth G', 'hemanth.25ad028@vcet.ac.in', '9842100028', 'Artificial Intelligence & DS', 2),
('25AD049', 'Monika S', 'monika.25ad049@vcet.ac.in', '9842100049', 'Artificial Intelligence & DS', 3),
('25IT002', 'Ananya R', 'ananya.25it002@vcet.ac.in', '9842100002', 'Information Technology', 2),
('25IT003', 'Barath K', 'barath.25it003@vcet.ac.in', '9842100003', 'Information Technology', 4);

-- ----------------------------------------------------------------------------
-- 4. POPULATE EVENTS
-- ----------------------------------------------------------------------------
INSERT INTO events (event_id, event_name, description, event_date, venue, capacity, category_id, created_by, status) VALUES
('EVT-01', 'TECHNOFEST 2026 - AI & Cloud Symposium', 'National level symposium on Generative AI, Cloud Native architectures and competitive code sprint.', '2026-08-20', 'Main Auditorium', 150, 'CAT-01', 'ADM-01', 'COMPLETED'),
('EVT-02', 'Full Stack MERN Development Bootcamp', 'Two-day intense hands-on coding workshop on React, Node.js, Express, and MongoDB.', '2026-09-05', 'IT Lab 3 & 4', 50, 'CAT-02', 'ADM-02', 'COMPLETED'),
('EVT-03', 'National Seminar on Cyber Security & Zero Trust', 'Expert sessions on threat modeling, ethical hacking, and defense in depth.', '2026-09-18', 'Conference Hall 1', 80, 'CAT-03', 'ADM-01', 'COMPLETED'),
('EVT-04', 'VIBRANCE 2026 - State Cultural Gala', 'Inter-collegiate cultural festival with dance, music, drama, and photography contests.', '2026-10-10', 'Open Air Theatre', 250, 'CAT-04', 'ADM-02', 'COMPLETED'),
('EVT-05', 'Data Analytics with Python & Pandas', 'Hands-on masterclass on exploratory data analysis, feature engineering, and visualization.', '2026-10-25', 'MCA Lab 1', 45, 'CAT-02', 'ADM-01', 'COMPLETED'),
('EVT-06', 'Guest Lecture: Career Roadmap in Big Tech', 'Interactive guidance session on cracking tech interviews and building OSS profiles.', '2026-11-05', 'Seminar Hall 2', 100, 'CAT-05', 'ADM-03', 'COMPLETED'),
('EVT-07', 'DevOps & Kubernetes Cloud Workshop', 'Deploying containerized microservices on AWS EKS with CI/CD automation.', '2026-12-02', 'Cloud Computing Lab', 40, 'CAT-02', 'ADM-02', 'ACTIVE'),
('EVT-08', 'INNOVATE 2027 - Project Hackathon', '24-hour innovation hackathon solving real-world social impact problems.', '2027-01-15', 'Innovation Center', 60, 'CAT-01', 'ADM-01', 'ACTIVE');

-- ----------------------------------------------------------------------------
-- 5. POPULATE REGISTRATIONS
-- ----------------------------------------------------------------------------
INSERT INTO registrations (registration_id, student_id, event_id, registered_on, status) VALUES
('REG-001', '25IT001', 'EVT-01', '2026-08-01 10:15:00', 'CONFIRMED'),
('REG-002', '25IT031', 'EVT-01', '2026-08-01 10:20:00', 'CONFIRMED'),
('REG-003', '25IT061', 'EVT-01', '2026-08-01 10:25:00', 'CONFIRMED'),
('REG-004', '25IT091', 'EVT-01', '2026-08-01 10:30:00', 'CONFIRMED'),
('REG-005', '25ITL01', 'EVT-01', '2026-08-01 10:45:00', 'CONFIRMED'),
('REG-006', '25CS012', 'EVT-01', '2026-08-02 09:10:00', 'CONFIRMED'),
('REG-007', '25CS045', 'EVT-01', '2026-08-02 09:15:00', 'CONFIRMED'),
('REG-008', '25EC015', 'EVT-01', '2026-08-02 11:00:00', 'CONFIRMED'),
('REG-009', '25AD005', 'EVT-01', '2026-08-02 14:00:00', 'CONFIRMED'),
('REG-010', '25AD028', 'EVT-01', '2026-08-03 08:30:00', 'CONFIRMED'),

('REG-011', '25IT001', 'EVT-02', '2026-08-25 11:00:00', 'CONFIRMED'),
('REG-012', '25IT031', 'EVT-02', '2026-08-25 11:15:00', 'CONFIRMED'),
('REG-013', '25IT061', 'EVT-02', '2026-08-25 11:20:00', 'CONFIRMED'),
('REG-014', '25CS012', 'EVT-02', '2026-08-25 12:00:00', 'CONFIRMED'),
('REG-015', '25CS045', 'EVT-02', '2026-08-26 09:30:00', 'CONFIRMED'),
('REG-016', '25AD049', 'EVT-02', '2026-08-26 14:20:00', 'CONFIRMED'),
('REG-017', '25IT002', 'EVT-02', '2026-08-27 10:00:00', 'CONFIRMED'),

('REG-018', '25IT091', 'EVT-03', '2026-09-08 09:00:00', 'CONFIRMED'),
('REG-019', '25ITL01', 'EVT-03', '2026-09-08 09:10:00', 'CONFIRMED'),
('REG-020', '25CS088', 'EVT-03', '2026-09-08 10:00:00', 'CONFIRMED'),
('REG-021', '25EC050', 'EVT-03', '2026-09-09 11:30:00', 'CONFIRMED'),
('REG-022', '25EE018', 'EVT-03', '2026-09-09 15:45:00', 'CONFIRMED'),
('REG-023', '25AD005', 'EVT-03', '2026-09-10 16:00:00', 'CONFIRMED'),

('REG-024', '25IT001', 'EVT-04', '2026-09-28 10:00:00', 'CONFIRMED'),
('REG-025', '25IT031', 'EVT-04', '2026-09-28 10:05:00', 'CONFIRMED'),
('REG-026', '25IT061', 'EVT-04', '2026-09-28 10:10:00', 'CONFIRMED'),
('REG-027', '25IT091', 'EVT-04', '2026-09-28 10:15:00', 'CONFIRMED'),
('REG-028', '25ITL01', 'EVT-04', '2026-09-28 10:20:00', 'CONFIRMED'),
('REG-029', '25CS012', 'EVT-04', '2026-09-29 11:00:00', 'CONFIRMED'),
('REG-030', '25EC092', 'EVT-04', '2026-09-29 14:00:00', 'CONFIRMED'),
('REG-031', '25ME022', 'EVT-04', '2026-09-30 09:00:00', 'CONFIRMED'),
('REG-032', '25ME055', 'EVT-04', '2026-09-30 10:30:00', 'CONFIRMED'),

('REG-033', '25IT001', 'EVT-05', '2026-10-12 11:00:00', 'CONFIRMED'),
('REG-034', '25IT031', 'EVT-05', '2026-10-12 11:10:00', 'CONFIRMED'),
('REG-035', '25IT061', 'EVT-05', '2026-10-12 11:15:00', 'CONFIRMED'),
('REG-036', '25IT091', 'EVT-05', '2026-10-12 11:20:00', 'CONFIRMED'),
('REG-037', '25CS045', 'EVT-05', '2026-10-13 09:00:00', 'CONFIRMED'),
('REG-038', '25AD005', 'EVT-05', '2026-10-13 14:00:00', 'CONFIRMED'),
('REG-039', '25AD028', 'EVT-05', '2026-10-14 10:30:00', 'CONFIRMED'),
('REG-040', '25AD049', 'EVT-05', '2026-10-14 12:00:00', 'CONFIRMED'),

('REG-041', '25IT001', 'EVT-06', '2026-10-28 09:00:00', 'CONFIRMED'),
('REG-042', '25IT061', 'EVT-06', '2026-10-28 09:15:00', 'CONFIRMED'),
('REG-043', '25CS012', 'EVT-06', '2026-10-28 10:00:00', 'CONFIRMED'),
('REG-044', '25CS088', 'EVT-06', '2026-10-29 11:00:00', 'CONFIRMED'),
('REG-045', '25EC015', 'EVT-06', '2026-10-29 15:00:00', 'CONFIRMED'),
('REG-046', '25EE042', 'EVT-06', '2026-10-30 08:30:00', 'CONFIRMED'),
('REG-047', '25ME022', 'EVT-06', '2026-10-30 14:00:00', 'CONFIRMED');

-- ----------------------------------------------------------------------------
-- 6. POPULATE ATTENDANCE (Recorded during event day)
-- ----------------------------------------------------------------------------
INSERT INTO attendance (attendance_id, registration_id, check_in_time, status, score) VALUES
('ATT-001', 'REG-001', '2026-08-20 09:05:00', 'ATTENDED', 95),
('ATT-002', 'REG-002', '2026-08-20 09:10:00', 'ATTENDED', 90),
('ATT-003', 'REG-003', '2026-08-20 09:08:00', 'ATTENDED', 92),
('ATT-004', 'REG-004', '2026-08-20 09:15:00', 'ATTENDED', 88),
('ATT-005', 'REG-005', '2026-08-20 09:20:00', 'ATTENDED', 94),
('ATT-006', 'REG-006', '2026-08-20 09:00:00', 'ATTENDED', 85),
('ATT-007', 'REG-007', '2026-08-20 09:12:00', 'ATTENDED', 89),
('ATT-008', 'REG-008', '2026-08-20 09:25:00', 'ATTENDED', 82),
('ATT-009', 'REG-009', '2026-08-20 09:02:00', 'ATTENDED', 96),

('ATT-010', 'REG-011', '2026-09-05 09:00:00', 'ATTENDED', 98),
('ATT-011', 'REG-012', '2026-09-05 09:05:00', 'ATTENDED', 95),
('ATT-012', 'REG-013', '2026-09-05 09:07:00', 'ATTENDED', 90),
('ATT-013', 'REG-014', '2026-09-05 09:12:00', 'ATTENDED', 91),
('ATT-014', 'REG-015', '2026-09-05 09:15:00', 'ATTENDED', 88),
('ATT-015', 'REG-016', '2026-09-05 09:20:00', 'ATTENDED', 93),

('ATT-016', 'REG-018', '2026-09-18 09:30:00', 'ATTENDED', 85),
('ATT-017', 'REG-019', '2026-09-18 09:35:00', 'ATTENDED', 87),
('ATT-018', 'REG-020', '2026-09-18 09:40:00', 'ATTENDED', 90),
('ATT-019', 'REG-021', '2026-09-18 09:45:00', 'ATTENDED', 84),
('ATT-020', 'REG-023', '2026-09-18 09:50:00', 'ATTENDED', 91),

('ATT-021', 'REG-024', '2026-10-10 16:30:00', 'ATTENDED', 100),
('ATT-022', 'REG-025', '2026-10-10 16:35:00', 'ATTENDED', 100),
('ATT-023', 'REG-026', '2026-10-10 16:40:00', 'ATTENDED', 100),
('ATT-024', 'REG-027', '2026-10-10 16:45:00', 'ATTENDED', 100),
('ATT-025', 'REG-028', '2026-10-10 16:50:00', 'ATTENDED', 100),
('ATT-026', 'REG-029', '2026-10-10 17:00:00', 'ATTENDED', 100),
('ATT-027', 'REG-030', '2026-10-10 17:05:00', 'ATTENDED', 100),
('ATT-028', 'REG-031', '2026-10-10 17:15:00', 'ATTENDED', 100),

('ATT-029', 'REG-033', '2026-10-25 09:10:00', 'ATTENDED', 96),
('ATT-030', 'REG-034', '2026-10-25 09:15:00', 'ATTENDED', 94),
('ATT-031', 'REG-035', '2026-10-25 09:20:00', 'ATTENDED', 92),
('ATT-032', 'REG-036', '2026-10-25 09:25:00', 'ATTENDED', 90),
('ATT-033', 'REG-037', '2026-10-25 09:30:00', 'ATTENDED', 88),
('ATT-034', 'REG-038', '2026-10-25 09:35:00', 'ATTENDED', 95),
('ATT-035', 'REG-039', '2026-10-25 09:40:00', 'ATTENDED', 91),

('ATT-036', 'REG-041', '2026-11-05 10:00:00', 'ATTENDED', 90),
('ATT-037', 'REG-042', '2026-11-05 10:05:00', 'ATTENDED', 89),
('ATT-038', 'REG-043', '2026-11-05 10:10:00', 'ATTENDED', 92),
('ATT-039', 'REG-044', '2026-11-05 10:15:00', 'ATTENDED', 94),
('ATT-040', 'REG-045', '2026-11-05 10:20:00', 'ATTENDED', 87);

-- ----------------------------------------------------------------------------
-- 7. POPULATE FEEDBACK
-- ----------------------------------------------------------------------------
INSERT INTO feedback (feedback_id, student_id, event_id, rating, comments, submitted_at) VALUES
('FB-001', '25IT001', 'EVT-01', 5, 'Outstanding symposium! The AI hackathon problem statements were very realistic.', '2026-08-20 17:30:00'),
('FB-002', '25IT031', 'EVT-01', 5, 'Great coordination and excellent keynote address.', '2026-08-20 17:35:00'),
('FB-003', '25IT061', 'EVT-01', 4, 'Very well organized. Lunch arrangements could be faster.', '2026-08-20 17:40:00'),
('FB-004', '25CS012', 'EVT-01', 5, 'Learned state-of-the-art Generative AI techniques.', '2026-08-20 17:45:00'),
('FB-005', '25IT001', 'EVT-02', 5, 'Super hands-on MERN stack sessions. Built my first React app!', '2026-09-06 16:00:00'),
('FB-006', '25IT031', 'EVT-02', 4, 'Trainer was very knowledgeable. Needed a bit more time for deployment.', '2026-09-06 16:15:00'),
('FB-007', '25CS045', 'EVT-02', 5, 'Practical code labs helped understand MongoDB aggregations.', '2026-09-06 16:20:00'),
('FB-008', '25IT091', 'EVT-03', 4, 'Cyber security live exploits were eye opening.', '2026-09-18 16:30:00'),
('FB-009', '25CS088', 'EVT-03', 4, 'Zero-trust architecture concept explained clearly.', '2026-09-18 16:45:00'),
('FB-010', '25IT001', 'EVT-04', 5, 'VIBRANCE was energetic and spectacular!', '2026-10-11 11:00:00'),
('FB-011', '25ME022', 'EVT-04', 5, 'Best cultural event of the year!', '2026-10-11 11:30:00'),
('FB-012', '25IT001', 'EVT-05', 5, 'Hands-on Pandas & Matplotlib practice gave real confidence for Data Science.', '2026-10-25 17:00:00'),
('FB-013', '25AD005', 'EVT-05', 5, 'Perfect complement to our Foundations of Data Science curriculum.', '2026-10-25 17:15:00'),
('FB-014', '25IT061', 'EVT-06', 4, 'Actionable career advice on resume building and GitHub portfolio.', '2026-11-05 16:30:00');

-- ----------------------------------------------------------------------------
-- 8. POPULATE INITIAL ANALYTICS SUMMARY CACHE
-- ----------------------------------------------------------------------------
INSERT INTO analytics (analysis_id, event_id, analysis_type, total_registered, total_attended, attendance_rate, avg_rating, insights, visualization_path) VALUES
('ANA-001', 'EVT-01', 'Symposium Participation', 10, 9, 90.00, 4.75, 'High engagement across IT, CS, and AI departments. 90% attendance achieved.', 'visualizations/01_category_wise_participation.png'),
('ANA-002', 'EVT-02', 'Workshop Attendance', 7, 6, 85.71, 4.67, 'Lab capacity reached threshold; attendees rated hands-on components top score.', 'visualizations/02_registration_vs_capacity_utilization.png'),
('ANA-003', 'EVT-03', 'Seminar Review', 6, 5, 83.33, 4.00, 'Consistent participation from IT and CSE students.', 'visualizations/03_department_wise_engagement.png'),
('ANA-004', 'EVT-04', 'Cultural Engagement', 9, 8, 88.89, 5.00, 'Wide cross-departmental representation including Mechanical and ECE.', 'visualizations/04_feedback_rating_distribution.png'),
('ANA-005', 'EVT-05', 'Data Science Workshop', 8, 7, 87.50, 5.00, 'Strong turnout from IT and AI&DS streams.', 'visualizations/05_monthly_event_trend.png');

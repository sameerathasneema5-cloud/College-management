# MASTER SYLLABUS REQUIREMENTS EVALUATION & SCENARIO MAPPING
## College Event Management and Participation Analysis System (Scenario 1)

---

**Institution:** Velalar College of Engineering and Technology (Autonomous), Erode – 638012  
**Department:** Information Technology | **Class:** 25IT3A & 25IT3B  
**Academic Year:** 2026–2027 (ODD Semester) | **Regulations:** R2025  
**Assigned Student Register Numbers:** `1`, `31`, `61`, `91`, `L1` (e.g., `25IT001`, `25IT031`, `25IT061`, `25IT091`, `25ITL01`)  

---

## 1. Executive Scenario Requirements Matrix

| Functional Requirement (Scenario 1) | 1. Software Engineering (`25ITT33`) | 2. Database Management (`25ITT31`) | 3. Java Programming (`25ITT32`) | 4. Foundations of Data Science (`25ITC31`) |
|---|---|---|---|---|
| **FR-01: Admin Event Management** (Create, Update, View, Delete) | SRS Section 3.1; DFD Process 2.0; Use Case: `Manage Events` | `events` table DDL, foreign key to `categories`, Admin table | `Event.java`, `OrganizerAdmin.java`, `EventService.createEvent()` | `events.csv`, event metadata inspection |
| **FR-02: Student Registration** | SRS Section 3.2; Use Case: `Register for Events` | M:N `registrations` associative table mapping Student to Event | `Student.java`, `Registration.java`, `registerStudent()` | Ingests `registrations.csv`, tracks sign-up volume |
| **FR-03: Duplicate & Capacity Prevention** | SRS Section 3.3; Basis Path Testing & Cyclomatic Complexity ($V(G)=5$) | Composite constraint `UNIQUE(student_id, event_id)` & capacity check | Custom exceptions: `DuplicateRegistrationException`, `CapacityExceededException` | Outlier detection; flags registration overflow |
| **FR-04: Attendance & Feedback Recording** | SRS Section 3.4; Activity Diagram: Attendance Lane | `attendance` table (check-in timestamp), `feedback` table (rating 1-5) | `Feedback.java`, `recordAttendance()`, rating scale validation | Computes turnout rate, feedback rating histograms & KDE |
| **FR-05: View Event Details & Status** | SRS Section 3.5; Presentation Layer CLI / GUI | Relational view: `v_event_summary` | Real-time event inquiry with remaining vacancy counts | Filters events by date, category, and status |
| **FR-06: Reporting & Student Engagement** | SRS Section 3.6; Class Diagram: `ParticipationAnalysis` | Relational view: `v_department_engagement` | `AnalyticsService.java` using Java Streams & `Collectors.groupingBy()` | Departmental participation donut chart & summary statistics |
| **FR-07: Python Retrieval & Visualizations** | Sequence Diagram: Step 15-16 Analytics call | Live SQLite / CSV data extraction | `EventDAO.exportDatasetsToCSV()` for Python integration | `eda_and_visualization.py` rendering 8 high-res PNG plots |

---

## 2. Subject-Wise Folder Organization & Deliverables

```
c:\CollegeManagement\
│
├── 📁 01_Software_Engineering_25ITT33/           [Category 1: Software Engineering]
│   ├── REQUIREMENTS_EVALUATION.md               # Unit-wise syllabus evaluation
│   ├── SRS_Document.md                          # IEEE 830 Requirements Specification
│   ├── System_Design_and_Architecture.md        # 3-Tier Architecture & DFDs
│   ├── Software_Testing_Document.md             # Black-box, White-box & 15 Test Cases
│   ├── Project_Management_and_Agile.md          # 4P's, COCOMO & Risk Management
│   ├── System_Architecture_Diagram.jpg          # System Architecture Diagram
│   ├── DFD_Level_0.jpg                          # Data Flow Diagram Level 0
│   ├── DFD_Level_1.jpg                          # Data Flow Diagram Level 1
│   ├── Use_Case_Diagram.jpg                     # UML Use Case Diagram
│   ├── UML_Class_Diagram.jpg                    # UML Class Diagram
│   ├── Activity_Diagram.jpg                     # UML Activity Diagram
│   ├── Sequence_Diagram.jpg                     # UML Sequence Diagram
│   └── 📁 diagrams/                             # Diagram image archive
│
├── 📁 02_Database_Management_Systems_25ITT31/   [Category 2: Database Management Systems]
│   ├── REQUIREMENTS_EVALUATION.md               # Unit-wise syllabus evaluation
│   ├── Entity_Relationship_Diagram_ERD.jpg      # Uploaded ER Diagram
│   ├── ER_and_Relational_Model.md               # Entity specs & Relational Algebra proofs
│   ├── Normalization_Document.md                # 1NF, 2NF, 3NF, BCNF decomposition proofs
│   ├── database_schema.sql                      # Production DDL schema with constraints & views
│   ├── sample_data.sql                          # Seed DML dataset for VCET events & students
│   ├── analytical_queries.sql                   # 15+ Advanced SQL Queries (CO2, CO3)
│   ├── Transaction_and_Concurrency_Control.md   # ACID, Strict 2PL & Deadlock graphs
│   ├── interactive_dbms.py                      # Interactive SQL Shell & Query Engine
│   ├── run_dbms.bat                             # Double-click runnable SQL runner (.bat)
│   ├── dbms_execution_output.txt                # Full text output of SQL execution
│   ├── dbms_execution_screenshot.png            # Visual screenshot of DBMS execution
│   └── 📁 diagrams/                             # ER Diagram image archive
│
├── 📁 03_Java_Programming_25ITT32/              [Category 3: Java Programming]
│   ├── REQUIREMENTS_EVALUATION.md               # Unit-wise syllabus evaluation
│   ├── 📁 src/com/college/eventmgmt/
│   │   ├── Main.java                            # Interactive terminal portal
│   │   ├── 📁 model/                            # Student, Event, Registration, Feedback, etc.
│   │   ├── 📁 dao/                              # DatabaseConnection & EventDAO
│   │   ├── 📁 service/                          # EventService, Streams & Multithreading
│   │   └── 📁 exception/                        # DuplicateRegistration & CapacityExceeded exceptions
│   ├── run_java.bat                             # Double-click runnable Java runner (.bat)
│   ├── run.bat                                  # Alternate execution batch script
│   ├── java_execution_output.txt                # Full text output of Java execution
│   ├── java_execution_screenshot.png            # Visual screenshot of Java execution
│   ├── sample_inputs_outputs.txt                # Recorded test execution transcript
│   └── Java_Implementation_Documentation.md     # OOP, Collections & Streams mapping
│
├── 📁 04_Foundations_of_Data_Science_25ITC31/   [Category 4: Foundations of Data Science]
│   ├── REQUIREMENTS_EVALUATION.md               # Unit-wise syllabus evaluation
│   ├── 📁 data/                                 # Raw & Preprocessed CSV datasets
│   ├── 📁 visualizations/                       # 8 Generated high-res charts (PNG)
│   ├── interactive_data_science.py              # Interactive Data Science CLI & Analytics Engine
│   ├── run_data_science.bat                     # Double-click runnable Data Science runner (.bat)
│   ├── activity.bat                             # Alternate double-click runner
│   ├── data_science_execution_output.txt        # Full text output of Data Science execution
│   ├── data_science_execution_screenshot.png    # Visual screenshot of Data Science execution
│   ├── data_retrieval.py                        # Database extraction module
│   ├── data_preprocessing.py                    # Cleaning, null imputation & Z-scores
│   ├── eda_and_visualization.py                 # Matplotlib & Seaborn visualization pipeline
│   ├── College_Event_Participation_Analysis.ipynb # Complete interactive Jupyter Notebook
│   └── Data_Science_Analysis_Report.md          # Statistical evaluation & recommendations
│
└── 📁 docs_and_syllabi/                         # Archived course syllabi and assessment sheet
    ├── 25ITT31_DBMS_Syllabus.jpg
    ├── 25ITT32_Java_Programming_Syllabus.jpg
    ├── 25ITT33_Software_Engineering_Syllabus.jpg
    ├── 25ITC31_Foundations_of_Data_Science_Syllabus.jpg
    └── Project_Based_Assessment_Instructions.jpg
```

---

## 3. Interactive Manual Input Testing Guide

Each of the three programming folders includes an interactive runner `.bat` file that can be double-clicked in Windows Explorer to manually give inputs, browse options, and inspect real-time outputs:

### 1. Java Programming (`03_Java_Programming_25ITT32/run_java.bat`)
* **How to run:** Double click `run_java.bat` in `03_Java_Programming_25ITT32`.
* **Manual Inputs Available:**
  - `Option 1`: Administrator Portal $\rightarrow$ Add new events, specify title, venue, capacity, category, date, and description.
  - `Option 2`: Student Portal $\rightarrow$ Enter Register No. (`25IT001`), browse active events, select an event to register, view confirmed registration badge, test duplicate rejection, and enter star rating (1–5) and review comments.
  - `Option 3`: Faculty Analytics Dashboard $\rightarrow$ View department breakdown via Java Streams, category breakdown, and background multithreaded worker logs.
  - `Option 4`: Automated demo verifying all 7 functional requirements.
* **Output Log File:** [java_execution_output.txt](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/java_execution_output.txt)
* **Visual Screenshot:** [java_execution_screenshot.png](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/java_execution_screenshot.png)

### 2. Foundations of Data Science (`04_Foundations_of_Data_Science_25ITC31/run_data_science.bat`)
* **How to run:** Double click `run_data_science.bat` (or `activity.bat`) in `04_Foundations_of_Data_Science_25ITC31`.
* **Manual Inputs Available:**
  - `Option 1`: Select operational dataset to view in terminal (Events, Students, Registrations, Feedback).
  - `Option 2`: View descriptive statistics (Mean, Median, Standard Deviation, Variance, Min, Max).
  - `Option 3`: Enter Event ID manually (e.g. `EVT-01`) to inspect turnout rate, capacity utilization %, reviews, and calculated Z-score.
  - `Option 4`: Test custom participant value (e.g. input `25` or `999`) to evaluate the Z-score formula ($z = \frac{x-\mu}{\sigma}$) and check whether it triggers an outlier alert ($|z| > 3.0$).
  - `Option 5`: Enter department name (e.g. `IT` or `CSE`) to filter student participant records.
  - `Option 6`: Select a specific chart (1 to 8, or All) to render.
* **Output Log File:** [data_science_execution_output.txt](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_science_execution_output.txt)
* **Visual Screenshot:** [data_science_execution_screenshot.png](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_science_execution_screenshot.png)

### 3. Database Management Systems (`02_Database_Management_Systems_25ITT31/run_dbms.bat`)
* **How to run:** Double click `run_dbms.bat` in `02_Database_Management_Systems_25ITT31`.
* **Manual Inputs Available:**
  - `Option 1`: View all database tables and views.
  - `Option 2`: Browse table records by typing table name (e.g. `events`, `students`, `v_event_summary`).
  - `Option 3`: Execute pre-configured analytical query by number (Select query 1 to 15).
  - `Option 4`: Custom interactive SQL shell $\rightarrow$ type any custom SQL statement (`SELECT * FROM events WHERE capacity > 50;`).
  - `Option 5`: Register student manually $\rightarrow$ prompts for Student ID and Event ID, enforces duplicate and capacity checks at SQL level.
  - `Option 6`: Execute all 15 queries in batch mode.
* **Output Log File:** [dbms_execution_output.txt](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/dbms_execution_output.txt)
* **Visual Screenshot:** [dbms_execution_screenshot.png](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/dbms_execution_screenshot.png)

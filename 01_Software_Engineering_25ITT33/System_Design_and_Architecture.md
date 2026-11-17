# SYSTEM DESIGN AND ARCHITECTURAL SPECIFICATION
## College Event Management and Participation Analysis System

---

**Course Code & Name:** 25ITT33 – Software Engineering  
**Department:** Information Technology  
**Institution:** Velalar College of Engineering and Technology, Erode – 12  
**Activity:** Project-Based Assessment (Scenario 1)  

---

## 1. Executive Summary

This document details the architectural, functional, and object-oriented design for the **College Event Management and Participation Analysis System**. The design bridges user requirements with physical implementation, employing structured analysis models (Data Flow Diagrams) and Object-Oriented Modeling (Unified Modeling Language - UML).

---

## 2. Overall System Architecture

The system follows a modern **Multi-Tier Layered Architecture** augmented with an asynchronous **Python Analytics Subsystem**. This decouples high-throughput Online Transaction Processing (OLTP) in the Java backend from compute-intensive Online Analytical Processing (OLAP) in Python.

### Architectural Blueprint
![System Architecture Diagram](diagrams/System_Architecture_Diagram.jpg)

### Layer Descriptions

1. **Presentation Layer:**
   - Provides user interfaces for **Students**, **Administrators/Organizers**, and **Faculty**.
   - Includes Web Portal, CLI dashboards, and reporting views.
   - Handles input presentation, form validation, and renders data visualization charts.

2. **Application (Business Logic) Layer:**
   - Implemented in **Java 21**.
   - Contains core services: `Event Management`, `Registration & Attendance`, `Participation Analysis`, `Feedback Management`, and `Report Generation`.
   - Enforces business rules: prevents duplicate registrations, enforces capacity thresholds, and handles concurrency locks.

3. **Data Layer:**
   - Implemented in **MySQL / SQLite Relational Database Management System**.
   - Handles structured data persistence, normalization (3NF/BCNF), relational constraints, and query execution.
   - Maintains transactional consistency through ACID properties.

4. **Analytics Layer (Python 3.13):**
   - Connects to the database to extract operational event and participant datasets.
   - Performs data preprocessing, cleaning, outlier detection, and feature engineering with **NumPy** and **Pandas**.
   - Generates visual analytics, trends, and correlation charts using **Matplotlib** and **Seaborn**.
   - Outputs reports, statistics, predictive insights, and exportable data (CSV/PDF).

```mermaid
graph TD
    subgraph Users ["Users / Actors"]
        U1["Student<br>(Register, Participate, Feedback)"]
        U2["Admin / Organizer<br>(Manage Events, View Reports)"]
        U3["Faculty<br>(Analyze Data, View Insights)"]
    end

    subgraph Presentation ["Presentation Layer"]
        P1["Web Portal / Terminal UI"]
        P2["Dashboard & Reports"]
    end

    subgraph Application ["Application Layer (Java Backend)"]
        A1["Event Management"]
        A2["Registration & Attendance"]
        A3["Participation Analysis"]
        A4["Feedback Management"]
        A5["Report Generation"]
    end

    subgraph Data ["Data Layer"]
        D1[("Relational Database<br>(MySQL / SQLite)")]
        D2["SQL Queries & Normalization"]
    end

    subgraph Analytics ["Analytics Layer (Python)"]
        PY1["Data Preprocessing"]
        PY2["Data Visualization (Matplotlib/Seaborn)"]
        PY3["Trend & Engagement Analysis"]
    end

    subgraph Outputs ["Outputs"]
        O1["Visual Reports & Dashboards"]
        O2["Event Reports & Statistics"]
        O3["Actionable Insights & Recommendations"]
    end

    Users --> Presentation
    Presentation --> Application
    Application --> Data
    Data --> Analytics
    Analytics --> Outputs
    Outputs --> Presentation
```

---

## 3. Data Flow Diagrams (DFD)

Data Flow Modeling traces the transformation of data through the system from source to sink.

### 3.1 Level 0 DFD (Context Level)
The Context Diagram establishes the boundary of the system, external entities, and high-level input/output data flows.

![Data Flow Diagram - Level 0](diagrams/DFD_Level_0.jpg)

#### External Entities & Flow Description:
1. **Student:**
   - **Inputs to System:** Registration Details, Event Participation Request, Feedback.
   - **Outputs from System:** Event Info, Confirmation, Attendance Status, Reports.
2. **Admin / Organizer:**
   - **Inputs to System:** Manage Events (Add/Update/Delete), Event Details, Reports Request.
   - **Outputs from System:** Event Reports, Participation Analytics, Attendance Logs.
3. **Faculty:**
   - **Inputs to System:** View Events Request, Participation Data Query, Analysis Request.
   - **Outputs from System:** Departmental Reports, Student Engagement Insights.
4. **Data Analysis & Visualization (Python Subsystem):**
   - Receives raw transactional data from database.
   - Transmits processed charts, descriptive metrics, and insights back to system.
5. **Data Stores:**
   - **D1 Events Database:** Stores scheduled events, dates, venues, categories, capacities.
   - **D2 Participants Database:** Stores student profiles, registrations, and attendance logs.
   - **D3 Feedback Database:** Stores ratings, reviews, and qualitative feedback.

---

### 3.2 Level 1 DFD (Detailed Functional Decomposition)
Decomposes the core system into 5 primary operational sub-processes.

![Data Flow Diagram - Level 1](diagrams/DFD_Level_1.jpg)

```mermaid
graph TD
    S([Student]) -->|Registration Details| P1["1.0 Manage Registrations"]
    S -->|Participation Request| P1
    S -->|Feedback| P4["4.0 Generate Reports"]
    P1 -->|Confirmation / Status| S

    AO([Admin / Organizer]) -->|Create / Update Events| P2["2.0 Manage Events"]
    AO -->|Reports Request| P4

    F([Faculty]) -->|Analysis Request| P3["3.0 Analyze Participation"]
    P4 -->|View Reports & Insights| F

    P1 <-->|Registration Data| DB1[("D1 Participants Database")]
    P2 <-->|Event Data| DB2[("D2 Events Database")]
    P1 -->|Registered Users| P2
    P2 -->|Event Information| P3
    P3 <-->|Analysis Data / Reports| DB3[("D3 Analytics Database")]
    P3 -->|Insights| P4
    P4 -->|Report Output| P5["5.0 Data Visualization (Python)"]
    P5 -->|Charts & Graphs| DASH["Dashboard & Visual Insights"]
    DASH --> F
```

#### Detailed Process Descriptions:
- **Process 1.0 (Manage Registrations):** Validates incoming student requests, cross-references event capacity from D2, checks for duplicate records in D1, and issues confirmation.
- **Process 2.0 (Manage Events):** Admin registers new symposiums/workshops, edits date/venue, or archives concluded events in D2.
- **Process 3.0 (Analyze Participation):** Pulls attendance logs from D1 and event schedules from D2 to calculate attendance ratios and department engagement.
- **Process 4.0 (Generate Reports):** Synthesizes feedback ratings from D3 with participation logs to compile audit reports.
- **Process 5.0 (Data Visualization using Python):** Preprocesses raw metrics into visual plots (Bar, Pie, Heatmap, Box plots) for display on the interactive dashboard.

---

## 4. Unified Modeling Language (UML) Diagrams

### 4.1 UML Use Case Diagram
Specifies system functionality from the perspective of external actors.

![Use Case Diagram](diagrams/Use_Case_Diagram.jpg)

#### Actors & Use Cases:
- **Student:**
  - `Register / Login`
  - `View Events`
  - `Register for Events`
  - `View My Participation`
  - `Provide Feedback`
- **Admin / Organizer:**
  - `Manage Events` (includes `Add Event`, `Update Event`, `Cancel Event`, `View Event Details`)
  - `Analyze Participation Data` (includes `Generate Reports`, `View Graphs & Insights`, `Export Reports`)
- **Faculty:**
  - `Analyze Participation Data`
  - `Manage Feedback` (includes `View Feedback`, `Respond to Feedback`)
- **System:**
  - `Manage Profile` (includes `Update Profile`)

```mermaid
graph LR
    subgraph SystemBoundary ["College Event Management System"]
        UC1((Register / Login))
        UC2((View Events))
        UC3((Register for Events))
        UC4((View My Participation))
        UC5((Provide Feedback))
        UC6((Manage Events))
        UC6_1((Add Event))
        UC6_2((Update Event))
        UC6_3((Cancel Event))
        UC7((Analyze Participation Data))
        UC7_1((Generate Reports))
        UC7_2((View Graphs & Insights))
        UC7_3((Export Reports))
        UC8((Manage Feedback))
        UC9((Manage Profile))
    end

    Student((Student)) --> UC1
    Student --> UC2
    Student --> UC3
    Student --> UC4
    Student --> UC5

    Admin((Admin / Organizer)) --> UC6
    Admin --> UC7

    Faculty((Faculty)) --> UC7
    Faculty --> UC8

    SysActor((System)) --> UC9

    UC6 -.->|<<include>>| UC6_1
    UC6 -.->|<<include>>| UC6_2
    UC6 -.->|<<include>>| UC6_3
    UC7 -.->|<<include>>| UC7_1
    UC7 -.->|<<include>>| UC7_2
    UC7 -.->|<<include>>| UC7_3
    UC7 -.->|<<extend>>| UC6
    UC7 -.->|<<extend>>| UC8
```

---

### 4.2 UML Class Diagram
Represents the static structure of the object-oriented Java system.

![UML Class Diagram](diagrams/UML_Class_Diagram.jpg)

```mermaid
classDiagram
    class Student {
        -String studentId
        -String name
        -String email
        -String department
        -int year
        +registerForEvent()
        +viewEvents()
        +viewParticipation()
        +giveFeedback()
    }

    class Event {
        -String eventId
        -String title
        -String description
        -Date date
        -String venue
        -int capacity
        -String category
        +addEvent()
        +updateEvent()
        +cancelEvent()
        +getEventDetails()
    }

    class Registration {
        -String registrationId
        -Date registrationDate
        -String status
        +checkEligibility()
        +confirmRegistrations()
        +cancelRegistration()
    }

    class OrganizerAdmin {
        -String adminId
        -String name
        -String email
        -String role
        +addEvent()
        +manageRegistrations()
        +generateReport()
        +viewAnalytics()
    }

    class Feedback {
        -String feedbackId
        -String studentId
        -String eventId
        -String comments
        -int rating
        +submitFeedback()
        +viewFeedback()
    }

    class ParticipationAnalysis {
        -String analysisId
        -String eventId
        -int totalParticipants
        -double attendanceRate
        -String insights
        +generateReport()
        +createVisualizations()
        +exportData()
    }

    class Database {
        -String dbId
        -String dbName
        -String connectionString
        +connect()
        +executeQuery()
        +fetchData()
        +closeConnection()
    }

    Student "1" -- "0..*" Registration : Registers
    Event "1" -- "0..*" Registration : Has
    OrganizerAdmin "1" -- "0..*" Registration : Made by
    Student "1" -- "0..*" Feedback : Provides
    Feedback "0..*" -- "1" ParticipationAnalysis : Helps Improve
    Event "1" -- "0..*" ParticipationAnalysis : Belongs to
    ParticipationAnalysis "1" -- "1" Database : Stores Data In
```

---

### 4.3 UML Activity Diagram
Details the end-to-end dynamic operational workflow across actors and swimlanes.

![Activity Diagram](diagrams/Activity_Diagram.jpg)

- **Student Lane:** Starts application $\rightarrow$ Login/Register $\rightarrow$ Browse events $\rightarrow$ Confirm participation $\rightarrow$ View reports.
- **System Lane:** Validates credentials $\rightarrow$ Verifies seat availability $\rightarrow$ Dispatches confirmation $\rightarrow$ Calls Python analytics script.
- **Database Lane:** Updates events, stores registrations, persists feedback ratings, and yields query results.
- **Admin Lane:** Initializes events, modifies dates/venues, adjusts quotas.

---

### 4.4 UML Sequence Diagram
Models object interaction and chronological message passing during event registration and analytical reporting.

![Sequence Diagram](diagrams/Sequence_Diagram.jpg)

1. `Student` sends credentials to `Web Interface (Frontend)`.
2. Frontend delegates to `Application (Java Backend)`.
3. Backend validates against `Database (MySQL)`.
4. Student selects an event and requests registration.
5. Backend verifies capacity, checks duplicate entries, and saves record to Database.
6. Confirmation acknowledgment returns to Student.
7. Student/Faculty requests participation report.
8. Backend queries Database for attendance and scores.
9. Backend invokes `Analytics (Python)` subsystem to preprocess data and render charts.
10. Final report with charts and engagement metrics is displayed on Frontend.

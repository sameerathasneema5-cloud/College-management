# REQUIREMENTS EVALUATION & SYLLABUS MAPPING
## Subject: 25ITT32 – Java Programming

---

**Institution:** Velalar College of Engineering and Technology, Erode – 638012  
**Department:** Information Technology  
**Academic Year:** 2026–2027 (ODD Semester)  
**Curriculum Regulations:** R2025  
**Scenario:** Scenario 1 – College Event Management and Participation Analysis System  
**Assigned Register Numbers:** 1, 31, 61, 91, L1  

---

## 1. Syllabus Unit-Wise Evaluation (25ITT32)

| Syllabus Unit & Topics | Scenario Requirements Addressed | Implementation Deliverable & Artifact Location |
|---|---|---|
| **UNIT 1: INTRODUCTION TO JAVA**<br>OOP concepts, Data types, Control statements, Classes, Objects, Methods, Constructors, Static, Scanner Console I/O | Implementing domain entities (`Student`, `Event`, `OrganizerAdmin`) with full encapsulation, constructors, and terminal menus. | - [Student.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/model/Student.java)<br>- [Event.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/model/Event.java)<br>- [OrganizerAdmin.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/model/OrganizerAdmin.java)<br>- [Main.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/Main.java) (Scanner interactive console). |
| **UNIT 2: INHERITANCE, PACKAGES & INTERFACES**<br>Packages, Member access, Overriding, Abstract classes, Interfaces, dynamic dispatch | Modular package architecture and object contract overrides (`toString`, `equals`, `hashCode`). | - Packages: `com.college.eventmgmt.model`, `dao`, `service`, `exception`.<br>- [Registration.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/model/Registration.java)<br>- [Category.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/model/Category.java). |
| **UNIT 3: EXCEPTION HANDLING & MULTITHREADING**<br>try-catch-finally, Custom user-defined exceptions, Java Thread Model, Synchronization, Worker Threads | FR-03 enforcement: Catching duplicate registration and capacity overflow, and background asynchronous report worker. | - [DuplicateRegistrationException.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/exception/DuplicateRegistrationException.java)<br>- [CapacityExceededException.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/exception/CapacityExceededException.java)<br>- [ReportGeneratorThread.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/service/ReportGeneratorThread.java) (Extends `Thread`, performs background batch aggregation). |
| **UNIT 4: STRING HANDLING & DATABASE CONNECTION**<br>String operations, StringBuilder, JDBC / JPA connection management | Formatted string output, CSV dataset persistence, and database connection singleton. | - [DatabaseConnection.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/dao/DatabaseConnection.java) (JDBC singleton for MySQL/SQLite).<br>- `EventDAO.exportDatasetsToCSV()` for Python integration. |
| **UNIT 5: GENERICS, COLLECTIONS, LAMBDAS & STREAMS**<br>Generics, Collections (`List`, `Map`, `ConcurrentHashMap`), Lambdas, Stream API (filtering, mapping, sorting, collectors, reduction) | Calculating participation statistics, department breakdown, capacity rankings, and average ratings using modern Java Streams. | - [AnalyticsService.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/service/AnalyticsService.java)<br>- `stream().filter().collect(Collectors.groupingBy())`<br>- `sorted(Comparator.comparingDouble(...))` |

---

## 2. Interactive Execution & Output Deliverables

The Java subsystem includes a complete **interactive console portal** where users can manually provide inputs and test functionality:

1. **Double-Click Runnable Runner:** [run_java.bat](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/run_java.bat) (and [run.bat](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/run.bat))
2. **Interactive Program File:** [Main.java](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/src/com/college/eventmgmt/Main.java)
   - Menu 1: Administrator Portal (Create events, view schedules, record attendance, export CSV)
   - Menu 2: Student Portal (Login with Register No., browse active events, register, submit rating & feedback)
   - Menu 3: Faculty Analytics Dashboard (Java 21 Streams breakdown & multithreaded worker)
   - Menu 4: Automated Demonstration Mode (`--demo`)
3. **Execution Output Text File:** [java_execution_output.txt](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/java_execution_output.txt)
4. **Execution Screenshot:** [java_execution_screenshot.png](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/java_execution_screenshot.png)
5. **Technical Documentation:** [Java_Implementation_Documentation.md](file:///c:/CollegeManagement/03_Java_Programming_25ITT32/Java_Implementation_Documentation.md)

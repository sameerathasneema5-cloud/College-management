# JAVA APPLICATION ARCHITECTURE AND IMPLEMENTATION GUIDE
## College Event Management and Participation Analysis System

---

**Course Code & Name:** 25ITT32 – Java Programming  
**Department:** Information Technology  
**Institution:** Velalar College of Engineering and Technology, Erode – 12  
**Activity:** Project-Based Assessment (Scenario 1)  

---

## 1. Curriculum Alignment with Syllabus (25ITT32)

The Java application is developed in strict alignment with the 5 Units of the **25ITT32: Java Programming** curriculum:

| Unit | Syllabus Topics | Project Implementation & Proof in Source Code |
|---|---|---|
| **Unit 1: Introduction to Java** | OOP concepts, Classes, Objects, Constructors, Methods, Access Control, Scanner I/O | `Event.java`, `Student.java`, `OrganizerAdmin.java` with encapsulation, private fields, constructors, getters/setters, formatted terminal I/O in `Main.java`. |
| **Unit 2: Inheritance, Packages & Interfaces** | Method overriding, dynamic dispatch, packages, modular code | Clean package hierarchy (`com.college.eventmgmt.model`, `dao`, `service`, `exception`), `equals()`, `hashCode()`, `toString()` method overriding. |
| **Unit 3: Exception Handling & Multithreading** | `try-catch-finally`, Custom Exceptions, Threads, Thread Lifecycle, Concurrency | Custom exceptions (`DuplicateRegistrationException`, `CapacityExceededException`, `EventNotFoundException`), background asynchronous thread (`ReportGeneratorThread`). |
| **Unit 4: String Handling & Database Connection** | `String`, `StringBuilder`, JDBC Connectivity, Prepared Statements | Format strings, CSV file I/O, JDBC singleton manager in `DatabaseConnection.java` with MySQL/SQLite compatibility. |
| **Unit 5: Generics, Collections, Lambdas & Streams** | Generics, Collections (`List`, `Map`), Lambda Expressions, Stream API, Filtering, Mapping, Sorting, Collectors | `AnalyticsService.java` leveraging `stream().filter().collect(Collectors.groupingBy())`, `sorted(Comparator)`, `average()`. |

---

## 2. Package Architecture

```
03_Java_Programming_25ITT32/
├── bin/                             # Compiled Java Bytecode (.class files)
├── src/
│   └── com/college/eventmgmt/
│       ├── Main.java                # Interactive console UI & test runner
│       ├── dao/
│       │   ├── DatabaseConnection.java  # JDBC connection singleton
│       │   └── EventDAO.java            # In-memory & persistent data access
│       ├── exception/
│       │   ├── CapacityExceededException.java
│       │   ├── DuplicateRegistrationException.java
│       │   ├── EventNotFoundException.java
│       │   └── InvalidFeedbackException.java
│       ├── model/
│       │   ├── Category.java
│       │   ├── Event.java
│       │   ├── Feedback.java
│       │   ├── OrganizerAdmin.java
│       │   ├── ParticipationAnalysis.java
│       │   ├── Registration.java
│       │   └── Student.java
│       └── service/
│           ├── AnalyticsService.java    # Java Streams & Lambdas engine
│           ├── EventService.java        # Core business workflow rules
│           └── ReportGeneratorThread.java # Multithreaded asynchronous worker
├── run.bat                          # 1-click Windows Batch execution script
├── run.ps1                          # 1-click PowerShell runner
└── sample_inputs_outputs.txt        # Full recorded test execution transcript
```

---

## 3. Core Business Logic & Invariant Enforcement

### 3.1 Duplicate Registration Prevention (FR-03)
Before creating any registration, `EventDAO.registerStudentForEvent` queries the active registration collection using a Java Stream predicate:
```java
boolean alreadyRegistered = registrationsMap.values().stream()
    .anyMatch(r -> r.getStudentId().equalsIgnoreCase(studentId) && 
                   r.getEventId().equalsIgnoreCase(eventId) &&
                   !"CANCELLED".equalsIgnoreCase(r.getStatus()));

if (alreadyRegistered) {
    throw new DuplicateRegistrationException(studentId, eventId);
}
```

### 3.2 Capacity Overbooking Prevention (FR-03)
The event capacity constraint is thread-safe using synchronized checks:
```java
public synchronized boolean canAcceptRegistration() {
    return "ACTIVE".equalsIgnoreCase(this.status) && this.registeredCount < this.capacity;
}
```
If `registeredCount >= capacity`, the system immediately throws `CapacityExceededException(eventId, capacity)`.

### 3.3 Advanced Data Processing using Java Streams (Unit 5)
Departmental participation breakdown is calculated using lambda grouping:
```java
Map<String, Long> deptBreakdown = eventDAO.getAllRegistrations().stream()
    .map(r -> eventDAO.getStudent(r.getStudentId()))
    .filter(Objects::nonNull)
    .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
```

---

## 4. How to Compile and Execute

### Quick Execution (Command Prompt / PowerShell):
```powershell
# Navigate to Java directory
cd c:\CollegeManagement\03_Java_Programming_25ITT32

# Compile and run interactive portal
.\run.bat

# Or run automated demonstration mode directly:
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | Select-Object -ExpandProperty FullName)
java -cp bin com.college.eventmgmt.Main --demo
```

# College Event Management and Participation Analysis System
### Integrated Academic Project-Based Assessment (2026–2027 ODD Semester)

**Institution:** Velalar College of Engineering and Technology (Autonomous), Erode – 638012  
**Department:** Department of Information Technology  
**Class:** 25IT3A & 25IT3B | **Regulations:** R2025  
**Assigned Scenario:** **Scenario 1 – College Event Management and Participation Analysis System**  
**Student Register Numbers:** `1`, `31`, `61`, `91`, `L1` (e.g., `25IT001`, `25IT031`, `25IT061`, `25IT091`, `25ITL01`)  

---

## 📌 Problem Statement

Velalar College of Engineering and Technology organizes numerous co-curricular and extracurricular activities, including national technical symposiums, hands-on workshops, research seminars, and cultural fests. 

Previously, event scheduling, student registrations, attendance logging, and feedback evaluation were conducted manually using paper forms or fragmented spreadsheets. This legacy process caused frequent registration errors, duplicate entries, uncontrolled venue overbooking, untracked student participation, and an absence of data-driven insights into event popularity and departmental engagement.

This project delivers an autonomous, multi-tier **College Event Management and Participation Analysis System** that:
1. Provides structured event scheduling, quotas, and registration workflows.
2. Enforces real-time validation to prevent duplicate registrations and capacity breaches.
3. Digitally captures event-day attendance and student feedback ratings.
4. Stores all transactions in a normalized relational database schema (3NF/BCNF).
5. Integrates an end-to-end Python Data Science pipeline to retrieve, clean, preprocess, and visualize participation trends using statistical metrics and graphical plots.

---

## 🗂️ Subject-Wise Folder Structure

The project repository is strictly partitioned into dedicated folders corresponding to each of the 4 academic curriculum subjects:

```
c:\CollegeManagement\
│
├── 📁 01_Software_Engineering_25ITT33/
│   ├── 📁 diagrams/
│   │   ├── System_Architecture_Diagram.jpg
│   │   ├── DFD_Level_0.jpg
│   │   ├── DFD_Level_1.jpg
│   │   ├── Use_Case_Diagram.jpg
│   │   ├── UML_Class_Diagram.jpg
│   │   ├── Activity_Diagram.jpg
│   │   └── Sequence_Diagram.jpg
│   ├── SRS_Document.md                      # IEEE 830 compliant SRS specification
│   ├── System_Design_and_Architecture.md    # Architecture & diagram breakdowns
│   ├── Software_Testing_Document.md         # Black-Box, White-Box & 15 test cases
│   └── Project_Management_and_Agile.md      # 4P's, COCOMO & Risk Management
│
├── 📁 02_Database_Management_Systems_25ITT31/
│   ├── 📁 diagrams/
│   │   └── Entity_Relationship_Diagram_ERD.jpg
│   ├── Entity_Relationship_Diagram_ERD.jpg      # ER Diagram
│   ├── run_dbms.bat                             # Double-Click Runnable SQL Runner (.bat)
│   ├── dbms_execution_output.txt                # Output text file of 15 queries executed
│   ├── dbms_execution_screenshot.png            # Screenshot of DBMS execution
│   ├── execute_dbms.py                          # SQL execution engine
│   ├── ER_and_Relational_Model.md               # ER model & Relational Algebra proofs
│   ├── Normalization_Document.md                # 1NF, 2NF, 3NF, BCNF decomposition proofs
│   ├── database_schema.sql                      # Production DDL schema with constraints & views
│   ├── sample_data.sql                          # Seed DML dataset for VCET events & students
│   ├── analytical_queries.sql                   # 15+ Advanced SQL queries (CO2, CO3)
│   └── Transaction_and_Concurrency_Control.md   # ACID, Strict 2PL & Deadlock handling
│
├── 📁 03_Java_Programming_25ITT32/
│   ├── 📁 src/com/college/eventmgmt/
│   │   ├── Main.java                            # Interactive application entry point
│   │   ├── 📁 model/                            # Domain entities (Event, Student, Registration, etc.)
│   │   ├── 📁 dao/                              # Database connection & EventDAO
│   │   ├── 📁 service/                          # Business logic, Streams & Multithreading
│   │   └── 📁 exception/                        # Custom exception classes
│   ├── 📁 bin/                                  # Compiled bytecode
│   ├── run_java.bat                             # Double-Click Runnable Java Runner (.bat)
│   ├── java_execution_output.txt                # Output text file of Java execution
│   ├── java_execution_screenshot.png            # Screenshot of Java execution
│   ├── run.bat                                  # Alternate execution batch script
│   ├── run.ps1                                  # Alternate PowerShell runner
│   ├── sample_inputs_outputs.txt                # Recorded test execution transcript
│   └── Java_Implementation_Documentation.md     # OOP, Collections & Streams mapping
│
├── 📁 04_Foundations_of_Data_Science_25ITC31/
│   ├── 📁 data/                                 # Raw & Preprocessed CSV datasets
│   ├── 📁 visualizations/                       # 8 Generated high-res charts (PNG)
│   ├── run_data_science.bat                     # Double-Click Runnable Data Science Runner (.bat)
│   ├── data_science_execution_output.txt        # Output text file of Data Science execution
│   ├── data_science_execution_screenshot.png    # Screenshot of Data Science execution
│   ├── data_retrieval.py                        # Database extraction module
│   ├── data_preprocessing.py                    # Cleaning, null imputation & Z-scores
│   ├── eda_and_visualization.py                 # Matplotlib & Seaborn visualization engine
│   ├── College_Event_Participation_Analysis.ipynb # Complete interactive Jupyter Notebook
│   └── Data_Science_Analysis_Report.md          # Statistical evaluation & recommendations
│
└── 📁 docs_and_syllabi/                     # Official course syllabi & instruction sheet
    ├── 25ITT31_DBMS_Syllabus.jpg
    ├── 25ITT32_Java_Programming_Syllabus.jpg
    ├── 25ITT33_Software_Engineering_Syllabus.jpg
    ├── 25ITC31_Foundations_of_Data_Science_Syllabus.jpg
    └── Project_Based_Assessment_Instructions.jpg
```

---

## 🛠️ Technology Stack

| Layer / Subsystem | Technology | Purpose |
|---|---|---|
| **Software Modeling & Design** | UML 2.5, IEEE 830 Standard, Mermaid | Requirements specification, architectural models & test design |
| **Backend Application** | Java 21 (OpenJDK Temurin LTS) | Business logic, concurrency control, Java Streams API, OOP |
| **Database Management** | MySQL 8.0+ / SQLite 3.40+ | 3NF/BCNF relational storage, relational algebra queries |
| **Data Analytics** | Python 3.13, NumPy, Pandas | Data extraction, cleaning, outlier detection, Z-score modeling |
| **Data Visualization** | Matplotlib 3.11, Seaborn 0.13 | Statistical graphs, time-series trends, correlation heatmaps |
| **Interactive Notebook** | Jupyter Notebook (.ipynb) | Documented data science exploration and reproducible analytics |
| **Version Control** | Git 2.52 & GitHub | Source code, schema scripts, and artifact management |

---

## 🚀 Execution Guide

### 1. Java Application Setup & Run
To run the interactive Java application (providing menus for Admins, Students, and Faculty):
```cmd
cd 03_Java_Programming_25ITT32
run.bat
```
To run the automated verification suite (which automatically verifies all 7 Functional Requirements and exports the latest CSV datasets):
```cmd
java -cp bin com.college.eventmgmt.Main --demo
```

### 2. Database Setup (MySQL)
To initialize the database in MySQL Server:
```cmd
mysql -u root -p < 02_Database_Management_Systems_25ITT31/database_schema.sql
mysql -u root -p college_events_db < 02_Database_Management_Systems_25ITT31/sample_data.sql
mysql -u root -p college_events_db < 02_Database_Management_Systems_25ITT31/analytical_queries.sql
```

### 3. Python Data Science Pipeline Setup & Run
To execute the data cleaning, preprocessing, and render all 8 visualization charts:
```cmd
cd 04_Foundations_of_Data_Science_25ITC31
python eda_and_visualization.py
```
To launch the interactive Jupyter Notebook:
```cmd
jupyter notebook College_Event_Participation_Analysis.ipynb
```

---

## 📋 Deliverables & Assessment Mapping

| S.No | Mandated Deliverable (Instruction Sheet) | Repository Artifact Location | Status |
|:---:|---|---|:---:|
| **1** | SRS, Design Diagrams, and Testing Documents | `01_Software_Engineering_25ITT33/` | ✅ Complete |
| **2** | Java Source Code, Sample Inputs, and Outputs | `03_Java_Programming_25ITT32/` | ✅ Complete |
| **3** | ER Diagram, Relational Model, SQL Scripts & Normalization | `02_Database_Management_Systems_25ITT31/` | ✅ Complete |
| **4** | Python Code for Retrieval, Preprocessing & Visualization | `04_Foundations_of_Data_Science_25ITC31/` | ✅ Complete |
| **5** | Master README.md & Syllabi Archives | Root `README.md` & `docs_and_syllabi/` | ✅ Complete |
| **6** | Runnable `.bat` Files (Double-Click Execution) | `run_java.bat`, `run_dbms.bat`, `run_data_science.bat` | ✅ Complete |
| **7** | Output Text Files for all 3 Languages | `*_execution_output.txt` in respective folders | ✅ Complete |
| **8** | Screenshots of Program Execution | `*_execution_screenshot.png` in respective folders | ✅ Complete |

---
*Developed for Project-Based Assessment in compliance with VCET Regulation R2025.*

# REQUIREMENTS EVALUATION & SYLLABUS MAPPING
## Subject: 25ITT31 – Database Management Systems

---

**Institution:** Velalar College of Engineering and Technology, Erode – 638012  
**Department:** Information Technology  
**Academic Year:** 2026–2027 (ODD Semester)  
**Curriculum Regulations:** R2025  
**Scenario:** Scenario 1 – College Event Management and Participation Analysis System  
**Assigned Register Numbers:** 1, 31, 61, 91, L1  

---

## 1. Syllabus Unit-Wise Evaluation (25ITT31)

| Syllabus Unit & Topics | Scenario Requirements Addressed | Implementation Deliverable & Artifact Location |
|---|---|---|
| **UNIT 1: DATABASE SYSTEMS AND DESIGN**<br>E-R Model, Mapping Cardinalities, Complex Attributes, Relational Model, Keys, Relational Algebra | Designing entity schemas, cardinalities (M:N student-event, 1:N category-event, 1:1 registration-attendance), and algebraic query formulation. | - [ER_and_Relational_Model.md](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/ER_and_Relational_Model.md)<br>- [Entity_Relationship_Diagram_ERD.jpg](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/Entity_Relationship_Diagram_ERD.jpg)<br>- Relational Algebra expressions for Selection ($\sigma$), Projection ($\pi$), Natural Join ($\bowtie$), Set Difference ($-$), and Division ($\div$). |
| **UNIT 2: SQL**<br>DDL, DML, Joins, Views, Nested subqueries, Integrity constraints, Triggers, Exists, Aggregates | Implementing relational schema, foreign key cascades, uniqueness constraints, analytical views, and complex joins. | - [database_schema.sql](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/database_schema.sql) (9 Tables, Indexes, Views: `v_event_summary`, `v_department_engagement`).<br>- [sample_data.sql](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/sample_data.sql) (Seed dataset for VCET).<br>- [analytical_queries.sql](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/analytical_queries.sql) (15 advanced queries). |
| **UNIT 3: NORMALIZATION & QUERY OPTIMIZATION**<br>Functional Dependencies, Armstrong's Axioms, 1NF, 2NF, 3NF, BCNF, Storage, B+ Tree Indexing | Decomposing unnormalized event tables into 3NF/BCNF without data redundancy or anomalies. | - [Normalization_Document.md](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/Normalization_Document.md)<br>- Step-by-step 1NF, 2NF, 3NF, BCNF decomposition proofs.<br>- Proof of Lossless Join Decomposition and Dependency Preservation. |
| **UNIT 4: TRANSACTION MANAGEMENT**<br>ACID Properties, Concurrency Control, Lock-based protocols (2PL), Deadlock Handling (Wait-For Graph) | Preventing registration overbooking race conditions during high concurrent traffic. | - [Transaction_and_Concurrency_Control.md](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/Transaction_and_Concurrency_Control.md)<br>- Strict 2PL locking protocols, Wait-Die/Wound-Wait deadlock prevention, and WFG detection. |
| **UNIT 5: NO SQL DATABASES & INTEGRATION**<br>Document-based stores (MongoDB), BASE properties, Data retrieval for Python Analytics | Bridge connecting relational transactional store with Python analytics engine. | - [interactive_dbms.py](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/interactive_dbms.py) (SQLite & MySQL compatible bridge).<br>- [data_retrieval.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_retrieval.py). |

---

## 2. Interactive Execution & Output Deliverables

The database layer includes an **interactive SQL environment** enabling manual testing and verification:

1. **Double-Click Runnable Runner:** [run_dbms.bat](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/run_dbms.bat)
2. **Interactive Program File:** [interactive_dbms.py](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/interactive_dbms.py)
   - Browse tables interactively
   - Run any of the 15 analytical queries by number
   - Custom SQL query terminal (`SQL>`)
   - Manually test duplicate registration and capacity overflow constraints
3. **Execution Output Text File:** [dbms_execution_output.txt](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/dbms_execution_output.txt)
4. **Execution Screenshot:** [dbms_execution_screenshot.png](file:///c:/CollegeManagement/02_Database_Management_Systems_25ITT31/dbms_execution_screenshot.png)

---

## 3. Course Outcomes (CO) Coverage Matrix

- **CO1 (Design Schema & ER Model):** Covered via `Entity_Relationship_Diagram_ERD.jpg` and `ER_and_Relational_Model.md`.
- **CO2 (Develop SQL Queries with Aggregates & Joins):** Covered via `database_schema.sql` and `analytical_queries.sql` (Queries 1 to 10).
- **CO3 (Apply Normalization & Optimization):** Covered via `Normalization_Document.md` (1NF through BCNF) and B+ tree index creation.
- **CO4 (Discuss Concurrency Control & Deadlocks):** Covered via `Transaction_and_Concurrency_Control.md`.

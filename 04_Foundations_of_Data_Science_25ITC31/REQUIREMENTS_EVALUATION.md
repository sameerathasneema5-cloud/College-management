# REQUIREMENTS EVALUATION & SYLLABUS MAPPING
## Subject: 25ITC31 – Foundations of Data Science

---

**Institution:** Velalar College of Engineering and Technology, Erode – 638012  
**Department:** Information Technology  
**Academic Year:** 2026–2027 (ODD Semester)  
**Curriculum Regulations:** R2025  
**Scenario:** Scenario 1 – College Event Management and Participation Analysis System  
**Assigned Register Numbers:** 1, 31, 61, 91, L1  

---

## 1. Syllabus Unit-Wise Evaluation (25ITC31)

| Syllabus Unit & Topics | Scenario Requirements Addressed | Implementation Deliverable & Artifact Location |
|---|---|---|
| **UNIT 1: DATA SCIENCE FUNDAMENTALS & DATA DESCRIPTION**<br>Data Science Roadmap, Types of Data/Variables, Describing Data with Averages (Mean, Median, Mode), Variability (Std Dev, Variance), Normal Distributions, Standard ($z$) Scores | Computing baseline statistical measures on college event capacity, registrations, and attendance; evaluating z-scores for anomaly detection. | - [data_preprocessing.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_preprocessing.py) (Mean, Median, Std Dev, Variance, IQR, Z-scores).<br>- [Data_Science_Analysis_Report.md](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/Data_Science_Analysis_Report.md). |
| **UNIT 2: PYTHON FOUNDATIONS FOR DATA ANALYSIS**<br>Jupyter Notebook, Python fundamentals, Lists, Dicts, File handling (CSV files), Importing libraries (NumPy, Pandas, Matplotlib, Seaborn) | Loading relational CSV datasets exported by Java/MySQL; setting up interactive Jupyter Notebook. | - [data_retrieval.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_retrieval.py)<br>- [College_Event_Participation_Analysis.ipynb](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/College_Event_Participation_Analysis.ipynb). |
| **UNIT 3: DATA WRANGLING**<br>NumPy arrays, slicing, aggregations, boolean indexing; Pandas Series & DataFrames, combining datasets, groupby, aggregations | Aggregating attendance by event, computing capacity utilization, and combining student-event records. | - [data_preprocessing.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_preprocessing.py)<br>- `pd.merge()`, `groupby()`, `agg()`, boolean indexing for attendees. |
| **UNIT 4: DATA CLEANING & TRANSFORMATION TECHNIQUES**<br>Handling missing values, duplicate detection and removal, type conversion, outlier identification & treatment, feature creation | Cleaning null ratings, removing duplicate student-event pairs, computing Attendance Rate (%) and Capacity Utilization (%). | - [data_preprocessing.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_preprocessing.py)<br>- `drop_duplicates()`, `fillna()`, outlier filtering with IQR and $z > 3.0$. |
| **UNIT 5: EXPLORATORY DATA ANALYSIS (EDA) & VISUALIZATION**<br>Principles of effective visualization, Matplotlib (Line, Bar, Pie, Histogram), Seaborn (Box Plot, Heatmap, Pair Plot, Correlation analysis) | Generating 8 distinct publication-quality charts depicting participation trends, department share, and feedback distributions. | - [eda_and_visualization.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/eda_and_visualization.py)<br>- 8 PNG charts in [visualizations/](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/visualizations/):<br>1. Category Bar Chart<br>2. Utilization Grouped Bar Chart<br>3. Department Donut Chart<br>4. Rating Histogram with KDE<br>5. Monthly Trend Line Chart<br>6. Attendance Box Plot & Outliers<br>7. Correlation Heatmap<br>8. Multivariate Pair Plot |

---

## 2. Lab Experiments Alignment (Curriculum Lab Component)

- **Experiment 1 (Student Dataset Management):** Implemented in `data_retrieval.py` and `students.csv`.
- **Experiment 2 (Central Tendency, Variability & Z-Scores):** Implemented in `data_preprocessing.py` and `interactive_data_science.py` (Option 4 allows manual input of custom values to test Z-score formulas).
- **Experiment 3 (NumPy & Pandas Aggregations):** Implemented in `data_preprocessing.py` via `groupby("event_id").agg()`.
- **Experiment 4 (Data Cleaning, Duplicates & Outliers):** Implemented in `data_preprocessing.py` with IQR calculations and duplicate exclusion.
- **Experiment 5 (Exploration and Trend Visualizations):** Implemented in `eda_and_visualization.py` generating 8 distinct plots.

---

## 3. Interactive Execution & Output Deliverables

The Data Science subsystem includes an **interactive analytics suite** where users can manually provide inputs and inspect outputs:

1. **Double-Click Runnable Runner:** [run_data_science.bat](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/run_data_science.bat) (and [activity.bat](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/activity.bat))
2. **Interactive Program File:** [interactive_data_science.py](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/interactive_data_science.py)
   - View operational datasets
   - Compute descriptive statistics
   - Deep-dive into specific events by ID
   - Manually test test values for Z-score outlier detection
   - Filter participation by department
   - Generate specific visualization charts on demand
3. **Execution Output Text File:** [data_science_execution_output.txt](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_science_execution_output.txt)
4. **Execution Screenshot:** [data_science_execution_screenshot.png](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/data_science_execution_screenshot.png)
5. **Interactive Notebook:** [College_Event_Participation_Analysis.ipynb](file:///c:/CollegeManagement/04_Foundations_of_Data_Science_25ITC31/College_Event_Participation_Analysis.ipynb)

"""
===============================================================================
COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
Interactive Data Science CLI & Analytics Engine
Subject: 25ITC31 - Foundations of Data Science
Institution: Velalar College of Engineering and Technology (VCET)
===============================================================================
"""

import os
import sys
import numpy as np
import pandas as pd
from data_retrieval import load_raw_datasets
from data_preprocessing import preprocess_and_merge, print_summary_statistics
from eda_and_visualization import generate_visualizations, get_viz_dir

class DualLogger:
    """Tee logger to write to both stdout and text file simultaneously."""
    def __init__(self, filepath):
        self.terminal = sys.stdout
        self.logfile = open(filepath, "w", encoding="utf-8")

    def write(self, message):
        self.terminal.write(message)
        self.logfile.write(message)
        self.logfile.flush()

    def flush(self):
        self.terminal.flush()
        self.logfile.flush()

def interactive_cli():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    output_txt = os.path.join(base_dir, "data_science_execution_output.txt")
    sys.stdout = DualLogger(output_txt)

    print("================================================================================")
    print("       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12")
    print("              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)")
    print("     FOUNDATIONS OF DATA SCIENCE (25ITC31) - INTERACTIVE ANALYTICS SUITE")
    print("================================================================================")

    master_df, participants_df = preprocess_and_merge()

    running = True
    while running:
        print("\n--------------------------------------------------------------------------------")
        print("                         DATA SCIENCE INTERACTIVE MENU")
        print("--------------------------------------------------------------------------------")
        print(" [1] View Operational Datasets (Events / Students / Registrations / Feedback)")
        print(" [2] Compute Descriptive Statistics (Mean, Median, Std Dev, Variance, IQR)")
        print(" [3] Deep-Dive into Specific Event by ID (Calculate Turnout & Z-Score)")
        print(" [4] Test Custom Participant Value for Outlier Detection (Z-Score Model)")
        print(" [5] Filter Participation Records by Academic Department")
        print(" [6] Generate / View Specific Visualization Chart (Choose 1 to 8, or All)")
        print(" [7] Run Full Data Pipeline & Export All 8 PNG Visualizations")
        print(" [8] Exit")
        print("--------------------------------------------------------------------------------")

        choice = input("Enter your choice (1-8): ").strip()

        if choice == "1":
            print("\nSelect Dataset to View:")
            print("1. Events Master")
            print("2. Students Roster")
            print("3. Registrations & Attendance")
            print("4. Feedback & Ratings")
            sub = input("Choice (1-4): ").strip()
            if sub == "1":
                print("\n--- EVENTS MASTER DATASET ---")
                print(master_df[["event_id", "event_name", "category", "capacity", "total_registered", "total_attended"]].to_string(index=False))
            elif sub == "2":
                print("\n--- STUDENTS ROSTER (Sample) ---")
                students_df = pd.read_csv(os.path.join(base_dir, "data", "students.csv"))
                print(students_df.head(10).to_string(index=False))
            elif sub == "3":
                print("\n--- REGISTRATIONS & ATTENDANCE ---")
                regs_df = pd.read_csv(os.path.join(base_dir, "data", "registrations.csv"))
                print(regs_df.head(12).to_string(index=False))
            elif sub == "4":
                print("\n--- FEEDBACK RATINGS & REVIEWS ---")
                fb_df = pd.read_csv(os.path.join(base_dir, "data", "feedback.csv"))
                print(fb_df.to_string(index=False))
            else:
                print("[ERROR] Invalid selection.")

        elif choice == "2":
            print_summary_statistics(master_df)

        elif choice == "3":
            print("\nAvailable Events:")
            print(", ".join(master_df["event_id"].tolist()))
            evt_id = input("Enter Event ID to inspect (e.g., EVT-01): ").strip().upper()
            row = master_df[master_df["event_id"] == evt_id]
            if len(row) > 0:
                r = row.iloc[0]
                print(f"\n=== EVENT DETAILS FOR [{evt_id}] ===")
                print(f"Title               : {r['event_name']}")
                print(f"Category            : {r['category']}")
                print(f"Capacity            : {r['capacity']} seats")
                print(f"Total Registered    : {r['total_registered']}")
                print(f"Actual Attended     : {r['total_attended']}")
                print(f"Attendance Rate     : {r['attendance_rate_pct']}%")
                print(f"Capacity Utilization: {r['capacity_utilization_pct']}%")
                print(f"Average Rating      : {r['avg_rating']} / 5.0★ ({r['total_reviews']} reviews)")
                print(f"Calculated Z-Score  : {r['z_score_reg']:.3f} (Relative to mean registrations)")
                print("========================================")
            else:
                print(f"[ERROR] Event ID '{evt_id}' not found.")

        elif choice == "4":
            mean_reg = master_df["total_registered"].mean()
            std_reg = master_df["total_registered"].std()
            print(f"\nCurrent Event Registration Baseline: Mean = {mean_reg:.2f}, Std Dev = {std_reg:.2f}")
            val_input = input("Enter a test participant count to evaluate (e.g., 15, 25, 999): ").strip()
            try:
                test_val = float(val_input)
                z = (test_val - mean_reg) / std_reg if std_reg > 0 else 0
                print(f"\n--- OUTLIER EVALUATION (UNIT 1 & LAB EXP 2) ---")
                print(f"Test Input Value  : {test_val}")
                print(f"Calculated Z-Score: z = ({test_val} - {mean_reg:.2f}) / {std_reg:.2f} = {z:.4f}")
                if abs(z) > 3.0:
                    print(f"Status            : ⚠️ OUTLIER DETECTED! (|z| = {abs(z):.2f} > 3.0)")
                    print("Action            : Value lies in extreme critical region (>3 standard deviations from mean).")
                else:
                    print(f"Status            : ✅ NORMAL DATA POINT (|z| = {abs(z):.2f} <= 3.0)")
                    print("Action            : Value lies within expected academic distribution bounds.")
            except ValueError:
                print("[ERROR] Please enter a valid numerical value.")

        elif choice == "5":
            depts = participants_df["department"].dropna().unique()
            print("\nAcademic Departments:")
            for i, d in enumerate(depts, 1):
                print(f" {i}. {d}")
            dept_name = input("Enter Department name or keyword (e.g., IT, CSE, Information): ").strip()
            filtered = participants_df[participants_df["department"].str.contains(dept_name, case=False, na=False)]
            if len(filtered) > 0:
                print(f"\n--- PARTICIPANTS IN '{dept_name.upper()}' ({len(filtered)} records) ---")
                print(filtered[["student_id", "name", "event_id", "attendance_status", "rating"]].to_string(index=False))
            else:
                print(f"[INFO] No student records found matching '{dept_name}'.")

        elif choice == "6":
            viz_dir = get_viz_dir()
            print("\nSelect Visual Chart to Generate / Verify:")
            print(" 1. Category-Wise Participation & Turnout (Bar Chart)")
            print(" 2. Registration vs Capacity Utilization (Grouped Bar Chart)")
            print(" 3. Department Participation Share (Donut Chart)")
            print(" 4. Feedback Rating Distribution (Histogram with KDE)")
            print(" 5. Monthly Participation Trajectory (Time-Series Line Chart)")
            print(" 6. Attendance Rate Dispersion by Category (Seaborn Box Plot)")
            print(" 7. Inter-Variable Correlation Heatmap (Seaborn Heatmap)")
            print(" 8. Multivariate Pair Plot")
            print(" 9. Generate All 8 Visualizations")
            c_num = input("Enter chart number (1-9): ").strip()
            generate_visualizations()
            print(f"[SUCCESS] Charts are updated in: {viz_dir}")

        elif choice == "7":
            print("\nExecuting complete data preprocessing and visualization pipeline...")
            generate_visualizations()
            print("[SUCCESS] All 8 charts rendered and datasets refreshed.")

        elif choice == "8":
            print("\n[SYSTEM] Exiting Data Science Analytics Portal. Thank you!")
            running = False
        else:
            print("[ERROR] Invalid choice. Please enter a number from 1 to 8.")

if __name__ == "__main__":
    interactive_cli()

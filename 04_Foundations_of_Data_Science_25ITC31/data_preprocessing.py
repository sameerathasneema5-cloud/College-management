"""
===============================================================================
COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
Data Cleaning, Preprocessing and Transformation Pipeline
Subject: 25ITC31 - Foundations of Data Science (Unit 3, Unit 4 & Lab Exp 2, 4)
===============================================================================
"""

import os
import numpy as np
import pandas as pd
from data_retrieval import load_raw_datasets, get_data_paths

def preprocess_and_merge():
    events_df, students_df, regs_df, feedback_df = load_raw_datasets()
    print("\n[PREPROCESS] Starting data cleaning and transformation pipeline...")

    # 1. Handling Missing Values and Data Types
    events_df["event_date"] = pd.to_datetime(events_df["event_date"])
    events_df["capacity"] = events_df["capacity"].astype(int)
    events_df["registered_count"] = events_df["registered_count"].astype(int)

    # 2. Duplicate Check
    initial_regs = len(regs_df)
    regs_df = regs_df.drop_duplicates(subset=["student_id", "event_id"])
    print(f" -> Duplicate registrations removed: {initial_regs - len(regs_df)}")

    # 3. Create Binary Attendance Indicator
    regs_df["attended_flag"] = regs_df["attendance_status"].apply(
        lambda x: 1 if str(x).strip().upper() == "ATTENDED" else 0
    )

    # 4. Aggregate Feedback by Event
    avg_feedback = feedback_df.groupby("event_id").agg(
        avg_rating=("rating", "mean"),
        total_reviews=("rating", "count")
    ).reset_index()

    # 5. Merge Event Attendance Aggregations
    event_attendance = regs_df.groupby("event_id").agg(
        total_registered=("registration_id", "count"),
        total_attended=("attended_flag", "sum")
    ).reset_index()

    # 6. Merge with Event Master Dataset
    master_events = pd.merge(events_df, event_attendance, on="event_id", how="left")
    master_events["total_registered"] = master_events["total_registered"].fillna(0).astype(int)
    master_events["total_attended"] = master_events["total_attended"].fillna(0).astype(int)

    # 7. Feature Engineering
    master_events["attendance_rate_pct"] = np.where(
        master_events["total_registered"] > 0,
        np.round((master_events["total_attended"] / master_events["total_registered"]) * 100.0, 2),
        0.0
    )

    master_events["capacity_utilization_pct"] = np.round(
        (master_events["total_registered"] / master_events["capacity"]) * 100.0, 2
    )

    # Merge feedback ratings
    master_events = pd.merge(master_events, avg_feedback, on="event_id", how="left")
    master_events["avg_rating"] = master_events["avg_rating"].fillna(0.0).round(2)
    master_events["total_reviews"] = master_events["total_reviews"].fillna(0).astype(int)

    # 8. Outlier Detection using Standard (Z) Scores (Unit 1 & Lab Exp 2)
    mean_reg = master_events["total_registered"].mean()
    std_reg = master_events["total_registered"].std()
    if std_reg > 0:
        master_events["z_score_reg"] = (master_events["total_registered"] - mean_reg) / std_reg
    else:
        master_events["z_score_reg"] = 0.0

    # 9. Interquartile Range (IQR) for Attendance Rate
    q25 = master_events["attendance_rate_pct"].quantile(0.25)
    q75 = master_events["attendance_rate_pct"].quantile(0.75)
    iqr = q75 - q25
    print(f" -> Attendance Rate IQR: {iqr:.2f} (Q1: {q25:.2f}, Q3: {q75:.2f})")

    # 10. Merge Full Participant-Level Dataset for Department Analysis
    participant_level = pd.merge(regs_df, students_df, on="student_id", how="left")
    participant_level = pd.merge(participant_level, events_df[["event_id", "event_name", "category", "event_date"]], on="event_id", how="left")
    participant_level = pd.merge(participant_level, feedback_df[["event_id", "student_id", "rating"]], on=["event_id", "student_id"], how="left")

    # Save cleaned datasets
    paths = get_data_paths()
    master_events.to_csv(paths["output_master"], index=False)
    participant_level_path = os.path.join(os.path.dirname(paths["output_master"]), "cleaned_participant_level.csv")
    participant_level.to_csv(participant_level_path, index=False)

    print(f"\n[SUCCESS] Preprocessed datasets saved to:")
    print(f" -> {paths['output_master']}")
    print(f" -> {participant_level_path}")

    return master_events, participant_level

def print_summary_statistics(master_df):
    """
    Computes measures of central tendency and dispersion (Unit 1).
    """
    print("\n===============================================================================")
    print("                     DESCRIPTIVE STATISTICAL METRICS (UNIT 1)")
    print("===============================================================================")
    cols = ["capacity", "total_registered", "total_attended", "attendance_rate_pct", "avg_rating"]
    for col in cols:
        mean_val = master_df[col].mean()
        median_val = master_df[col].median()
        std_val = master_df[col].std()
        min_val = master_df[col].min()
        max_val = master_df[col].max()
        print(f"Metric: {col.upper():<25} | Mean: {mean_val:6.2f} | Median: {median_val:6.2f} | Std: {std_val:6.2f} | Min: {min_val:5.1f} | Max: {max_val:5.1f}")
    print("===============================================================================\n")

if __name__ == "__main__":
    master, participants = preprocess_and_merge()
    print_summary_statistics(master)

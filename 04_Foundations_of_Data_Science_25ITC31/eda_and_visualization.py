"""
===============================================================================
COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
Exploratory Data Analysis (EDA) and Visualization Suite
Subject: 25ITC31 - Foundations of Data Science (Unit 5 & Lab Exp 5)
Institution: Velalar College of Engineering and Technology (VCET)
===============================================================================
"""

import os
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import numpy as np
from data_preprocessing import preprocess_and_merge, print_summary_statistics

# Set aesthetic visual style
sns.set_theme(style="whitegrid", palette="muted")
plt.rcParams.update({
    "font.family": "sans-serif",
    "font.size": 11,
    "axes.titlesize": 14,
    "axes.titleweight": "bold",
    "axes.labelsize": 12,
    "axes.labelweight": "bold",
    "figure.titlesize": 16,
    "figure.titleweight": "bold"
})

def get_viz_dir():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    viz_dir = os.path.join(base_dir, "visualizations")
    os.makedirs(viz_dir, exist_ok=True)
    return viz_dir

def generate_visualizations():
    master_df, participants_df = preprocess_and_merge()
    print_summary_statistics(master_df)
    viz_dir = get_viz_dir()
    print("\n[VISUALIZATION] Rendering publication-quality charts...")

    # Filter completed events for meaningful analysis
    completed_events = master_df[master_df["total_registered"] > 0].copy()

    # -------------------------------------------------------------------------
    # CHART 1: Category-Wise Registrations vs Attendance (Bar Chart)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(10, 6))
    cat_summary = completed_events.groupby("category")[["total_registered", "total_attended"]].sum().reset_index()
    
    x = np.arange(len(cat_summary))
    width = 0.35
    
    rects1 = ax.bar(x - width/2, cat_summary["total_registered"], width, label="Total Registered", color="#2b5c8f")
    rects2 = ax.bar(x + width/2, cat_summary["total_attended"], width, label="Total Attended", color="#2ca02c")
    
    ax.set_ylabel("Number of Students")
    ax.set_title("Total Registrations vs Actual Attendance by Event Category")
    ax.set_xticks(x)
    ax.set_xticklabels(cat_summary["category"], rotation=15, ha="right")
    ax.legend(frameon=True)
    ax.bar_label(rects1, padding=3)
    ax.bar_label(rects2, padding=3)
    plt.tight_layout()
    chart1_path = os.path.join(viz_dir, "01_category_wise_participation.png")
    plt.savefig(chart1_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart1_path}")

    # -------------------------------------------------------------------------
    # CHART 2: Capacity Utilization vs Attendance Rate (Grouped Bar Chart)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(11, 6))
    x = np.arange(len(completed_events))
    width = 0.35

    r1 = ax.bar(x - width/2, completed_events["capacity_utilization_pct"], width, label="Capacity Utilization %", color="#ff7f0e")
    r2 = ax.bar(x + width/2, completed_events["attendance_rate_pct"], width, label="Attendance Rate %", color="#1f77b4")

    ax.set_ylabel("Percentage (%)")
    ax.set_title("Event Performance: Capacity Utilization vs Attendance Turnout")
    ax.set_xticks(x)
    ax.set_xticklabels(completed_events["event_id"], rotation=0)
    ax.set_ylim(0, 115)
    ax.axhline(80, color="red", linestyle="--", alpha=0.7, label="Target Turnout Threshold (80%)")
    ax.legend(loc="lower right", frameon=True)
    ax.bar_label(r1, fmt="%.1f%%", padding=3, fontsize=9)
    ax.bar_label(r2, fmt="%.1f%%", padding=3, fontsize=9)
    plt.tight_layout()
    chart2_path = os.path.join(viz_dir, "02_registration_vs_capacity_utilization.png")
    plt.savefig(chart2_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart2_path}")

    # -------------------------------------------------------------------------
    # CHART 3: Department-Wise Student Distribution (Donut / Pie Chart)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(8, 8))
    dept_counts = participants_df["department"].value_counts()
    colors = sns.color_palette("Set2", len(dept_counts))

    wedges, texts, autotexts = ax.pie(
        dept_counts, 
        labels=dept_counts.index, 
        autopct="%1.1f%%", 
        startangle=140, 
        colors=colors,
        pctdistance=0.8,
        wedgeprops=dict(width=0.4, edgecolor="white", linewidth=2)
    )
    plt.setp(autotexts, size=10, weight="bold")
    ax.set_title("Student Participation Share Across Academic Departments")
    plt.tight_layout()
    chart3_path = os.path.join(viz_dir, "03_department_wise_engagement.png")
    plt.savefig(chart3_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart3_path}")

    # -------------------------------------------------------------------------
    # CHART 4: Feedback Rating Distribution (Histogram with KDE)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(9, 5))
    ratings = participants_df["rating"].dropna()
    sns.histplot(ratings, bins=[1, 2, 3, 4, 5, 6], kde=True, discrete=True, color="#9467bd", ax=ax, shrink=0.8)
    ax.set_title("Distribution of Student Feedback Ratings (1 to 5 Stars)")
    ax.set_xlabel("Star Rating")
    ax.set_ylabel("Frequency of Student Reviews")
    ax.set_xticks([1, 2, 3, 4, 5])
    plt.tight_layout()
    chart4_path = os.path.join(viz_dir, "04_feedback_rating_distribution.png")
    plt.savefig(chart4_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart4_path}")

    # -------------------------------------------------------------------------
    # CHART 5: Monthly Participation Trend (Time Series Line Chart)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(10, 5))
    completed_events["month_year"] = completed_events["event_date"].dt.strftime("%Y-%m")
    monthly_trend = completed_events.groupby("month_year")[["total_registered", "total_attended"]].sum().reset_index()

    ax.plot(monthly_trend["month_year"], monthly_trend["total_registered"], marker="o", linewidth=2.5, label="Total Registrations", color="#1f77b4")
    ax.plot(monthly_trend["month_year"], monthly_trend["total_attended"], marker="s", linewidth=2.5, linestyle="--", label="Actual Attendance", color="#2ca02c")
    
    ax.set_title("Academic Year Participation Trajectory (2026-2027)")
    ax.set_xlabel("Event Month")
    ax.set_ylabel("Student Count")
    ax.legend(frameon=True)
    for i, row in monthly_trend.iterrows():
        ax.annotate(str(row["total_registered"]), (row["month_year"], row["total_registered"] + 0.3), ha="center")
        ax.annotate(str(row["total_attended"]), (row["month_year"], row["total_attended"] - 0.5), ha="center")
    plt.tight_layout()
    chart5_path = os.path.join(viz_dir, "05_monthly_event_trend.png")
    plt.savefig(chart5_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart5_path}")

    # -------------------------------------------------------------------------
    # CHART 6: Attendance Rate Dispersion by Category (Seaborn Box Plot)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(9, 5))
    sns.boxplot(x="category", y="attendance_rate_pct", data=completed_events, palette="pastel", ax=ax)
    sns.stripplot(x="category", y="attendance_rate_pct", data=completed_events, color="black", alpha=0.7, size=7, jitter=0.2, ax=ax)
    ax.set_title("Dispersion of Attendance Rate Across Event Types (Box Plot & Outliers)")
    ax.set_xlabel("Category")
    ax.set_ylabel("Attendance Rate (%)")
    ax.set_xticklabels(ax.get_xticklabels(), rotation=15, ha="right")
    plt.tight_layout()
    chart6_path = os.path.join(viz_dir, "06_attendance_rate_boxplot.png")
    plt.savefig(chart6_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart6_path}")

    # -------------------------------------------------------------------------
    # CHART 7: Correlation Heatmap (Seaborn Heatmap)
    # -------------------------------------------------------------------------
    fig, ax = plt.subplots(figsize=(8, 6))
    corr_cols = ["capacity", "total_registered", "total_attended", "attendance_rate_pct", "avg_rating"]
    corr_matrix = completed_events[corr_cols].corr()

    sns.heatmap(corr_matrix, annot=True, cmap="Blues", fmt=".2f", linewidths=1.2, linecolor="white", ax=ax)
    ax.set_title("Inter-Variable Correlation Heatmap (Pearson r)")
    plt.tight_layout()
    chart7_path = os.path.join(viz_dir, "07_correlation_heatmap.png")
    plt.savefig(chart7_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart7_path}")

    # -------------------------------------------------------------------------
    # CHART 8: Pair Plot for Multi-Variable Dynamics
    # -------------------------------------------------------------------------
    pair_cols = ["total_registered", "total_attended", "attendance_rate_pct", "category"]
    pair_grid = sns.pairplot(completed_events[pair_cols], hue="category", palette="Dark2", corner=False, diag_kind="kde")
    pair_grid.fig.suptitle("Multivariate Pair Plot: Event Registrations vs Turnout", y=1.02)
    chart8_path = os.path.join(viz_dir, "08_pairplot_multivariate.png")
    pair_grid.savefig(chart8_path, dpi=300)
    plt.close()
    print(f" -> Saved: {chart8_path}")

    print("\n[SUCCESS] All 8 publication-ready charts successfully generated!")

if __name__ == "__main__":
    generate_visualizations()

"""
===============================================================================
COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
Database Engine & SQL Query Executor
Subject: 25ITT31 - Database Management Systems
Institution: Velalar College of Engineering and Technology (VCET)
===============================================================================
"""

import os
import sqlite3
import textwrap

def execute_dbms_pipeline():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    db_file = os.path.join(base_dir, "college_events.db")
    schema_file = os.path.join(base_dir, "database_schema.sql")
    data_file = os.path.join(base_dir, "sample_data.sql")
    queries_file = os.path.join(base_dir, "analytical_queries.sql")
    output_text_file = os.path.join(base_dir, "dbms_execution_output.txt")

    if os.path.exists(db_file):
        os.remove(db_file)

    conn = sqlite3.connect(db_file)
    cursor = conn.cursor()

    log_lines = []
    def log(msg=""):
        print(msg)
        log_lines.append(msg)

    log("================================================================================")
    log("     VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12")
    log("            DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)")
    log("     DATABASE MANAGEMENT SYSTEMS (25ITT31) - SQL EXECUTION ENGINE")
    log("================================================================================")
    log(f"Database Engine: SQLite {sqlite3.sqlite_version} (ACID Compliant)")
    log(f"Database File: {os.path.basename(db_file)}")
    log("Status: Initializing Database Schema (DDL)...")

    # 1. Execute Schema
    with open(schema_file, "r", encoding="utf-8") as f:
        schema_sql = f.read()
    cursor.executescript(schema_sql)
    log("[SUCCESS] Database Schema, Indexes and Views initialized successfully.")

    # 2. Insert Sample Data
    log("\nStatus: Inserting Seed Dataset (DML)...")
    with open(data_file, "r", encoding="utf-8") as f:
        data_sql = f.read()
    cursor.executescript(data_sql)
    conn.commit()

    # Verify tables
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;")
    tables = [r[0] for r in cursor.fetchall() if not r[0].startswith('sqlite_')]
    log(f"[SUCCESS] Tables Created: {', '.join(tables)}")

    # 3. Read and execute analytical queries
    log("\n================================================================================")
    log("                   EXECUTING 15 ANALYTICAL & REPORTING QUERIES")
    log("================================================================================")

    with open(queries_file, "r", encoding="utf-8") as f:
        raw_queries = f.read()

    query_blocks = raw_queries.split(";")
    q_counter = 0

    query_titles = [
        "Query 1: Active Upcoming Events (Selection & Projection)",
        "Query 2: Multi-Table Inner Join - Detailed Registration Audit Log",
        "Query 3: Left Outer Join - Capacity & Registration Gap Analysis",
        "Query 4: Aggregation with GROUP BY & HAVING - Turnout >= 85%",
        "Query 5: Nested Subquery with IN - Students in Technical Symposiums",
        "Query 6: Correlated Subquery with EXISTS - Events with Student Feedback",
        "Query 7: Subquery with NOT EXISTS - Absentee List",
        "Query 8: Department-wise Participation Distribution",
        "Query 9: Window Function (DENSE_RANK) - Ranking Events by Volume",
        "Query 10: Window Function (ROW_NUMBER) - Chronological Queue Order",
        "Query 11: Top-Rated Events by Average Student Satisfaction",
        "Query 12: Cross-Departmental Diversity (Events with >= 3 Unique Depts)",
        "Query 13: Active Star Participants (Students with >= 3 Events)",
        "Query 14: Comprehensive Executive Summary View (v_event_summary)",
        "Query 15: Department Engagement Comparative View (v_department_engagement)"
    ]

    for block in query_blocks:
        lines = [l for l in block.splitlines() if not l.strip().startswith("--")]
        clean_sql = " ".join(lines).strip()
        if not clean_sql:
            continue

        q_counter += 1
        title = query_titles[q_counter - 1] if q_counter <= len(query_titles) else f"Query {q_counter}"

        log(f"\n--- [{title}] ---")
        log(f"SQL: {clean_sql[:110]}...")

        cursor.execute(clean_sql)
        headers = [d[0] for d in cursor.description]
        rows = cursor.fetchall()

        # Format as neat text table
        col_widths = [max(len(h), max((len(str(r[i])) for r in rows), default=0)) for i, h in enumerate(headers)]
        header_row = " | ".join(h.ljust(col_widths[i]) for i, h in enumerate(headers))
        separator = "-+-".join("-" * col_widths[i] for i in range(len(headers)))

        log(header_row)
        log(separator)
        # Limit rows displayed in text for very long queries (e.g. limit to 8 for display, note total)
        display_rows = rows[:8]
        for r in display_rows:
            log(" | ".join(str(r[i]).ljust(col_widths[i]) for i in range(len(headers))))
        if len(rows) > 8:
            log(f"... and {len(rows) - 8} more rows (Total: {len(rows)} rows)")
        log(f"[Result: {len(rows)} row(s) returned]")

    log("\n================================================================================")
    log("      ALL 15 SQL QUERIES EXECUTED SUCCESSFULLY - DATABASE ENGINE READY")
    log("================================================================================")

    # Save to output text file
    with open(output_text_file, "w", encoding="utf-8") as f:
        f.write("\n".join(log_lines))
    print(f"\n[FILE SAVED] Output text file saved to: {output_text_file}")

    conn.close()
    return log_lines

if __name__ == "__main__":
    execute_dbms_pipeline()

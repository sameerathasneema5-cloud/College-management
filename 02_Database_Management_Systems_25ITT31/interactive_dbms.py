"""
===============================================================================
COLLEGE EVENT MANAGEMENT AND PARTICIPATION ANALYSIS SYSTEM
Interactive SQL Shell & Database Query Engine
Subject: 25ITT31 - Database Management Systems
Institution: Velalar College of Engineering and Technology (VCET)
===============================================================================
"""

import os
import sys
import sqlite3

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

def format_ascii_table(headers, rows, max_rows=15):
    if not headers:
        return "No headers available."
    col_widths = [max(len(h), max((len(str(r[i])) for r in rows), default=0)) for i, h in enumerate(headers)]
    header_row = " | ".join(h.ljust(col_widths[i]) for i, h in enumerate(headers))
    separator = "-+-".join("-" * col_widths[i] for i in range(len(headers)))
    lines = [header_row, separator]
    for r in rows[:max_rows]:
        lines.append(" | ".join(str(r[i]).ljust(col_widths[i]) for i in range(len(headers))))
    if len(rows) > max_rows:
        lines.append(f"... and {len(rows) - max_rows} more row(s) (Total: {len(rows)})")
    return "\n".join(lines)

def init_db(db_file, schema_file, data_file):
    conn = sqlite3.connect(db_file)
    cursor = conn.cursor()
    with open(schema_file, "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    with open(data_file, "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    conn.commit()
    conn.close()

def interactive_dbms():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    db_file = os.path.join(base_dir, "college_events.db")
    schema_file = os.path.join(base_dir, "database_schema.sql")
    data_file = os.path.join(base_dir, "sample_data.sql")
    queries_file = os.path.join(base_dir, "analytical_queries.sql")
    output_txt = os.path.join(base_dir, "dbms_execution_output.txt")

    sys.stdout = DualLogger(output_txt)

    # Initialize if DB missing
    if not os.path.exists(db_file):
        init_db(db_file, schema_file, data_file)

    conn = sqlite3.connect(db_file)
    cursor = conn.cursor()

    print("================================================================================")
    print("       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12")
    print("              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)")
    print("     DATABASE MANAGEMENT SYSTEMS (25ITT31) - INTERACTIVE SQL ENGINE")
    print("================================================================================")
    print(f"Database Engine : SQLite {sqlite3.sqlite_version} (Full ACID Compliance)")
    print(f"Connected DB    : {os.path.basename(db_file)}")

    # Load 15 analytical queries
    with open(queries_file, "r", encoding="utf-8") as f:
        raw = f.read().split(";")
    query_list = []
    for stmt in raw:
        lines = [l for l in stmt.splitlines() if not l.strip().startswith("--")]
        clean = " ".join(lines).strip()
        if clean:
            query_list.append(clean)

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

    running = True
    while running:
        print("\n--------------------------------------------------------------------------------")
        print("                       DATABASE MANAGEMENT INTERACTIVE MENU")
        print("--------------------------------------------------------------------------------")
        print(" [1] View Database Tables & Schema (DDL)")
        print(" [2] Browse Table Records (events / students / registrations / feedback / etc.)")
        print(" [3] Run Analytical Query by Number (Select Query 1 to 15)")
        print(" [4] Custom Interactive SQL Query Prompt (Type your own SQL statement)")
        print(" [5] Register Student for Event Manually (Enforces Duplicate & Capacity Checks)")
        print(" [6] Execute All 15 Analytical Queries in Batch Mode")
        print(" [7] Re-seed Database from schema.sql & sample_data.sql")
        print(" [8] Exit")
        print("--------------------------------------------------------------------------------")

        choice = input("Enter your choice (1-8): ").strip()

        if choice == "1":
            print("\n=== DATABASE TABLES & VIEWS ===")
            cursor.execute("SELECT type, name FROM sqlite_master WHERE type IN ('table', 'view') AND name NOT LIKE 'sqlite_%' ORDER BY type, name;")
            for obj_type, name in cursor.fetchall():
                cursor.execute(f"SELECT COUNT(*) FROM {name};")
                count = cursor.fetchone()[0]
                print(f" - [{obj_type.upper():5s}] {name:<32s} (Row Count: {count})")

        elif choice == "2":
            tbl = input("\nEnter table or view name (e.g. events, students, registrations, v_event_summary): ").strip()
            try:
                cursor.execute(f"SELECT * FROM {tbl};")
                headers = [d[0] for d in cursor.description]
                rows = cursor.fetchall()
                print(f"\n--- CONTENTS OF [{tbl.upper()}] ({len(rows)} rows) ---")
                print(format_ascii_table(headers, rows, max_rows=15))
            except sqlite3.OperationalError as e:
                print(f"[SQL ERROR] {e}")

        elif choice == "3":
            print("\nSelect Analytical Query to Execute:")
            for i, t in enumerate(query_titles, 1):
                print(f" {i:2d}. {t}")
            q_idx_str = input("\nEnter query number (1-15): ").strip()
            try:
                q_idx = int(q_idx_str) - 1
                if 0 <= q_idx < len(query_list):
                    sql = query_list[q_idx]
                    print(f"\n--- [{query_titles[q_idx]}] ---")
                    print(f"SQL: {sql}")
                    cursor.execute(sql)
                    headers = [d[0] for d in cursor.description]
                    rows = cursor.fetchall()
                    print(format_ascii_table(headers, rows, max_rows=15))
                    print(f"[Query Result: {len(rows)} row(s) returned]")
                else:
                    print("[ERROR] Query number must be between 1 and 15.")
            except ValueError:
                print("[ERROR] Please enter a valid number.")

        elif choice == "4":
            print("\n=== CUSTOM SQL PROMPT ===")
            print("Type your SQL query (e.g. SELECT * FROM events WHERE capacity > 50;)")
            print("Type 'back' to return to menu.")
            while True:
                user_sql = input("\nSQL> ").strip()
                if user_sql.lower() in ("back", "exit", "quit"):
                    break
                if not user_sql:
                    continue
                try:
                    cursor.execute(user_sql)
                    if cursor.description:
                        headers = [d[0] for d in cursor.description]
                        rows = cursor.fetchall()
                        print(format_ascii_table(headers, rows, max_rows=20))
                        print(f"[{len(rows)} row(s) returned]")
                    else:
                        conn.commit()
                        print(f"[SUCCESS] Command executed ({cursor.rowcount} row(s) affected).")
                except sqlite3.Error as e:
                    print(f"[SQL ERROR] {e}")

        elif choice == "5":
            print("\n--- MANUAL STUDENT REGISTRATION (ENFORCING FR-03 IN SQL) ---")
            s_id = input("Enter Student Register No (e.g. 25IT001, 25IT031, 25CS012): ").strip().upper()
            e_id = input("Enter Event ID (e.g. EVT-01, EVT-02, EVT-07, EVT-08): ").strip().upper()

            # 1. Verify student
            cursor.execute("SELECT name, department FROM students WHERE student_id = ?;", (s_id,))
            student_row = cursor.fetchone()
            if not student_row:
                print(f"[REJECTED] Student ID '{s_id}' does not exist in students table.")
                continue

            # 2. Verify event and check capacity
            cursor.execute("SELECT event_name, capacity, status FROM events WHERE event_id = ?;", (e_id,))
            event_row = cursor.fetchone()
            if not event_row:
                print(f"[REJECTED] Event ID '{e_id}' does not exist in events table.")
                continue

            event_name, capacity, status = event_row
            if status != "ACTIVE":
                print(f"[REJECTED] Event '{event_name}' is not currently ACTIVE (Status: {status}).")
                continue

            # 3. Check duplicate registration (FR-03)
            cursor.execute("SELECT registration_id FROM registrations WHERE student_id = ? AND event_id = ? AND status != 'CANCELLED';", (s_id, e_id))
            dup = cursor.fetchone()
            if dup:
                print(f"[REJECTED - FR-03] DUPLICATE REGISTRATION! Student {s_id} is already registered under Registration ID {dup[0]}.")
                continue

            # 4. Check capacity limit (FR-03)
            cursor.execute("SELECT COUNT(*) FROM registrations WHERE event_id = ? AND status = 'CONFIRMED';", (e_id,))
            current_reg = cursor.fetchone()[0]
            if current_reg >= capacity:
                print(f"[REJECTED - FR-03] CAPACITY EXCEEDED! Event '{event_name}' is fully booked ({current_reg}/{capacity} seats filled).")
                continue

            # 5. Insert registration
            cursor.execute("SELECT COUNT(*) FROM registrations;")
            new_reg_id = f"REG-{cursor.fetchone()[0] + 1:03d}"
            cursor.execute("INSERT INTO registrations (registration_id, student_id, event_id, status) VALUES (?, ?, ?, 'CONFIRMED');", (new_reg_id, s_id, e_id))
            conn.commit()
            print(f"[SUCCESS - CONFIRMED] Registered student {student_row[0]} ({s_id}) for '{event_name}' under Registration ID: {new_reg_id} (Seat {current_reg + 1}/{capacity}).")

        elif choice == "6":
            print("\nExecuting all 15 Analytical Queries in batch mode...")
            for i, (title, sql) in enumerate(zip(query_titles, query_list), 1):
                print(f"\n--- [{title}] ---")
                cursor.execute(sql)
                headers = [d[0] for d in cursor.description]
                rows = cursor.fetchall()
                print(format_ascii_table(headers, rows, max_rows=8))
                print(f"[Result: {len(rows)} row(s)]")
            print("\n[SUCCESS] Batch execution of all 15 queries completed.")

        elif choice == "7":
            print("\nResetting and re-seeding database from schema.sql and sample_data.sql...")
            init_db(db_file, schema_file, data_file)
            conn = sqlite3.connect(db_file)
            cursor = conn.cursor()
            print("[SUCCESS] Database reset and re-seeded successfully.")

        elif choice == "8":
            print("\n[SYSTEM] Exiting Database Management System. Thank you!")
            running = False
        else:
            print("[ERROR] Invalid choice. Please enter a number between 1 and 8.")

    conn.close()

if __name__ == "__main__":
    interactive_dbms()

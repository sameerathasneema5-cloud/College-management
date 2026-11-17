@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System
REM Database Management Systems (25ITT31) - SQL Execution Runner
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

title DBMS (25ITT31) - College Event Management SQL Runner
color 0B
echo.
echo ================================================================================
echo       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12
echo              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)
echo              DATABASE MANAGEMENT SYSTEMS (25ITT31) SQL RUNNER
echo ================================================================================
echo.
echo Initializing Database, executing DDL schema, inserting sample records,
echo and running 15 Analytical SQL Queries...
echo.

python execute_dbms.py

echo.
echo ================================================================================
echo Execution completed! The output has been saved to 'dbms_execution_output.txt'.
echo ================================================================================
echo.
pause

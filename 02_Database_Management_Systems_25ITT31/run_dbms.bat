@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System
REM Database Management Systems (25ITT31) - Interactive SQL Runner (.bat)
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

title DBMS (25ITT31) - Interactive SQL Shell & Query Engine
color 0B

echo.
echo ================================================================================
echo       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12
echo              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)
echo              DATABASE MANAGEMENT SYSTEMS (25ITT31) SQL RUNNER
echo ================================================================================
echo.
echo Launching Interactive Database Engine...
echo You can manually browse tables, execute analytical queries, type custom SQL,
echo and test duplicate / capacity constraints interactively!
echo.

python -u interactive_dbms.py

echo.
echo ================================================================================
echo Database session closed. Output transcript saved to 'dbms_execution_output.txt'.
echo ================================================================================
echo.
pause

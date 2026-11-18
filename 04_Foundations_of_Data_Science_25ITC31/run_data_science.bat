@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System
REM Foundations of Data Science (25ITC31) - Interactive Analytics Runner (.bat)
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

title Data Science (25ITC31) - Interactive Analytics & Visualization Runner
color 0E

echo.
echo ================================================================================
echo       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12
echo              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)
echo         FOUNDATIONS OF DATA SCIENCE (25ITC31) INTERACTIVE RUNNER
echo ================================================================================
echo.
echo Launching Interactive Data Science Analytics Suite...
echo You can manually inspect statistics, filter departments, test Z-scores,
echo and generate visualizations on demand!
echo.

python -u interactive_data_science.py

echo.
echo ================================================================================
echo Session concluded. Output transcript saved to 'data_science_execution_output.txt'.
echo All 8 Visual Charts available in 'visualizations/' folder.
echo ================================================================================
echo.
pause

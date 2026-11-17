@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System
REM Foundations of Data Science (25ITC31) - Analytics & Visualization Runner (.bat)
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

title Data Science (25ITC31) - Participation Analytics Runner
color 0E

echo.
echo ================================================================================
echo       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12
echo              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)
echo         FOUNDATIONS OF DATA SCIENCE (25ITC31) PIPELINE RUNNER
echo ================================================================================
echo.
echo [1/3] Running Data Retrieval and Preprocessing Pipeline...
echo [2/3] Computing Central Tendency, Dispersion, IQR and Z-Scores...
echo [3/3] Generating 8 Publication-Ready Matplotlib & Seaborn Visualizations...
echo.

python -u eda_and_visualization.py > data_science_execution_output.txt 2>&1
type data_science_execution_output.txt

echo.
echo ================================================================================
echo Execution completed! Output saved to 'data_science_execution_output.txt'.
echo All 8 Visual Charts saved to 'visualizations/' folder.
echo ================================================================================
echo.
pause

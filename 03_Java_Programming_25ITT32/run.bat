@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System (Java Runner)
REM Course Code: 25ITT32 - Java Programming
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

title Java Programming (25ITT32) - College Event Management Portal
color 0A

echo.
echo ================================================================================
echo       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12
echo              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)
echo              JAVA PROGRAMMING (25ITT32) INTERACTIVE RUNNER
echo ================================================================================
echo.

if not exist "bin" mkdir "bin"

echo [1/2] Compiling Java source files...
dir /s /b src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed.
    pause
    exit /b %ERRORLEVEL%
)

echo [2/2] Launching Interactive Application...
java -cp bin com.college.eventmgmt.Main %*
echo.
pause

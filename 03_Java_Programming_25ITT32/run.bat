@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System (Java Runner)
REM Course Code: 25ITT32 - Java Programming
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

echo [BUILD] Compiling Java source files...
if not exist "bin" mkdir "bin"

dir /s /b src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed.
    pause
    exit /b %ERRORLEVEL%
)

echo [RUN] Launching College Event Management System...
java -cp bin com.college.eventmgmt.Main %*
pause

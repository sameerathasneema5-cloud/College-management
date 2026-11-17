@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System
REM Java Programming (25ITT32) - Application Runner (.bat)
REM Institution: Velalar College of Engineering and Technology (VCET)
REM ============================================================================

title Java Programming (25ITT32) - College Event Management
color 0A

echo.
echo ================================================================================
echo       VELALAR COLLEGE OF ENGINEERING AND TECHNOLOGY (VCET), ERODE-12
echo              DEPARTMENT OF INFORMATION TECHNOLOGY (2026-2027)
echo              JAVA PROGRAMMING (25ITT32) APPLICATION RUNNER
echo ================================================================================
echo.

if not exist "bin" mkdir "bin"

echo [1/3] Compiling Java source files with OpenJDK 21...
dir /s /b src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo [2/3] Java build successful.
echo.
echo [3/3] Executing College Event Management Application & Test Suite...
echo.

REM Run and display on console while saving to java_execution_output.txt
java -cp bin com.college.eventmgmt.Main --demo > java_execution_output.txt 2>&1
type java_execution_output.txt

echo.
echo ================================================================================
echo Execution completed! The output has been saved to 'java_execution_output.txt'.
echo ================================================================================
echo.
pause

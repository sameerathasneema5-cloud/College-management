@echo off
REM ============================================================================
REM College Event Management and Participation Analysis System
REM Java Programming (25ITT32) - Interactive Application Runner (.bat)
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

echo [1/2] Compiling Java source files with OpenJDK 21...
dir /s /b src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo [2/2] Launching Interactive Application...
echo.
echo You can now manually navigate through menus, register for events,
echo test duplicate/capacity limits, add events, submit feedback, and view analytics!
echo.

REM Launch interactive application directly with keyboard input enabled
java -cp bin com.college.eventmgmt.Main %*

echo.
echo ================================================================================
echo Application closed. Output transcript saved to 'java_execution_output.txt'.
echo ================================================================================
echo.
pause

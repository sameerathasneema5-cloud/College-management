@echo off
if not exist "bin" mkdir bin
javac -d bin -sourcepath src src\com\college\eventmgmt\*.java
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed.
    exit /b %ERRORLEVEL%
)
java -cp bin com.college.eventmgmt.Main %*
if "%~1"=="" pause

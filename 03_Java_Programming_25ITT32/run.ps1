# ============================================================================
# College Event Management and Participation Analysis System (PowerShell Runner)
# Course Code: 25ITT32 - Java Programming
# ============================================================================

Write-Host "[BUILD] Compiling Java source files..." -ForegroundColor Cyan
if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

$javaFiles = Get-ChildItem -Recurse -Filter *.java src | Select-Object -ExpandProperty FullName
javac -d bin $javaFiles

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Java compilation failed." -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "[RUN] Launching College Event Management System..." -ForegroundColor Green
java -cp bin com.college.eventmgmt.Main $args

@echo off
title GestionEmpresas - EJECUTAR
color 0A
chcp 65001 >nul

:: Configurar Java
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.10
set Path=%JAVA_HOME%\bin;%Path%

echo ========================================
echo    EJECUTANDO GESTION EMPRESAS
echo ========================================
echo.

:: Compilar
echo Compilando proyecto con código centrado...
javac -d bin src/main/Main.java src/modelos/*.java src/operaciones/*.java

if %errorlevel% neq 0 (
    echo ❌ Error: No se pudo compilar
    pause
    exit /b
)

echo ✅ Compilación exitosa
echo.
echo Iniciando aplicación centrada...
echo.
java -cp bin main.Main

pause
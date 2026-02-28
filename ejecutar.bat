@echo off
title GestionEmpresas
color 0A

:: Configurar Java
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.10
set Path=%JAVA_HOME%\bin;%Path%

echo ========================================
echo    COMPILANDO GESTION EMPRESAS
echo ========================================
echo.

:: Limpiar compilación anterior
if exist bin rmdir /s /q bin
mkdir bin

:: Compilar
javac -d bin src/main/Main.java src/modelos/*.java src/operaciones/*.java

if %errorlevel% equ 0 (
    cls
    echo ========================================
    echo    EJECUTANDO GESTION EMPRESAS
    echo ========================================
    echo.
    java -cp bin main.Main
) else (
    echo.
    echo ERROR: No se pudo compilar
    pause
)
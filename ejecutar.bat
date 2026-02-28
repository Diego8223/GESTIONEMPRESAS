@echo off
title GestionEmpresas - EJECUTAR
color 0A

:: Configurar Java
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.10
set Path=%JAVA_HOME%\bin;%Path%

echo ========================================
echo    EJECUTANDO GESTION EMPRESAS
echo ========================================
echo.

:: Compilar si es necesario
if not exist "bin" (
    echo Compilando proyecto...
    dir /s /b src\*.java > sources.txt
    javac -d bin @sources.txt
    del sources.txt 2>nul
)

:: Verificar que compiló
if not exist "bin\main\Main.class" (
    echo ❌ Error: No se pudo compilar
    pause
    exit /b
)

:: Ejecutar el programa
echo Iniciando aplicación...
echo.
java -cp bin main.Main

pause
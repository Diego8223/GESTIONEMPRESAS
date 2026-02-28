@echo off
title GestionEmpresas - Java 21
color 0A

echo ========================================
echo    GESTION EMPRESAS - PROYECTO JAVA
echo ========================================
echo.

:: Configurar Java manualmente (USANDO TU RUTA ESPECÍFICA)
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.10
set Path=%JAVA_HOME%\bin;%Path%

:: Verificar que Java funciona
echo ✅ Configurando Java...
java -version >nul 2>nul

if %errorlevel% neq 0 (
    echo ❌ ERROR: No se pudo configurar Java
    echo.
    echo Verifica que Java esté instalado en:
    echo C:\Program Files\Java\jdk-21.0.10
    echo.
    pause
    exit /b
)

:: Mostrar versión de Java
echo ✅ Java configurado correctamente:
java -version
echo.

:: Crear carpeta bin si no existe
if not exist bin mkdir bin

:: Compilar el proyecto
echo 📦 Compilando proyecto...
javac -d bin src/**/*.java 2>nul

if %errorlevel% equ 0 (
    echo ✅ Compilación exitosa!
    echo.
    echo ========================================
    echo    PROYECTO LISTO PARA EJECUTAR
    echo ========================================
    echo.
    echo Comandos disponibles:
    echo ----------------------------------------
    echo 1. Ejecutar programa: java -cp bin main.Main
    echo 2. Abrir VS Code: code .
    echo 3. Salir: exit
    echo ----------------------------------------
    echo.
    cmd /k
) else (
    echo ❌ Error en la compilación
    echo Revisa los archivos Java
    pause
)
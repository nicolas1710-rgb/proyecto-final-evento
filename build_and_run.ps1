# Script de Gestión para TicketFlow
# Configura el entorno para usar JDK 17 y ofrece opciones de compilación y ejecución

$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path

function Show-Menu {
    Clear-Host
    Write-Host "==============================" -ForegroundColor Cyan
    Write-Host "   TicketFlow - Gestión JDK" -ForegroundColor Cyan
    Write-Host "==============================" -ForegroundColor Cyan
    Write-Host "Java detectado:" -ForegroundColor Gray
    java -version 2>&1 | Select-String "version"

    Write-Host "`n¿Qué deseas hacer?" -ForegroundColor White
    Write-Host "1. Limpiar y Compilar (mvn clean compile)"
    Write-Host "2. Ejecutar Aplicación (mvn javafx:run)"
    Write-Host "3. Compilar y Ejecutar todo"
    Write-Host "4. Reparar Caché de Maven y Forzar Descarga (Intenta solucionar errores de plugins)"
    Write-Host "5. Salir"
}

while ($true) {
    Show-Menu
    $opcion = Read-Host "`nSelecciona una opción (1-5)"

    switch ($opcion) {
        "1" {
            Write-Host "`n--- Compilando Proyecto ---" -ForegroundColor Cyan
            mvn clean compile
        }
        "2" {
            Write-Host "`n--- Iniciando Aplicación ---" -ForegroundColor Cyan
            mvn javafx:run
        }
        "3" {
            Write-Host "`n--- Compilando Proyecto ---" -ForegroundColor Cyan
            mvn clean compile
            if ($LASTEXITCODE -eq 0) {
                Write-Host "`n--- Iniciando Aplicación ---" -ForegroundColor Cyan
                mvn javafx:run
            }
        }
        "4" {
            Write-Host "`n--- Limpiando archivos corruptos de Maven ---" -ForegroundColor Yellow
            # Eliminar archivos .lastUpdated que suelen causar el error "Failed to read artifact descriptor"
            Get-ChildItem -Path "$HOME\.m2\repository" -Filter "*.lastUpdated" -Recurse | Remove-Item -ErrorAction SilentlyContinue
            Write-Host "--- Forzando actualización de dependencias y plugins ---" -ForegroundColor Cyan
            mvn clean compile -U
            Write-Host "`nIntenta ejecutar la opción 2 o 3 ahora." -ForegroundColor Green
        }
        "5" { exit }
        default { Write-Host "Opción no válida." -ForegroundColor Red }
    }

    Write-Host "`nPresiona cualquier tecla para volver al menú..." -ForegroundColor Gray
    $null = [System.Console]::ReadKey($true)
}

package main;

import modelos.*;
import operaciones.*;
import java.util.Scanner;

public class Main {
    private static OperacionEmpresa operacionEmpresa = new OperacionEmpresa();
    private static OperacionEmpleado operacionEmpleado = new OperacionEmpleado();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuCentrado();
            opcion = leerOpcion();
            ejecutarOpcion(opcion);
            
            // Pausa para que el usuario vea los resultados
            if (opcion != 10 && opcion != 0) {
                System.out.println("\n" + " ".repeat(35) + "Presione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 10);
        
        scanner.close();
    }

    private static void mostrarMenuCentrado() {
        // Limpiar pantalla
        for (int i = 0; i < 30; i++) System.out.println();
        
        String espacios = "                                   "; // 35 espacios
        
        System.out.println(espacios + "╔════════════════════════════════════════════════════╗");
        System.out.println(espacios + "║         SISTEMA DE GESTIÓN DE EMPRESAS            ║");
        System.out.println(espacios + "║               Y EMPLEADOS                          ║");
        System.out.println(espacios + "╠════════════════════════════════════════════════════╣");
        System.out.println(espacios + "║    1. 🏢 Agregar Empresa                           ║");
        System.out.println(espacios + "║    2. 📋 Listar Empresas                           ║");
        System.out.println(espacios + "║    3. 🔍 Buscar Empresa por NIT                    ║");
        System.out.println(espacios + "║    4. 👤 Agregar Empleado                          ║");
        System.out.println(espacios + "║    5. 📋 Listar Empleados                          ║");
        System.out.println(espacios + "║    6. 🔍 Buscar Empleado por Documento             ║");
        System.out.println(espacios + "║    7. 💰 Calcular Sueldo                           ║");
        System.out.println(espacios + "║    8. 📊 Contar empleados x empresa                ║");
        System.out.println(espacios + "║    9. 📋 Listar empleados x empresa                ║");
        System.out.println(espacios + "║   10. ❌ Salir                                      ║");
        System.out.println(espacios + "╚════════════════════════════════════════════════════╝");
        System.out.print(espacios + "   👉 Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion) {
        String espacios = "                                   "; // 35 espacios
        
        switch (opcion) {
            case 1:
                agregarEmpresa();
                break;
            case 2:
                System.out.println(espacios + "══════════════════════════════════════════════");
                System.out.println(espacios + "         LISTA DE EMPRESAS");
                System.out.println(espacios + "══════════════════════════════════════════════");
                operacionEmpresa.listarTodasEmpresas();
                break;
            case 3:
                buscarEmpresaPorNit();
                break;
            case 4:
                agregarEmpleado();
                break;
            case 5:
                System.out.println(espacios + "══════════════════════════════════════════════");
                System.out.println(espacios + "         LISTA DE EMPLEADOS");
                System.out.println(espacios + "══════════════════════════════════════════════");
                operacionEmpleado.listarTodosEmpleados();
                break;
            case 6:
                buscarEmpleadoPorDocumento();
                break;
            case 7:
                calcularSueldoEmpleado();
                break;
            case 8:
                contarEmpleadosEnEmpresa();
                break;
            case 9:
                listarEmpleadosDeEmpresa();
                break;
            case 10:
                System.out.println(espacios + "══════════════════════════════════════════════");
                System.out.println(espacios + "         ¡Hasta luego!");
                System.out.println(espacios + "══════════════════════════════════════════════");
                break;
            default:
                System.out.println(espacios + "❌ Opción no válida.");
        }
    }

    private static void agregarEmpresa() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         AGREGAR EMPRESA");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.print(espacios + "📌 NIT: ");
        String nit = scanner.nextLine();
        System.out.print(espacios + "🏷️ Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print(espacios + "📍 Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print(espacios + "🌆 Ciudad: ");
        String ciudad = scanner.nextLine();

        Empresa empresa = new EmpresaDesarrollo(nit, nombre, direccion, ciudad);
        operacionEmpresa.agregarEmpresa(empresa);
    }

    private static void buscarEmpresaPorNit() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         BUSCAR EMPRESA");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.print(espacios + "📌 NIT: ");
        String nit = scanner.nextLine();
        Empresa empresa = operacionEmpresa.buscarEmpresaPorNit(nit);
        if (empresa != null) {
            System.out.println(espacios + "✅ Encontrada: " + empresa.getNombre());
        } else {
            System.out.println(espacios + "❌ No encontrada");
        }
    }

    private static void agregarEmpleado() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         AGREGAR EMPLEADO");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.println(espacios + "📋 EMPRESAS DISPONIBLES:");
        operacionEmpresa.listarTodasEmpresas();
        
        System.out.print(espacios + "📌 NIT empresa (o Enter): ");
        String nitEmpresa = scanner.nextLine();
        
        Empresa empresa = null;
        if (!nitEmpresa.isEmpty()) {
            empresa = operacionEmpresa.buscarEmpresaPorNit(nitEmpresa);
            if (empresa != null) {
                System.out.println(espacios + "✅ Seleccionada: " + empresa.getNombre());
            }
        }

        System.out.print(espacios + "🆔 Documento: ");
        String doc = scanner.nextLine();
        System.out.print(espacios + "👤 Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print(espacios + "💰 Sueldo por hora: $");
        double sueldo = Double.parseDouble(scanner.nextLine());
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "   1. 👨‍💻 Desarrollador");
        System.out.println(espacios + "   2. 👔 Gestor de Proyectos");
        System.out.println(espacios + "   3. 👑 Admin");
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.print(espacios + "👉 Tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());
        
        Empleado empleado = null;
        switch(tipo) {
            case 1: 
                empleado = new Desarrollador(doc, nombre, sueldo);
                System.out.println(espacios + "✅ Desarrollador creado");
                break;
            case 2: 
                System.out.print(espacios + "📋 Área: ");
                String area = scanner.nextLine();
                empleado = new GestorProyectos(doc, nombre, sueldo, area);
                System.out.println(espacios + "✅ Gestor creado");
                break;
            case 3: 
                empleado = new Admin(doc, nombre, sueldo);
                System.out.println(espacios + "✅ Admin creado");
                break;
            default:
                System.out.println(espacios + "❌ Tipo inválido");
                return;
        }
        
        if (empresa != null) {
            empresa.agregarEmpleado(empleado);
        }
        
        operacionEmpleado.agregarEmpleado(empleado);
    }

    private static void buscarEmpleadoPorDocumento() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         BUSCAR EMPLEADO");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.print(espacios + "📌 Documento: ");
        String doc = scanner.nextLine();
        Empleado emp = operacionEmpleado.buscarEmpleadoPorDocumento(doc);
        if (emp != null) {
            System.out.println(espacios + "✅ Encontrado: " + emp.getNombre());
        } else {
            System.out.println(espacios + "❌ No encontrado");
        }
    }

    private static void calcularSueldoEmpleado() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         CALCULAR SUELDO");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.print(espacios + "📌 Documento: ");
        String doc = scanner.nextLine();
        Empleado emp = operacionEmpleado.buscarEmpleadoPorDocumento(doc);
        
        if (emp == null) {
            System.out.println(espacios + "❌ Empleado no encontrado");
            return;
        }
        
        System.out.print(espacios + "⏱️ Horas: ");
        int horas = Integer.parseInt(scanner.nextLine());
        
        double sueldo = operacionEmpleado.calcularSueldoEmpleado(doc, horas);
        
        System.out.println(espacios + "──────────────────────────────────────────────────");
        System.out.println(espacios + "   Empleado: " + emp.getNombre());
        System.out.println(espacios + "   Tipo: " + emp.getClass().getSimpleName());
        System.out.printf(espacios + "   Sueldo/hora: $%,.0f%n", emp.getSueldoHora());
        System.out.println(espacios + "   Horas: " + horas);
        System.out.printf(espacios + "   TOTAL: $%,.0f%n", sueldo);
    }

    private static void contarEmpleadosEnEmpresa() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         CONTAR EMPLEADOS");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.print(espacios + "📌 NIT: ");
        String nit = scanner.nextLine();
        int cantidad = operacionEmpresa.contarEmpleadosEnEmpresa(nit);
        if (cantidad != -1) {
            System.out.println(espacios + "📊 Empleados: " + cantidad);
        } else {
            System.out.println(espacios + "❌ Empresa no encontrada");
        }
    }

    private static void listarEmpleadosDeEmpresa() {
        String espacios = "                                   ";
        
        System.out.println(espacios + "══════════════════════════════════════════════");
        System.out.println(espacios + "         EMPLEADOS POR EMPRESA");
        System.out.println(espacios + "══════════════════════════════════════════════");
        
        System.out.print(espacios + "📌 NIT: ");
        String nit = scanner.nextLine();
        operacionEmpresa.listarEmpleadosDeEmpresa(nit);
    }
}
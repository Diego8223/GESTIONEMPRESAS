package main;

import modelos.*;
import operaciones.*;
import javax.swing.*;
import java.util.Scanner;

public class Main {
    private static OperacionEmpresa operacionEmpresa = new OperacionEmpresa();
    private static OperacionEmpleado operacionEmpleado = new OperacionEmpleado();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE EMPRESAS Y EMPLEADOS ===");
        System.out.println("1. Agregar Empresa");
        System.out.println("2. Listar todas las Empresas");
        System.out.println("3. Buscar Empresa por NIT");
        System.out.println("4. Agregar Empleado");
        System.out.println("5. Listar todos los Empleados");
        System.out.println("6. Buscar Empleado por Documento");
        System.out.println("7. Calcular Sueldo de Empleado");
        System.out.println("8. Contar empleados en una empresa");
        System.out.println("9. Listar empleados de una empresa");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarEmpresa();
                break;
            case 2:
                operacionEmpresa.listarTodasEmpresas();
                break;
            case 3:
                buscarEmpresaPorNit();
                break;
            case 4:
                agregarEmpleado();
                break;
            case 5:
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
            case 0:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private static void agregarEmpresa() {
        System.out.println("\n=== AGREGAR NUEVA EMPRESA ===");
        System.out.print("NIT: ");
        String nit = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();

        Empresa empresa = new EmpresaDesarrollo(nit, nombre, direccion, ciudad);
        operacionEmpresa.agregarEmpresa(empresa);
    }

    private static void buscarEmpresaPorNit() {
        System.out.print("\nIngrese el NIT de la empresa a buscar: ");
        String nit = scanner.nextLine();
        Empresa empresa = operacionEmpresa.buscarEmpresaPorNit(nit);
        if (empresa != null) {
            System.out.println("Empresa encontrada: " + empresa.toString());
        } else {
            System.out.println("Empresa no encontrada.");
        }
    }

    private static void agregarEmpleado() {
        System.out.println("\n=== AGREGAR NUEVO EMPLEADO ===");
        
        // Verificar si hay empresas para asignar
        if (operacionEmpresa.buscarEmpresaPorNit("") == null && 
            !hayEmpresasDisponibles()) {
            System.out.println("Debe registrar al menos una empresa primero.");
            return;
        }

        System.out.print("Documento: ");
        String documento = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Sueldo por hora: ");
        double sueldoHora = Double.parseDouble(scanner.nextLine());
        
        System.out.println("Tipo de empleado:");
        System.out.println("1. Desarrollador");
        System.out.println("2. Gestor de Proyectos");
        System.out.println("3. Admin");
        System.out.print("Seleccione tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        Empleado empleado = null;
        switch (tipo) {
            case 1:
                empleado = new Desarrollador(documento, nombre, sueldoHora);
                break;
            case 2:
                System.out.print("Área de gestión: ");
                String area = scanner.nextLine();
                empleado = new GestorProyectos(documento, nombre, sueldoHora, area);
                break;
            case 3:
                empleado = new Admin(documento, nombre, sueldoHora);
                break;
            default:
                System.out.println("Tipo no válido.");
                return;
        }

        // Asignar empresa
        System.out.println("\nEmpresas disponibles:");
        operacionEmpresa.listarTodasEmpresas();
        System.out.print("Ingrese el NIT de la empresa para asignar: ");
        String nitEmpresa = scanner.nextLine();
        
        Empresa empresa = operacionEmpresa.buscarEmpresaPorNit(nitEmpresa);
        if (empresa != null) {
            empresa.agregarEmpleado(empleado);
            operacionEmpleado.agregarEmpleado(empleado);
        } else {
            System.out.println("Empresa no encontrada. Empleado no asignado.");
            operacionEmpleado.agregarEmpleado(empleado);
        }
    }

    private static boolean hayEmpresasDisponibles() {
        // Método auxiliar para verificar si hay empresas
        try {
            operacionEmpresa.buscarEmpresaPorNit("dummy");
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static void buscarEmpleadoPorDocumento() {
        System.out.print("\nIngrese el documento del empleado a buscar: ");
        String documento = scanner.nextLine();
        Empleado empleado = operacionEmpleado.buscarEmpleadoPorDocumento(documento);
        if (empleado != null) {
            System.out.println("Empleado encontrado: " + empleado.toString());
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void calcularSueldoEmpleado() {
        System.out.print("\nIngrese el documento del empleado: ");
        String documento = scanner.nextLine();
        System.out.print("Ingrese las horas trabajadas: ");
        int horas = Integer.parseInt(scanner.nextLine());
        
        double sueldo = operacionEmpleado.calcularSueldoEmpleado(documento, horas);
        if (sueldo != -1) {
            System.out.println("El sueldo calculado es: $" + sueldo);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void contarEmpleadosEnEmpresa() {
        System.out.print("\nIngrese el NIT de la empresa: ");
        String nit = scanner.nextLine();
        int cantidad = operacionEmpresa.contarEmpleadosEnEmpresa(nit);
        if (cantidad != -1) {
            System.out.println("La empresa tiene " + cantidad + " empleados.");
        } else {
            System.out.println("Empresa no encontrada.");
        }
    }

    private static void listarEmpleadosDeEmpresa() {
        System.out.print("\nIngrese el NIT de la empresa: ");
        String nit = scanner.nextLine();
        operacionEmpresa.listarEmpleadosDeEmpresa(nit);
    }
}
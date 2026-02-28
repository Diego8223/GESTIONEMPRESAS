package operaciones;

import modelos.Empleado;
import modelos.Empresa;
import java.util.ArrayList;

public class OperacionEmpleado implements IOperacionEmpleado {
    private ArrayList<Empleado> empleados;

    public OperacionEmpleado() {
        this.empleados = new ArrayList<>();
    }

    @Override
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado agregado exitosamente.");
    }

    @Override
    public void listarTodosEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n=== LISTA DE EMPLEADOS ===");
        for (Empleado emp : empleados) {
            System.out.println(emp.toString());
        }
    }

    @Override
    public Empleado buscarEmpleadoPorDocumento(String documento) {
        for (Empleado emp : empleados) {
            if (emp.getDocumento().equals(documento)) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public double calcularSueldoEmpleado(String documento, int horasTrabajadas) {
        Empleado emp = buscarEmpleadoPorDocumento(documento);
        if (emp != null) {
            return emp.calcularSueldo(horasTrabajadas);
        }
        return -1;
    }

    @Override
    public int contarEmpleadosPorEmpresa(String nitEmpresa) {
        int contador = 0;
        for (Empleado emp : empleados) {
            if (emp.getEmpresa() != null && emp.getEmpresa().getNit().equals(nitEmpresa)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public void listarEmpleadosPorEmpresa(String nitEmpresa) {
        System.out.println("\n=== EMPLEADOS DE LA EMPRESA CON NIT: " + nitEmpresa + " ===");
        boolean encontrado = false;
        for (Empleado emp : empleados) {
            if (emp.getEmpresa() != null && emp.getEmpresa().getNit().equals(nitEmpresa)) {
                System.out.println(emp.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay empleados en esta empresa o la empresa no existe.");
        }
    }
}
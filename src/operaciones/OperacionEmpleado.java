package operaciones;

import modelos.Empleado;
import java.util.ArrayList;

public class OperacionEmpleado implements IOperacionEmpleado {
    private ArrayList<Empleado> empleados;
    private static final String ESPACIOS = "                                   "; // 35 espacios

    public OperacionEmpleado() {
        this.empleados = new ArrayList<>();
    }

    @Override
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println(ESPACIOS + "✅ Empleado agregado exitosamente.");
    }

    @Override
    public void listarTodosEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println(ESPACIOS + "📋 No hay empleados registrados.");
            return;
        }
        System.out.println(ESPACIOS + "──────────────────────────────────────────────────");
        for (Empleado emp : empleados) {
            System.out.println(ESPACIOS + "  " + emp.toString());
            System.out.println(ESPACIOS + "──────────────────────────────────────────────────");
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
        System.out.println(ESPACIOS + "══════════════════════════════════════════════");
        System.out.println(ESPACIOS + "   EMPLEADOS DE LA EMPRESA NIT: " + nitEmpresa);
        System.out.println(ESPACIOS + "══════════════════════════════════════════════");
        boolean encontrado = false;
        for (Empleado emp : empleados) {
            if (emp.getEmpresa() != null && emp.getEmpresa().getNit().equals(nitEmpresa)) {
                System.out.println(ESPACIOS + "  " + emp.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println(ESPACIOS + "  📋 No hay empleados en esta empresa.");
        }
    }
}
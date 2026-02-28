package operaciones;

import modelos.Empresa;
import modelos.Empleado;
import java.util.ArrayList;

public class OperacionEmpresa implements IOperacionEmpresa {
    private ArrayList<Empresa> empresas;

    public OperacionEmpresa() {
        this.empresas = new ArrayList<>();
    }

    @Override
    public void agregarEmpresa(Empresa empresa) {
        empresas.add(empresa);
        System.out.println("Empresa agregada exitosamente.");
    }

    @Override
    public void listarTodasEmpresas() {
        if (empresas.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }
        System.out.println("\n=== LISTA DE EMPRESAS ===");
        for (Empresa emp : empresas) {
            System.out.println(emp.toString());
        }
    }

    @Override
    public Empresa buscarEmpresaPorNit(String nit) {
        for (Empresa emp : empresas) {
            if (emp.getNit().equals(nit)) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public int contarEmpleadosEnEmpresa(String nit) {
        Empresa empresa = buscarEmpresaPorNit(nit);
        if (empresa != null) {
            return empresa.getEmpleados().size();
        }
        return -1;
    }

    @Override
    public void listarEmpleadosDeEmpresa(String nit) {
        Empresa empresa = buscarEmpresaPorNit(nit);
        if (empresa != null) {
            System.out.println("\n=== EMPLEADOS DE " + empresa.getNombre() + " ===");
            if (empresa.getEmpleados().isEmpty()) {
                System.out.println("La empresa no tiene empleados.");
            } else {
                for (Empleado emp : empresa.getEmpleados()) {
                    System.out.println(emp.toString());
                }
            }
        } else {
            System.out.println("Empresa no encontrada.");
        }
    }
}
package operaciones;

import modelos.Empresa;
import modelos.Empleado;
import java.util.ArrayList;

public class OperacionEmpresa implements IOperacionEmpresa {
    private ArrayList<Empresa> empresas;
    private static final String ESPACIOS = "                                   "; // 35 espacios

    public OperacionEmpresa() {
        this.empresas = new ArrayList<>();
    }

    @Override
    public void agregarEmpresa(Empresa empresa) {
        empresas.add(empresa);
        System.out.println(ESPACIOS + "✅ Empresa agregada exitosamente.");
    }

    @Override
    public void listarTodasEmpresas() {
        if (empresas.isEmpty()) {
            System.out.println(ESPACIOS + "📋 No hay empresas registradas.");
            return;
        }
        System.out.println(ESPACIOS + "──────────────────────────────────────────────────");
        for (Empresa emp : empresas) {
            System.out.println(ESPACIOS + "  " + emp.toString());
            System.out.println(ESPACIOS + "──────────────────────────────────────────────────");
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
            System.out.println(ESPACIOS + "══════════════════════════════════════════════");
            System.out.println(ESPACIOS + "   EMPLEADOS DE " + empresa.getNombre());
            System.out.println(ESPACIOS + "══════════════════════════════════════════════");
            if (empresa.getEmpleados().isEmpty()) {
                System.out.println(ESPACIOS + "   📋 La empresa no tiene empleados.");
            } else {
                for (Empleado emp : empresa.getEmpleados()) {
                    System.out.println(ESPACIOS + "   " + emp.toString());
                    System.out.println(ESPACIOS + "   ─────────────────────────────────────");
                }
            }
        } else {
            System.out.println(ESPACIOS + "❌ Empresa no encontrada.");
        }
    }
}
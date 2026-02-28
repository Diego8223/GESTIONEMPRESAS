package operaciones;

import modelos.Empleado;

public interface IOperacionEmpleado {
    void agregarEmpleado(Empleado empleado);
    void listarTodosEmpleados();
    Empleado buscarEmpleadoPorDocumento(String documento);
    double calcularSueldoEmpleado(String documento, int horasTrabajadas);
    int contarEmpleadosPorEmpresa(String nitEmpresa);
    void listarEmpleadosPorEmpresa(String nitEmpresa);
}
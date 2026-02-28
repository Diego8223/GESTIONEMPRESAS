package operaciones;

import modelos.Empresa;

public interface IOperacionEmpresa {
    void agregarEmpresa(Empresa empresa);
    void listarTodasEmpresas();
    Empresa buscarEmpresaPorNit(String nit);
    int contarEmpleadosEnEmpresa(String nit);
    void listarEmpleadosDeEmpresa(String nit);
}
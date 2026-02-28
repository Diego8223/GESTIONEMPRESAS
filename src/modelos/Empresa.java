package modelos;

import java.util.ArrayList;

public class Empresa {
    private String nit;
    private String nombre;
    private String direccion;
    private String ciudad;
    private ArrayList<Empleado> empleados;

    public Empresa() {
        this.empleados = new ArrayList<>();
    }

    public Empresa(String nit, String nombre, String direccion, String ciudad) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.empleados = new ArrayList<>();
    }

    // Getters y setters (igual que antes)
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public ArrayList<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(ArrayList<Empleado> empleados) { this.empleados = empleados; }
    
    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
        empleado.setEmpresa(this);
    }
    
    @Override
    public String toString() {
        return String.format("🏢 NIT: %s | %s | %s | %s | 👥 %d empleados",
            nit, nombre, direccion, ciudad, empleados.size());
    }
}
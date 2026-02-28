package modelos;

public class GestorProyectos extends Empleado {
    private String area;

    public GestorProyectos() {
        super();
    }

    public GestorProyectos(String documento, String nombre, double sueldoHora, String area) {
        super(documento, nombre, sueldoHora);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public double calcularSueldo(int horasTrabajadas) {
        // Los gestores tienen un bono del 10% por su área de gestión
        double sueldoBase = getSueldoHora() * horasTrabajadas;
        return sueldoBase * 1.10;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Área: " + area;
    }
}
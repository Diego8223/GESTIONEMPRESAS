package modelos;

public class Desarrollador extends Empleado {
    
    public Desarrollador() {
        super();
    }
    
    public Desarrollador(String documento, String nombre, double sueldoHora) {
        super(documento, nombre, sueldoHora);
    }
    
    @Override
    public double calcularSueldo(int horasTrabajadas) {
        return getSueldoHora() * horasTrabajadas;
    }
}
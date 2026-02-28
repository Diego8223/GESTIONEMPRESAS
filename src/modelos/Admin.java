package modelos;

public class Admin extends Empleado {
    
    public Admin() {
        super();
    }
    
    public Admin(String documento, String nombre, double sueldoHora) {
        super(documento, nombre, sueldoHora);
    }
    
    @Override
    public double calcularSueldo(int horasTrabajadas) {
        // Los admins tienen un bono fijo de $100,000
        return (getSueldoHora() * horasTrabajadas) + 100000;
    }
}
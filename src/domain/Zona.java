package domain;

public class Zona {
    private String nombre;
    private double precio;
    private int capacidad;
    private ListaSimple<Asiento> asientos;

    public Zona(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.asientos = new ListaSimple<>();
        inicializarAsientos();
    }

    private void inicializarAsientos() {
        for (int i = 1; i <= capacidad; i++) {
            asientos.agregar(new Asiento());
        }
    }

    // Getters y setters
}
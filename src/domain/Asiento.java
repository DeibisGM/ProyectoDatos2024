package domain;

public class Asiento {
    private int numero;
    private boolean ocupado;

    public Asiento(int numero) {
        this.numero = numero;
        this.ocupado = false;
    }

    // Getters and setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public boolean isOcupado() { return ocupado; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }
}
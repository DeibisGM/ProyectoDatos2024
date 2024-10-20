package domain;

public class Reserva {
    private String nombreCliente;
    private String identificacion;
    private String formaPago;
    private String correoElectronico;
    private String genero;
    private boolean primerReservaEnLinea;
    private ListaSimple<Asiento> asientosReservados;
    private double totalPagar;

    public Reserva(String nombreCliente, String identificacion, String formaPago, String correoElectronico, String genero, boolean primerReservaEnLinea, ListaSimple<Asiento> asientosReservados, double totalPagar) {
        this.nombreCliente = nombreCliente;
        this.identificacion = identificacion;
        this.formaPago = formaPago;
        this.correoElectronico = correoElectronico;
        this.genero = genero;
        this.primerReservaEnLinea = primerReservaEnLinea;
        this.asientosReservados = asientosReservados;
        this.totalPagar = totalPagar;
    }

    // Getters and setters
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getFormaPago() { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public boolean isPrimerReservaEnLinea() { return primerReservaEnLinea; }
    public void setPrimerReservaEnLinea(boolean primerReservaEnLinea) { this.primerReservaEnLinea = primerReservaEnLinea; }

    public ListaSimple<Asiento> getAsientosReservados() { return asientosReservados; }
    public void setAsientosReservados(ListaSimple<Asiento> asientosReservados) { this.asientosReservados = asientosReservados; }

    public double getTotalPagar() { return totalPagar; }
    public void setTotalPagar(double totalPagar) { this.totalPagar = totalPagar; }
}
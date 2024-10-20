package domain;

import java.time.LocalTime;

public class Servicio {
    private String nombreEmpresa;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private int cantidadContrataciones;
    private double precio;
    private ServiceManager encargado;

    public Servicio(String nombreEmpresa, LocalTime horaEntrada, LocalTime horaSalida, int cantidadContrataciones, double precio, ServiceManager encargado) {
        this.nombreEmpresa = nombreEmpresa;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cantidadContrataciones = cantidadContrataciones;
        this.precio = precio;
        this.encargado = encargado;
    }

    // Getters and setters
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }

    public int getCantidadContrataciones() { return cantidadContrataciones; }
    public void setCantidadContrataciones(int cantidadContrataciones) { this.cantidadContrataciones = cantidadContrataciones; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public ServiceManager getEncargado() { return encargado; }
    public void setEncargado(ServiceManager encargado) { this.encargado = encargado; }
}
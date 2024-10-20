package domain;// Evento.java
import java.time.LocalDateTime;

public class Evento {
    private String nombre;
    private String tipo;
    private String detalles;
    private LocalDateTime fechaHora;
    private String lugar;
    private int maxAsistentes;

    public Evento(String nombre, String tipo, String detalles, LocalDateTime fechaHora, String lugar, int maxAsistentes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.detalles = detalles;
        this.fechaHora = fechaHora;
        this.lugar = lugar;
        this.maxAsistentes = maxAsistentes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getLugar() {
        return lugar;
    }

    public int getMaxAsistentes() {
        return maxAsistentes;
    }

    public long getMonthsUntilEvent() {
        return LocalDateTime.now().until(this.fechaHora, java.time.temporal.ChronoUnit.MONTHS);
    }

    public boolean hasPassed() {
        return this.fechaHora.isBefore(LocalDateTime.now());
    }
}
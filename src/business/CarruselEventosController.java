package business;

import domain.Evento;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarruselEventosController {

    private List<Evento> eventos;
    private int currentIndex = 0;

    @FXML
    private Label tituloLabel;

    @FXML
    private Label eventoLabel;

    @FXML
    private Label estadoLabel;

    public void initialize() {
        eventos = crearEventos();
        actualizarEvento();
    }

    private List<Evento> crearEventos() {
        // Debería moverse a un servicio separado si hay lógica o persistencia pesada
        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("Evento 1", "Tipo 1", "Detalles del Evento 1", LocalDateTime.of(2023, 12, 25, 18, 0), "Lugar 1", 100));
        eventos.add(new Evento("Evento 2", "Tipo 2", "Detalles del Evento 2", LocalDateTime.of(2024, 11, 10, 10, 0), "Lugar 2", 150));
        eventos.add(new Evento("Evento 3", "Tipo 3", "Detalles del Evento 3", LocalDateTime.of(2025, 5, 5, 13, 0), "Lugar 3", 200));
        return eventos;
    }

    private void actualizarEvento() {
        Evento evento = eventos.get(currentIndex);
        eventoLabel.setText(String.format("Nombre: %s%nTipo: %s%nDetalles: %s%nFecha y Hora: %s%nLugar: %s%nMáx Asistentes: %d",
                evento.getNombre(), evento.getTipo(), evento.getDetalles(), evento.getFechaHora().toString(), evento.getLugar(), evento.getMaxAsistentes()));

        estadoLabel.setText(getEstadoEvento(evento));
    }

    private String getEstadoEvento(Evento evento) {
        if (evento.hasPassed()) {
            return "Este evento ya ha pasado.";
        } else {
            long mesesFaltantes = evento.getMonthsUntilEvent();
            return (mesesFaltantes > 1) ? "Quedan " + mesesFaltantes + " meses para este evento." : "Este es un evento próximo.";
        }
    }

    @FXML
    private void mostrarAnterior() {
        currentIndex = (currentIndex - 1 + eventos.size()) % eventos.size();
        actualizarEvento();
    }

    @FXML
    private void mostrarSiguiente() {
        currentIndex = (currentIndex + 1) % eventos.size();
        actualizarEvento();
    }
}
package business;

import data.FileData;
import data.Logic;
import domain.Event;
import domain.Zone;
import domain.Nodo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainController extends Application {
    @FXML private Button bt_addEvent;
    @FXML private TextArea ta_info;
    private FileData fileData;
    private static final String EVENT_FORM_PATH = "/presentation/EventForm.fxml";

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/presentation/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void initialize() {
        fileData = FileData.getInstance();
        actualizarTextArea();
    }

    private void actualizarTextArea() {
        if (fileData.getEventos().getCabeza() != null) {
            Nodo<Event> currentNode = fileData.getCurrentEventNode();
            if (currentNode == null) {
                currentNode = fileData.getEventos().getCabeza();
                fileData.setCurrentEventNode(currentNode);
            }

            Event currentEvent = currentNode.getDato();
            StringBuilder infoBuilder = new StringBuilder();
            appendEventInfo(infoBuilder, currentEvent);
            appendZonesInfo(infoBuilder, currentEvent);

            ta_info.setText(infoBuilder.toString());
        } else {
            ta_info.setText("No hay eventos registrados.");
        }
    }

    private void appendEventInfo(StringBuilder builder, Event event) {
        builder.append("INFORMACIÓN DEL EVENTO\n")
                .append("====================\n")
                .append("Nombre: ").append(event.getName()).append("\n")
                .append("Tipo: ").append(event.getType()).append("\n")
                .append("Detalles: ").append(event.getDetails()).append("\n")
                .append("Fecha: ").append(event.getDate()).append(" ").append(event.getTime()).append("\n")
                .append("Lugar: ").append(event.getPlace()).append("\n")
                .append("Capacidad: ").append(event.getCapacity()).append("\n\n");
    }

    private void appendZonesInfo(StringBuilder builder, Event event) {
        builder.append("ZONAS DEL EVENTO\n")
                .append("===============\n");

        if (event.getZones().isEmpty()) {
            builder.append("No hay zonas definidas para este evento.\n");
            return;
        }

        int zoneCount = 1;
        for (Zone zone : event.getZones()) {
            builder.append(String.format("Zona %d:\n", zoneCount++))
                    .append(String.format("  Nombre: %s\n", zone.getName()))
                    .append(String.format("  Categoría: %s\n", zone.getCategory()))
                    .append(String.format("  Precio: $%.2f\n", zone.getPrice()))
                    .append(String.format("  Capacidad: %d\n\n", zone.getTotalSpaces()));
        }
    }

    @FXML
    void addEvent(ActionEvent event) {
        Logic.closeCurrentWindowAndOpen(EVENT_FORM_PATH, (Stage) bt_addEvent.getScene().getWindow());
    }

    @FXML
    void navigateNext(ActionEvent event) {
        Nodo<Event> currentNode = fileData.getCurrentEventNode();
        if (currentNode != null && currentNode.getSiguiente() != null) {
            fileData.setCurrentEventNode(currentNode.getSiguiente());
            actualizarTextArea();
        }
    }

    @FXML
    void navigatePrev(ActionEvent event) {
        Nodo<Event> currentNode = fileData.getCurrentEventNode();
        if (currentNode != null && currentNode.getAnterior() != null) {
            fileData.setCurrentEventNode(currentNode.getAnterior());
            actualizarTextArea();
        }
    }
}
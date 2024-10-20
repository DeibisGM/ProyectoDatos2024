package business;

import data.FileData;
import data.Logic;
import domain.Event;
import domain.Zone;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventFormController {

    private static final String EVENT_FORM_PATH = "/presentation/Main.fxml";
    private static final String ZONE_FORM_PATH = "/presentation/zoneForm.fxml";

    @FXML
    private Button bt_CreateEvent;
    @FXML
    private TextField eventName;
    @FXML
    private ComboBox<String> eventType;
    @FXML
    private TextArea eventDetails;
    @FXML
    private DatePicker eventDate;
    @FXML
    private TextField eventTime;
    @FXML
    private TextField eventPlace;
    @FXML
    private TextField eventCapacity;
    @FXML
    private ListView<String> zoneList;
    @FXML
    private Button btnAddCapacity;
    @FXML
    private Button btnResetZones;

    private ObservableList<Zone> zones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        eventType.setItems(FXCollections.observableArrayList(
                "Concierto", "Teatro", "Deportivo", "Conferencia"
        ));

        // Disable buttons initially
        btnAddCapacity.setDisable(true);
        btnResetZones.setDisable(true);
    }

    @FXML
    private void handleAddCapacity() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Capacidad");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el número de asientos adicionales:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                if (!input.trim().isEmpty()) {
                    int additionalCapacity = Integer.parseInt(input);
                    int currentCapacity = Integer.parseInt(eventCapacity.getText());
                    int newCapacity = currentCapacity + additionalCapacity;

                    eventCapacity.setText(String.valueOf(newCapacity));

                    // Open zone form to assign the new capacity
                    openZoneForm();
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Por favor ingrese un número válido");
            }
        });
    }

    @FXML
    private void handleResetZones() {
        zones.clear();
        updateZoneList();
        eventCapacity.setDisable(false);
        btnAddCapacity.setDisable(true);
        btnResetZones.setDisable(true);
    }

    @FXML
    private void openZoneForm() {
        if (eventCapacity.getText().isEmpty()) {
            showAlert("Error", "Debe definir la capacidad total del evento antes de gestionar zonas");
            return;
        }

        try {
            int totalCapacity = Integer.parseInt(eventCapacity.getText());

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ZONE_FORM_PATH));
            Parent root = loader.load();

            ZoneFormController zoneController = loader.getController();
            zoneController.setData(totalCapacity, zones, this);

            Stage zoneStage = new Stage();
            zoneStage.initModality(Modality.APPLICATION_MODAL);
            zoneStage.setTitle("Gestionar Zonas");
            zoneStage.setScene(new Scene(root));
            zoneStage.showAndWait();

        } catch (NumberFormatException e) {
            showAlert("Error", "La capacidad debe ser un número válido");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error al abrir el formulario de zonas");
        }
    }

    public void updateZones(ObservableList<Zone> newZones) {
        this.zones.clear();
        this.zones.addAll(newZones);
        updateZoneList();

        // Disable capacity field and enable buttons when zones are added
        if (!zones.isEmpty()) {
            eventCapacity.setDisable(true);
            btnAddCapacity.setDisable(false);
            btnResetZones.setDisable(false);
        }
    }

    private void updateZoneList() {
        zoneList.getItems().clear();
        for (Zone zone : zones) {
            zoneList.getItems().add(String.format("%s - %s ($%.2f) - Capacidad: %d",
                    zone.getName(), zone.getCategory(), zone.getPrice(), zone.getTotalSpaces()));
        }
    }

    @FXML
    private void handleCreateEvent() {
        if (!validateEventFields()) {
            return;
        }

        if (zones.isEmpty()) {
            showAlert("Error", "Debe agregar al menos una zona al evento");
            return;
        }

        try {
            int totalCapacity = Integer.parseInt(eventCapacity.getText());
            int totalZoneCapacity = calculateTotalZoneCapacity();

            if (totalZoneCapacity != totalCapacity) {
                showAlert("Error", String.format(
                        "La suma de capacidades de las zonas (%d) no coincide con la capacidad total del evento (%d)",
                        totalZoneCapacity,
                        totalCapacity
                ));
                return;
            }

            LocalTime.parse(eventTime.getText());

            Event event = new Event(
                    eventName.getText(),
                    eventType.getValue(),
                    eventDetails.getText(),
                    eventDate.getValue(),
                    eventTime.getText(),
                    eventPlace.getText(),
                    totalCapacity,
                    zones
            );

            FileData.getInstance().agregarEvento(event);
            Logic.closeCurrentWindowAndOpen(EVENT_FORM_PATH, (Stage) bt_CreateEvent.getScene().getWindow());

        } catch (DateTimeParseException e) {
            showAlert("Error", "Formato de hora inválido. Use HH:mm");
        } catch (NumberFormatException e) {
            showAlert("Error", "La capacidad debe ser un número válido");
        }
    }

    // Método modificado para calcular la capacidad total de forma tradicional
    private int calculateTotalZoneCapacity() {
        int totalCapacity = 0;
        for (Zone zone : zones) {
            totalCapacity += zone.getTotalSpaces();
        }
        return totalCapacity;
    }

    private boolean validateEventFields() {
        if (eventName.getText().isEmpty() ||
                eventType.getValue() == null ||
                eventDetails.getText().isEmpty() ||
                eventDate.getValue() == null ||
                eventTime.getText().isEmpty() ||
                eventPlace.getText().isEmpty() ||
                eventCapacity.getText().isEmpty()) {

            showAlert("Error", "Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
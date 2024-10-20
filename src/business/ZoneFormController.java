package business;

import domain.Zone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ZoneFormController {
    @FXML
    private TextField zoneName;
    @FXML
    private TextField zonePrice;
    @FXML
    private TextField zoneCapacity;
    @FXML
    private ComboBox<String> zoneCategory;
    @FXML
    private ListView<String> zoneList;
    @FXML
    private Label totalCapacityLabel;
    @FXML
    private Label remainingCapacityLabel;

    private ObservableList<Zone> zones = FXCollections.observableArrayList();
    private int totalCapacity;
    private int remainingCapacity;
    private EventFormController parentController;

    @FXML
    public void initialize() {
        zoneCategory.setItems(FXCollections.observableArrayList(
                "VIP", "Preferencial", "General"
        ));
    }

    public void setData(int totalCapacity, ObservableList<Zone> existingZones, EventFormController parentController) {
        this.totalCapacity = totalCapacity;
        this.parentController = parentController;
        this.zones.addAll(existingZones);

        // Actualizar la lista visual con las zonas existentes
        updateZoneList();

        // Actualizar etiquetas de capacidad
        totalCapacityLabel.setText("Capacidad Total: " + totalCapacity);
        updateRemainingCapacity();
    }

    private void updateRemainingCapacity() {
        int usedCapacity = calculateUsedCapacity();
        remainingCapacity = totalCapacity - usedCapacity;
        remainingCapacityLabel.setText("Asientos Restantes: " + remainingCapacity);
    }

    private int calculateUsedCapacity() {
        return zones.stream().mapToInt(Zone::getTotalSpaces).sum();
    }

    @FXML
    private void handleAddZone() {
        if (!validateZoneFields()) {
            return;
        }

        try {
            String name = zoneName.getText();
            double price = Double.parseDouble(zonePrice.getText());
            int zoneCapacityValue = Integer.parseInt(zoneCapacity.getText());
            String category = zoneCategory.getValue();

            if (zoneCapacityValue > remainingCapacity) {
                showAlert("Error", "La capacidad de la zona excede los asientos disponibles. " +
                        "Asientos restantes: " + remainingCapacity);
                return;
            }

            Zone zone = new Zone(name, price, zoneCapacityValue, category);
            zones.add(zone);
            updateZoneList();
            updateRemainingCapacity();
            clearZoneFields();

        } catch (NumberFormatException e) {
            showAlert("Error", "Precio y capacidad deben ser números válidos");
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
    private void handleFinish() {
        if (calculateUsedCapacity() < totalCapacity) {
            showAlert("Error", "Aún quedan asientos por asignar a zonas");
            return;
        }

        parentController.updateZones(zones);
        closeWindow();
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) zoneName.getScene().getWindow();
        stage.close();
    }

    private boolean validateZoneFields() {
        if (zoneName.getText().isEmpty() ||
                zonePrice.getText().isEmpty() ||
                zoneCapacity.getText().isEmpty() ||
                zoneCategory.getValue() == null) {

            showAlert("Error", "Todos los campos de la zona son requeridos");
            return false;
        }
        return true;
    }

    private void clearZoneFields() {
        zoneName.clear();
        zonePrice.clear();
        zoneCapacity.clear();
        zoneCategory.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
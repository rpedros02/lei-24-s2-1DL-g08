package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.time.LocalDate;

public class RegisterVehicleGUI {

    @FXML
    private TextField txtPlateId;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtTare;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtMileage;

    @FXML
    private TextField txtLastVehicleCheckup;

    @FXML
    private DatePicker dpRegisterDate;

    @FXML
    private DatePicker dpAcquisitionDate;

    @FXML
    private TextField txtMaintenanceFrequency;

    @FXML
    private Label lblMessage;

    private final VehicleController controller = new VehicleController();

    @FXML
    private void handleRegisterVehicle() {
        String plateId = txtPlateId.getText();
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String type = txtType.getText();
        String tareStr = txtTare.getText();
        String weightStr = txtWeight.getText();
        String mileageStr = txtMileage.getText();
        String lastVehicleCheckupStr = txtLastVehicleCheckup.getText();
        LocalDate registerDateLocal = dpRegisterDate.getValue();
        LocalDate acquisitionDateLocal = dpAcquisitionDate.getValue();
        String maintenanceFrequencyStr = txtMaintenanceFrequency.getText();

        if (plateId.isEmpty() || brand.isEmpty() || model.isEmpty() || type.isEmpty() || tareStr.isEmpty() || weightStr.isEmpty() || mileageStr.isEmpty() || lastVehicleCheckupStr.isEmpty() || registerDateLocal == null || acquisitionDateLocal == null || maintenanceFrequencyStr.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        try {
            double tare = Double.parseDouble(tareStr);
            double weight = Double.parseDouble(weightStr);
            int mileage = Integer.parseInt(mileageStr);
            int lastVehicleCheckup = Integer.parseInt(lastVehicleCheckupStr);
            Date registerDate = new Date(registerDateLocal.getDayOfMonth(), registerDateLocal.getMonthValue(), registerDateLocal.getYear());
            Date acquisitionDate = new Date(acquisitionDateLocal.getDayOfMonth(), acquisitionDateLocal.getMonthValue(), acquisitionDateLocal.getYear());
            int maintenanceFrequency = Integer.parseInt(maintenanceFrequencyStr);

            Vehicle vehicle = controller.createVehicle(plateId, brand, model, type, tare, weight, mileage, lastVehicleCheckup, registerDate, acquisitionDate, maintenanceFrequency);
            if (vehicle != null) {
                lblMessage.setText("Vehicle registered successfully!");
            } else {
                lblMessage.setText("Failed to register vehicle. Vehicle might already exist.");
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numerical values for tare, weight, mileage, last vehicle check up km, and maintenance frequency.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
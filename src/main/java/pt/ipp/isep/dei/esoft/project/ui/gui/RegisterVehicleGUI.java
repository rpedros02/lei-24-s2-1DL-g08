package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.time.LocalDate;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for registering a vehicle.
 */
public class RegisterVehicleGUI {

    @FXML
    // TextField for entering the vehicle's plate ID
    private TextField txtPlateId;

    @FXML
    // TextField for entering the vehicle's brand
    private TextField txtBrand;

    @FXML
    // TextField for entering the vehicle's model
    private TextField txtModel;

    @FXML
    // TextField for entering the vehicle's type
    private TextField txtType;

    @FXML
    // TextField for entering the vehicle's tare
    private TextField txtTare;

    @FXML
    // TextField for entering the vehicle's weight
    private TextField txtWeight;

    @FXML
    // TextField for entering the vehicle's mileage
    private TextField txtMileage;

    @FXML
    // TextField for entering the vehicle's last checkup
    private TextField txtLastVehicleCheckup;

    @FXML
    // DatePicker for selecting the vehicle's register date
    private DatePicker dpRegisterDate;

    @FXML
    // DatePicker for selecting the vehicle's acquisition date
    private DatePicker dpAcquisitionDate;

    @FXML
    // TextField for entering the vehicle's maintenance frequency
    private TextField txtMaintenanceFrequency;


    @FXML
    // Button for going back to the previous user interface
    private Button btnBack;

    /**
     * Handles the action of returning to the Vfm user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the VfmGUI.
     */
    @FXML
    public void handleVfm() {
        handleVFM(btnBack);
    }

    // Controller for managing vehicles
    private final VehicleController controller = new VehicleController();

    /**
     * Handles the action of registering a vehicle.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, creates a vehicle if the input is valid, and displays a message indicating the result.
     */
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
                showSuccess("Vehicle registered successfully.").showAndWait();
                handleVfm();
            } else {
                showAlert("An error occurred while registering the vehicle.").showAndWait();
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numerical values for tare, weight, mileage, last vehicle check up km, and maintenance frequency.");
        }
    }


}
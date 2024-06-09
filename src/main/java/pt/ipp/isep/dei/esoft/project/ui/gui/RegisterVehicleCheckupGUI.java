package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.time.LocalDate;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for registering a vehicle checkup.
 */
public class RegisterVehicleCheckupGUI {

    @FXML
    // TextField for entering the vehicle's plate ID
    private TextField txtPlateId;

    @FXML
    // DatePicker for selecting the checkup's register date
    private DatePicker dpRegisterDate1;

    @FXML
    // TextField for entering the last vehicle checkup's kilometer count
    private TextField txtLastVehicleCheckUpKm;

    @FXML
    // Label for displaying messages
    private Label lblMessage;

    @FXML
    // Button for going back to the previous user interface
    private Button btnBack;

    @FXML
    /**
     * Handles the action of returning to the Vfm user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the VfmGUI.
     */
    public void handleVfm() {
        loadUI("/VfmGUI.fxml");
    }

    // Controller for managing vehicle checkups
    private final VehicleCheckupController controller;

    /**
     * Constructs a RegisterVehicleCheckupGUI with a VehicleCheckupController.
     */
    public RegisterVehicleCheckupGUI() {
        this.controller = new VehicleCheckupController();
    }

    @FXML
    /**
     * Handles the action of registering a vehicle checkup.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, creates a vehicle checkup if the input is valid, and displays a message indicating the result.
     */
    private void handleRegisterVehicleCheckup(ActionEvent event) {
        String plateId = txtPlateId.getText();
        LocalDate localDate = dpRegisterDate1.getValue();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        int lastVehicleCheckUpKm = Integer.parseInt(txtLastVehicleCheckUpKm.getText());

        boolean success = controller.registerVehicleCheckup(plateId, date, lastVehicleCheckUpKm);

        if (success) {
            lblMessage.setText("Check-Up registered successfully!");
        } else {
            lblMessage.setText("Failed to register Check-Up. Please try again.");
        }
    }
}

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

public class RegisterVehicleCheckupGUI {

    @FXML
    private TextField txtPlateId;

    @FXML
    private DatePicker dpRegisterDate1;

    @FXML
    private TextField txtLastVehicleCheckUpKm;

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnBack;
    @FXML
    public void handleVfm() {
        loadUI("/VfmGUI.fxml");
    }

    private final VehicleCheckupController controller;

    public RegisterVehicleCheckupGUI() {
        this.controller = new VehicleCheckupController();
    }

    @FXML
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

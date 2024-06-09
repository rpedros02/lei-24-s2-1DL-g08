package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.handleGSM;

/**
 * This class provides a user interface for assigning one or more vehicles to an agenda entry.
 */
public class AssignOneOrMoreVehiclesGUI {

    @FXML
    // ComboBox for selecting vehicles
    private ComboBox<Vehicle> cbVehicles;

    @FXML
    // ComboBox for selecting an agenda entry
    private ComboBox<Entry> cbAgendaEntry;

    @FXML
    // Button for going back
    private Button btnBack;

    /**
     * Handles the action of navigating to the GSM user interface.
     * It is triggered when the GSM button is clicked.
     */
    @FXML
    public void handleGsm() {
       handleGSM(btnBack);
    }

    // Controller for assigning vehicles to agenda entries
    private final AssignVehicleAgendaController controller;

    /**
     * Constructs a new instance of AssignOneOrMoreVehiclesGUI.
     * It initializes the controller.
     */
    public AssignOneOrMoreVehiclesGUI() {
        controller = new AssignVehicleAgendaController();
    }

    /**
     * Initializes the user interface.
     * It populates the ComboBoxes with the vehicles and agenda entries.
     */
    @FXML
    public void initialize() {
        cbVehicles.setItems(FXCollections.observableArrayList(controller.getVehicles()));
        cbAgendaEntry.setItems(FXCollections.observableArrayList(controller.getAgenda().getEntries()));
    }

    /**
     * Handles the action of assigning one or more vehicles to an agenda entry.
     * It validates the selected vehicle and agenda entry and assigns the vehicle to the entry if they are valid.
     */
    @FXML
    private void handleAssignOneOrMoreVehicles() {
        Vehicle selectedVehicle = cbVehicles.getSelectionModel().getSelectedItem();
        Entry selectedEntry = cbAgendaEntry.getSelectionModel().getSelectedItem();

        if (selectedVehicle != null && selectedEntry != null) {
            controller.assignVehicle(selectedEntry, selectedVehicle);
            System.out.println("Vehicle assigned successfully.");
        } else {
            System.out.println("Please select both a vehicle and an entry.");
        }
    }
}

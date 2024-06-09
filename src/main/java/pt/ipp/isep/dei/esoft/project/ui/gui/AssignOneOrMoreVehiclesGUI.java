package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class AssignOneOrMoreVehiclesGUI {

    @FXML
    private ComboBox<Vehicle> cbVehicles;

    @FXML
    private ComboBox<Entry> cbAgendaEntry;
    @FXML
    private Button btnBack;
    @FXML
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    private AssignVehicleAgendaController controller;

    public AssignOneOrMoreVehiclesGUI() {
        controller = new AssignVehicleAgendaController();
    }

    @FXML
    public void initialize() {
        // Inicializa os ComboBoxes com dados
        cbVehicles.setItems(FXCollections.observableArrayList(controller.getVehicles()));
        cbAgendaEntry.setItems(FXCollections.observableArrayList(controller.getAgenda().getEntries()));
    }

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

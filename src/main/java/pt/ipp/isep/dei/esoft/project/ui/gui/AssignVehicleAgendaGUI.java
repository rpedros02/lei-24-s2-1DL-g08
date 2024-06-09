package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.showAlert;
import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.showSuccess;

public class AssignVehicleAgendaGUI {

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cbVehicles;

    @FXML
    private ComboBox<String> cbEntries;

    private final ToDoList toDoList = Repositories.getInstance().getOrganizationRepository().getOrganizationByEmployeeEmail(UtilsGUI.getLoggedInUserEmail()).getTodoList();
    private final VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

    public void initialize() {
        List<Entry> entries = toDoList.getEntries();
        if (entries.isEmpty()) {
            showAlert("There are no entries in the agenda.").showAndWait();
        } else {
            for (Entry entry : entries) {
                if (!entry.getStatus().toString().equals("Canceled")) {
                    cbEntries.getItems().add(entry.getTitle());
                }
            }
        }
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        if (vehicles.isEmpty()) {
            showAlert("There are no vehicles in the repository.").showAndWait();
        } else {
            for (Vehicle vehicle : vehicles) {
                cbVehicles.getItems().add(vehicle.getPlateId());
            }
        }
    }

    public void handleAssignVehicle() {
        String entryTitle = cbEntries.getValue();
        String vehiclePlateId = cbVehicles.getValue();
        if (entryTitle == null || vehiclePlateId == null) {
            showAlert("Please select an entry and a vehicle.").showAndWait();
        } else {
            Entry entry = toDoList.getEntryByTitle(entryTitle);
            Vehicle vehicle = vehicleRepository.getVehicleByPlate(vehiclePlateId);
            if (vehicle == null) {
                showAlert("Vehicle not found.").showAndWait();
            } else {
                entry.setVehicles(List.of(vehicle));
                showSuccess("Vehicle assigned to the entry successfully.").showAndWait();
            }
        }
    }

    public void handleGsm() {
        UtilsGUI.handleGSM(btnBack);
    }
}

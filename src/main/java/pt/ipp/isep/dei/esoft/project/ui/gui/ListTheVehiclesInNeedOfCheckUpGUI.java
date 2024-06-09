package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

public class ListTheVehiclesInNeedOfCheckUpGUI {

    private final VehicleController controller = new VehicleController();

    @FXML
    public ListView<String> vehicleView;

    @FXML
    public Button btnBack;



    public void handleListTheVehiclesInNeedOfCheckUp() {
        controller.getVehicleRepository().getVehiclesInNeedOfCheckUp();
        for (Vehicle vehicle : controller.getVehicleRepository().getVehiclesInNeedOfCheckUp()) {
            vehicleView.getItems().add(vehicle.getPlateId());
        }
    }

    public void handleVfm() {
        UtilsGUI.handleVFM(btnBack);
    }
}

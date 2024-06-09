package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for the Vehicle Fleet Manager (VFM).
 */
public class VfmGUI {

    @FXML
    // Button for registering a vehicle
    private Button btnRegisterVehicle;

    @FXML
    // Button for registering a vehicle checkup
    private Button btnRegisterVehicleCheckup;

    @FXML
    // Button for listing the vehicles in need of checkup
    private Button btnListTheVehiclesInNeedOfCheckup;

    @FXML
    // Button for returning to the main menu
    private Button btnMainMenu;

    /**
     * Initializes the user interface components.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {

    }

    /**
     * Handles the action of registering a vehicle.
     * It is triggered when the Register Vehicle button is clicked.
     * It closes the current stage and loads the RegisterVehicleGUI.
     */
    @FXML
    public void handleRegisterVehicle() {
        Stage stage = (Stage) btnRegisterVehicle.getScene().getWindow();
        loadUI("/RegisterVehicleGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of registering a vehicle checkup.
     * It is triggered when the Register Vehicle Checkup button is clicked.
     * It closes the current stage and loads the RegisterVehicleCheckupGUI.
     */
    @FXML
    public void handleRegisterVehicleCheckup() {
        Stage stage = (Stage) btnRegisterVehicleCheckup.getScene().getWindow();
        loadUI("/RegisterVehicleCheckupGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of listing the vehicles in need of checkup.
     * It is triggered when the List The Vehicles In Need Of Checkup button is clicked.
     * It closes the current stage and loads the ListTheVehiclesInNeedOfCheckupGUI.
     */
    @FXML
    public void handleListTheVehiclesInNeedOfCheckup() {
        Stage stage = (Stage) btnListTheVehiclesInNeedOfCheckup.getScene().getWindow();
        loadUI("/ListTheVehiclesInNeedOfCheckupGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of returning to the main menu.
     * It is triggered when the Main Menu button is clicked.
     * It closes the current stage and loads the user interface for the current user's role.
     */
    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        currentStage.close();
    }
}

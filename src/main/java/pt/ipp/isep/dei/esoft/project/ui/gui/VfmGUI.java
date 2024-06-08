package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class VfmGUI {

    @FXML
    private Button btnRegisterVehicle;

    @FXML
    private Button btnRegisterVehicleCheckup;

    @FXML
    private Button btnListTheVehiclesInNeedOfCheckup;

    @FXML
    private Button btnMainMenu;

    @FXML
    public void initialize() {
        // Initialize UI components if needed

    }

    @FXML
    public void handleRegisterVehicle() {
        Stage stage = (Stage) btnRegisterVehicle.getScene().getWindow();
        loadUI("/RegisterVehicleGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleRegisterVehicleCheckup() {
        Stage stage = (Stage) btnRegisterVehicleCheckup.getScene().getWindow();
        loadUI("/RegisterVehicleCheckupGUI.fxml");
        stage.close();
    }


    @FXML
    public void handleListTheVehiclesInNeedOfCheckup() {
        Stage stage = (Stage) btnListTheVehiclesInNeedOfCheckup.getScene().getWindow();
        loadUI("/ListTheVehiclesInNeedOfCheckupGUI.fxml");
        stage.close();
    }


    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        currentStage.close();
        loadUI("/MainMenuGUI.fxml");
    }

}

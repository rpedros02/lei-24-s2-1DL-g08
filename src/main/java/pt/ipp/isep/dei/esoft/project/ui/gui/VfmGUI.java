package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.CreateVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CheckUpsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CreateVehicleCheckupUI;

import java.io.IOException;

public class VfmGUI {

    @FXML
    private Button btnRegisterVehicle;

    @FXML
    private Button btnRegisterVehicleCheckup;

    @FXML
    private Button btnListTheVehiclesInNeedOfCheckup;

    @FXML
    private Button btnAssignSkill;

    @FXML
    private Button btnMainMenu;

    @FXML
    public void initialize() {
        // Initialize UI components if needed

    }

    @FXML
    public void handleRegisterVehicle() {
        loadUI("/RegisterVehicleGUI.fxml");
    }

    @FXML
    public void handleRegisterVehicleCheckup() {
        loadUI("/RegisterVehicleCheckupGUI.fxml");
    }


    @FXML
    public void handleListTheVehiclesInNeedOfCheckup() {
        loadUI("/ListTheVehiclesInNeedOfCheckupGUI.fxml");
    }
    @FXML
    public void handleAssignSkill() {
        loadUI("/AssignSkillGUI.fxml");
    }


    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        currentStage.close();
        loadUI("/MainMenuGUI.fxml");
    }


    private void loadUI(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

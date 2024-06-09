package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

public class AdminGUI {

    @FXML
    private Button btnMainMenu;

    @FXML
    public void handleHrm() {
        handleHRM(btnMainMenu);
    }

    @FXML
    public void handleVfm() {
        handleVFM(btnMainMenu);
    }

    @FXML
    public void handleGsm() {
        handleGSM(btnMainMenu);
    }

    @FXML
    public void handleCollaboratorMenu() {
        handleCollaborator(btnMainMenu);
    }

    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/MainMenuGUI.fxml");
        currentStage.close();
    }

}
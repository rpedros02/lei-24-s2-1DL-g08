package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class AdminGUI {

    @FXML
    private Button btnMainMenu;

    @FXML
    public void handleHrm() {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/HrmGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleVfm() {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/VfmGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleGsm() {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/GsmGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleCollaboratorMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/CollaboratorMenuGUI.fxml");
        currentStage.close();
    }

    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        currentStage.close();
    }

}
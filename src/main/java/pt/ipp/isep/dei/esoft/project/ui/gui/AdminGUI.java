package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminGUI {

    @FXML
    private Button btnMainMenu;

    @FXML
    public void handleHrm() {
        loadUI("/HrmGUI.fxml");
    }

    @FXML
    public void handleVfm() {
        loadUI("/VfmGUI.fxml");
    }

    @FXML
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    @FXML
    public void handleCollaboratorMenu() {
        loadUI("/CollaboratorMenuGUI.fxml");
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
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HrmGUI {

    @FXML
    private Button btnMainMenu;
    @FXML
    private Button btnRegisterSkill;
    @FXML
    private Button btnRegisterJob;
    @FXML
    private Button btnRegisterCollaborator;
    @FXML
    private Button btnAssignSkillsToACollaborator;
    @FXML
    private Button btnGenerateTeamProposal;

    @FXML
    public void initialize() {
        // Initialize UI components if needed

    }

    @FXML
    public void handleRegisterSkill() {
        loadUI("/RegisterSkillGUI.fxml");
    }

    @FXML
    public void handleRegisterJob() {
        loadUI("/RegisterJobGUI.fxml");
    }

    @FXML
    public void handleRegisterCollaborator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterCollaboratorGUI.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 900));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAssignSkillsToCollaborator() {
        loadUI("/AssignSkillsToCollaboratorGUI.fxml");
    }

    @FXML
    public void handleGenerateTeamProposal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GenerateTeamProposalGUI.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
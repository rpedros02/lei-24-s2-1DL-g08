package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;


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

    }

    @FXML
    public void handleRegisterSkill() {
        Stage stage = (Stage) btnRegisterSkill.getScene().getWindow();
        loadUI("/RegisterSkillGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleRegisterJob() {
        Stage stage = (Stage) btnRegisterJob.getScene().getWindow();
        loadUI("/RegisterJobGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleRegisterCollaborator() {
        Stage stage = (Stage) btnRegisterCollaborator.getScene().getWindow();
        loadUI("/RegisterCollaborator.fxml");
        stage.close();
    }

    @FXML
    public void handleAssignSkillsToCollaborator() {
        Stage stage = (Stage) btnAssignSkillsToACollaborator.getScene().getWindow();
        loadUI("/AssignSkillsToCollaboratorGUI.fxml");
        stage.close();
    }

    @FXML
    public void handleGenerateTeamProposal() {
        Stage stage = (Stage) btnGenerateTeamProposal.getScene().getWindow();
        loadUI("/GenerateTeamProposalGUI.fxml");
        stage.close();
    }


    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        currentStage.close();
    }


}
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;


/**
 * This class provides a user interface for managing human resources.
 */
public class HrmGUI {

    @FXML
    // Main menu button
    private Button btnMainMenu;

    @FXML
    // Button for registering a skill
    private Button btnRegisterSkill;

    @FXML
    // Button for registering a job
    private Button btnRegisterJob;

    @FXML
    // Button for registering a collaborator
    private Button btnRegisterCollaborator;

    @FXML
    // Button for assigning skills to a collaborator
    private Button btnAssignSkillsToACollaborator;

    @FXML
    // Button for generating a team proposal
    private Button btnGenerateTeamProposal;

    @FXML
    /**
     * Initializes the user interface.
     */
    public void initialize() {

    }

    /**
     * Handles the action of registering a skill.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the RegisterSkillGUI.
     */
    @FXML
    public void handleRegisterSkill() {
        Stage stage = (Stage) btnRegisterSkill.getScene().getWindow();
        loadUI("/RegisterSkillGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of registering a job.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the RegisterJobGUI.
     */
    @FXML
    public void handleRegisterJob() {
        Stage stage = (Stage) btnRegisterJob.getScene().getWindow();
        loadUI("/RegisterJobGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of registering a collaborator.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the RegisterCollaboratorGUI.
     */
    @FXML
    public void handleRegisterCollaborator() {
        Stage stage = (Stage) btnRegisterCollaborator.getScene().getWindow();
        loadUI("/RegisterCollaboratorGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of assigning skills to a collaborator.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the AssignSkillsToCollaboratorGUI.
     */
    @FXML
    public void handleAssignSkillsToCollaborator() {
        Stage stage = (Stage) btnAssignSkillsToACollaborator.getScene().getWindow();
        loadUI("/AssignSkillsToCollaboratorGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of generating a team proposal.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the GenerateTeamProposalGUI.
     */
    @FXML
    public void handleGenerateTeamProposal() {
        Stage stage = (Stage) btnGenerateTeamProposal.getScene().getWindow();
        loadUI("/GenerateTeamProposalGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of returning to the main menu.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the main menu user interface.
     */
    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        currentStage.close();
    }
}
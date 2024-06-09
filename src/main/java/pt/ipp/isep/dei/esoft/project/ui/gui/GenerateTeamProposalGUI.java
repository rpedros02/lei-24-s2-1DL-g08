package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for generating a team proposal.
 */
public class GenerateTeamProposalGUI {

    @FXML
    // TextField for entering the minimum team size
    private TextField txtMinTeamSize;

    @FXML
    // TextField for entering the maximum team size
    private TextField txtMaxTeamSize;

    @FXML
    // ComboBox for selecting the type of skill
    private ComboBox<String> cbType;

    @FXML
    // Label for displaying messages
    private Label lblMessage;

    @FXML
    // Button for going back
    private Button btnBack;

    @FXML
    private CheckBox chbSkills;


    /**
     * Handles the action of navigating to the HRM user interface.
     * It is triggered when the Back button is clicked.
     */
    @FXML
    public void handleHrm() {
        handleHRM(btnBack);
    }

    @FXML
    public void handleReturn() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
    // Controller for generating a team
    private final GenerateTeamController controller;

    // Repository for accessing skills
    private final SkillsRepository skillsRepository;

    /**
     * Constructs a GenerateTeamProposalGUI with a GenerateTeamController and a SkillsRepository.
     */
    public GenerateTeamProposalGUI() {
        this.controller = new GenerateTeamController();
        this.skillsRepository = Repositories.getInstance().getSkillsRepository();
    }

    /**
     * Initializes the user interface.
     * It populates the ComboBox with the names of all skills.
     */
    @FXML
    private void initialize() {
        List<Skill> skills = skillsRepository.getAllSkills();
        for (Skill skill : skills) {
            cbType.getItems().add(skill.getName());
        }
    }

    public List<Skill> handleAddSkills() {
        loadUI("SelectSkills.fxml");
        return Repositories.getInstance().getSkillsRepository().getAllSkills();
    }


    /**
     * Handles the action of generating a team proposal.
     * <p>
     * It is triggered when the Generate Team Proposal button is clicked.
     * It reads the minimum and maximum team sizes and the selected skill.
     */
    @FXML
    private void handleGenerateTeamProposal() {
        try {
            int minTeamSize = Integer.parseInt(txtMinTeamSize.getText());
            int maxTeamSize = Integer.parseInt(txtMaxTeamSize.getText());
            String selectedSkill = cbType.getValue();

            if (selectedSkill == null || selectedSkill.isEmpty()) {
                showAlert("Please select a skill.");
                return;
            }

            Skill skill = skillsRepository.getSkillByName(selectedSkill);
            List<Skill> teamSkills = List.of(skill);

            Team team = controller.generateTeam(minTeamSize, maxTeamSize, teamSkills);

            if(controller.registerTeam(team)){
                showSuccess("Team successfully generated and registered.");
            } else {
                showAlert("Error generating team.");
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter valid team sizes.");
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }
}
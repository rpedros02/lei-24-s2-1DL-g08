package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.ArrayList;
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
    private ComboBox<String> cbSkills;

    @FXML
    // Button for going back
    private Button btnBack;

    /**
     * Handles the action of navigating to the HRM user interface.
     * It is triggered when the Back button is clicked.
     */
    @FXML
    public void handleHrm() {
        handleHRM(btnBack);
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
            cbSkills.getItems().add(skill.getName());
        }
    }

    /**
     * Handles the action of generating a team proposal.
     * It is triggered when the Generate button is clicked.
     * It validates the entered team sizes and selected skill, generates a team with the specified sizes and skill, and registers the team.
     * It displays a success message if the team is successfully generated and registered, or an error message if an error occurs.
     */
    @FXML
    private void handleGenerateTeamProposal() {
        try {
            int minTeamSize = Integer.parseInt(txtMinTeamSize.getText());
            int maxTeamSize = Integer.parseInt(txtMaxTeamSize.getText());
            String selectedSkill = cbSkills.getValue();

            if (selectedSkill == null || selectedSkill.isEmpty()) {
                showAlert("Please select a skill.").showAndWait();
                return;
            }

            Skill skill = skillsRepository.getSkillByName(selectedSkill);
            List<Skill> teamSkills = new ArrayList<>(List.of(skill));

            Team team = controller.generateTeam(minTeamSize, maxTeamSize, teamSkills);

            controller.registerTeam(team);

            showSuccess("Team generated and registered successfully.").showAndWait();
        } catch (NumberFormatException e) {
            showAlert("Please enter valid team sizes.").showAndWait();
        } catch (Exception e) {
            showAlert(e.getMessage()).showAndWait();
        }
    }
}
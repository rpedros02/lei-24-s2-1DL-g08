package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.List;
import java.util.stream.Collectors;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;
import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.showAlert;

public class GenerateTeamProposalGUI {

    @FXML
    private TextField txtMinTeamSize;

    @FXML
    private TextField txtMaxTeamSize;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnBack;
    @FXML
    public void handleHrm() {
        loadUI("/HrmGUI.fxml");
    }

    private final GenerateTeamController controller;
    private final SkillsRepository skillsRepository;

    public GenerateTeamProposalGUI() {
        this.controller = new GenerateTeamController();
        this.skillsRepository = Repositories.getInstance().getSkillsRepository();
    }

    @FXML
    private void initialize() {
        List<Skill> skills = skillsRepository.getAllSkills();
        for (Skill skill : skills) {
            cbType.getItems().add(skill.getName());
        }
    }

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

            controller.registerTeam(team);

            lblMessage.setText("Team successfully generated and registered!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Please enter valid numbers for team sizes.");
        } catch (Exception e) {
            lblMessage.setText("Error generating team: " + e.getMessage());
        }
    }
}
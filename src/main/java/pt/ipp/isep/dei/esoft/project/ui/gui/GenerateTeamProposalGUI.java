package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class GenerateTeamProposalGUI {

    @FXML
    private TextField txtMinTeamSize;
    @FXML
    private TextField txtMaxTeamSize;
    @FXML
    private TextField txtSkillName;
    @FXML
    private Button btnCreateTeam;
    @FXML
    private Label lblMessage;

    private GenerateTeamController controller;

    public GenerateTeamProposalGUI() {
        controller = new GenerateTeamController();
    }

    @FXML
    private void handleGenerateTeamProposal() {
        try {
            int minTeamSize = Integer.parseInt(txtMinTeamSize.getText());
            int maxTeamSize = Integer.parseInt(txtMaxTeamSize.getText());
            String skillName = txtSkillName.getText();

            if (minTeamSize <= 0 || maxTeamSize <= 0 || skillName.isEmpty()) {
                lblMessage.setText("All fields must be filled correctly.");
                return;
            }

            List<Skill> teamSkills = new ArrayList<>();
            teamSkills.add(new Skill(1, skillName)); // Assuming a skill ID for simplicity

            Team team = controller.generateTeam(minTeamSize, maxTeamSize, teamSkills);
            controller.registerTeam(team);

            lblMessage.setText("Team successfully generated!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Team sizes must be valid numbers.");
        } catch (Exception e) {
            lblMessage.setText("An error occurred while generating the team.");
        }
    }
}
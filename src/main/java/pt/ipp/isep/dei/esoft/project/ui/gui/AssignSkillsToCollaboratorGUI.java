package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class AssignSkillsToCollaboratorGUI {

    @FXML
    private ComboBox<Collaborator> cbCollaborator;

    @FXML
    private ComboBox<Skill> cbSkill;

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnBack;
    @FXML
    public void handleHrm() {
        loadUI("/HrmGUI.fxml");
    }

    private final AssignSkillController controller;

    public AssignSkillsToCollaboratorGUI() {
        this.controller = new AssignSkillController();
    }

    @FXML
    public void initialize() {
        List<Collaborator> collaborators = controller.getCollabortorList();
        cbCollaborator.getItems().addAll(collaborators);

        List<Skill> skills = controller.getSkillList();
        cbSkill.getItems().addAll(skills);
    }

    @FXML
    private void handleAssignSkillsToCollaborator(ActionEvent event) {
        Collaborator selectedCollaborator = cbCollaborator.getValue();
        Skill selectedSkill = cbSkill.getValue();

        if (selectedCollaborator != null && selectedSkill != null) {
            controller.assignSkills(selectedCollaborator, List.of(selectedSkill));
            lblMessage.setText("Skill assigned successfully!");
        } else {
            lblMessage.setText("Please select a collaborator and a skill.");
        }
    }
}

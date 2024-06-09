package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

public class AssignSkillsToCollaboratorGUI {

    @FXML
    private ComboBox<String> cbCollaborator;

    @FXML
    private ComboBox<String> cbSkill;

    @FXML
    private Button btnBack;

    private final AssignSkillController controller;

    @FXML
    public void handleReturn() {
        UtilsGUI.handleHRM(btnBack);
    }


    public AssignSkillsToCollaboratorGUI() {
        this.controller = new AssignSkillController();
    }

    @FXML
    public void initialize() {
        List<Collaborator> collaborators = controller.getCollabortorList();
        for ( Collaborator collaborator : collaborators
             ) {
            cbCollaborator.getItems().add(STR."\{collaborator.getName()} - \{collaborator.getEmail()}");
        }

        List<Skill> skills = controller.getSkillList();
        for (Skill skill : skills) {
            cbSkill.getItems().add(skill.getName());
        }
    }

    @FXML
    private void handleAssignSkillsToCollaborator() {

        Collaborator selectedCollaborator = controller.getCollaboratorByEmail(cbCollaborator.getValue().split(" - ")[1].trim());
        Skill selectedSkill = controller.getSkillByName(cbSkill.getValue());

        if (selectedCollaborator != null && selectedSkill != null) {
            controller.assignSkills(selectedCollaborator, List.of(selectedSkill));
            UtilsGUI.showSuccess("Skill assigned successfully!").showAndWait();
        } else {
            UtilsGUI.showAlert("Please select a collaborator and a skill.");
        }
    }
}

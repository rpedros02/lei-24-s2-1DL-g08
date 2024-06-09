package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

/**
 * This class provides a user interface for assigning skills to a collaborator.
 */
public class AssignSkillsToCollaboratorGUI {

    @FXML
    // ComboBox for selecting a collaborator
    private ComboBox<String> cbCollaborator;

    @FXML
    // ComboBox for selecting a skill
    private ComboBox<String> cbSkill;

    @FXML
    // Button for going back
    private Button btnBack;

    // Controller for assigning skills
    private final AssignSkillController controller;

    /**
     * Handles the action of returning to the previous user interface.
     * It is triggered when the Back button is clicked.
     */
    @FXML
    public void handleReturn() {
        UtilsGUI.handleHRM(btnBack);
    }

    /**
     * Constructs a new instance of AssignSkillsToCollaboratorGUI.
     * It initializes the controller.
     */
    public AssignSkillsToCollaboratorGUI() {
        this.controller = new AssignSkillController();
    }

    /**
     * Initializes the user interface.
     * It populates the ComboBoxes with the collaborators and skills.
     */
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

    /**
     * Handles the action of assigning skills to a collaborator.
     * It validates the selected collaborator and skill and assigns the skill to the collaborator if they are valid.
     */
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

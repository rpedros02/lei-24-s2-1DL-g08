package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.SkillController;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.handleHRM;
import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.showAlert;

/**
 * This class provides a user interface for registering a skill.
 */
public class RegisterSkillGUI {

    @FXML
    // TextField for entering the skill's ID
    private TextField txtSkillId;

    @FXML
    // TextField for entering the skill's name
    private TextField txtSkillName;

    @FXML
    // Label for displaying messages
    private Label lblMessage;

    @FXML
    // Button for going back to the previous user interface
    private Button btnBack;

    // Controller for managing skills
    private final SkillController controller = new SkillController();

    @FXML
    /**
     * Handles the action of returning to the previous user interface.
     * It is triggered when the Back button is clicked.
     */
    public void handleReturn() {
        handleHRM(btnBack);
    }

    @FXML
    /**
     * Handles the action of registering a skill.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, creates a skill if the input is valid, and displays a message indicating the result.
     */
    private void handleRegisterSkill() {
        String skillIdStr = txtSkillId.getText();
        String skillName = txtSkillName.getText();

        if (skillIdStr.isEmpty() || skillName.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        int skillId;
        try {
            skillId = Integer.parseInt(skillIdStr);
        } catch (NumberFormatException e) {
            showAlert("Skill ID must be a valid integer.");
            return;
        }

        if (controller.RegisterSkill(skillId, skillName)) {
            lblMessage.setText("Skill registered successfully!");
        } else {
            lblMessage.setText("Failed to register skill. Skill might already exist.");
        }
    }

}

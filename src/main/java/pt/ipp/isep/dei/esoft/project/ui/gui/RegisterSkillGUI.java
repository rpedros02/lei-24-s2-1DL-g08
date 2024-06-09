package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.SkillController;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.handleHRM;
import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.showAlert;

public class RegisterSkillGUI {

    @FXML
    private TextField txtSkillId;

    @FXML
    private TextField txtSkillName;
    @FXML
    private Label lblMessage;
    @FXML
    private Button btnBack;

    private final SkillController controller = new SkillController();

    @FXML
    public void handleReturn() {
        handleHRM(btnBack);
    }

    @FXML
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

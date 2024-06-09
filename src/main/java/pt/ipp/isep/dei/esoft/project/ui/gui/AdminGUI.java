package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for the admin.
 */
public class AdminGUI {

    @FXML
    // Button for navigating to the main menu
    private Button btnMainMenu;

    @FXML
    /**
     * Handles the action of navigating to the HRM user interface.
     * It is triggered when the HRM button is clicked.
     */
    public void handleHrm() {
        handleHRM(btnMainMenu);
    }

    @FXML
    /**
     * Handles the action of navigating to the VFM user interface.
     * It is triggered when the VFM button is clicked.
     */
    public void handleVfm() {
        handleVFM(btnMainMenu);
    }

    @FXML
    /**
     * Handles the action of navigating to the GSM user interface.
     * It is triggered when the GSM button is clicked.
     */
    public void handleGsm() {
        handleGSM(btnMainMenu);
    }

    @FXML
    /**
     * Handles the action of navigating to the Collaborator user interface.
     * It is triggered when the Collaborator button is clicked.
     */
    public void handleCollaboratorMenu() {
        handleCollaborator(btnMainMenu);
    }

    @FXML
    /**
     * Handles the action of navigating to the main menu.
     * It is triggered when the Main Menu button is clicked.
     * It closes the current stage and loads the main menu user interface.
     */
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/MainMenuGUI.fxml");
        currentStage.close();
    }

}
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for the development team.
 */
public class DevTeamGUI {

    @FXML
    // Label for displaying a welcome message
    private Label lblMessage;

    @FXML
    // Button for returning to the previous user interface
    private Button btnReturn;

    @FXML
    /**
     * Initializes the user interface.
     * It sets the text of the welcome message label.
     */
    public void initialize() {
        lblMessage.setText("Bem-vindo à equipa de desenvolvimento!");
    }

    @FXML
    /**
     * Handles the action of returning to the previous user interface.
     * It is triggered when the Return button is clicked.
     * It closes the current stage and loads the main menu user interface.
     */
    public void handleReturn() {
        Stage stage = (Stage) this.btnReturn.getScene().getWindow();
        loadUI("/MainMenuGUI.fxml");
        stage.close();
    }

}

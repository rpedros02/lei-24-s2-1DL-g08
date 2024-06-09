package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for registering a job.
 */
public class RegisterJobGUI {

    @FXML
    // TextField for entering the job's name
    private TextField txtJobName;

    @FXML
    // Label for displaying messages
    private Label lblMessage;

    @FXML
    // Button for going back to the HRM user interface
    private Button btnBack;

    @FXML
    /**
     * Handles the action of going back to the HRM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the HrmGUI.
     */
    public void handleHrm() {
        loadUI("/HrmGUI.fxml");
    }

    // Controller for creating a job
    private final CreateJobController controller = new CreateJobController();

    @FXML
    /**
     * Handles the action of registering a job.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, creates a job if the input is valid, and displays a message indicating the result.
     */
    private void handleRegisterJob() {
        String jobName = txtJobName.getText();
        if (jobName.isEmpty()) {
            showAlert("Please enter the job name.");
            return;
        }

        if (controller.generateJob(jobName)) {
            lblMessage.setText("Job created successfully!");
        } else {
            lblMessage.setText("Failed to create job.");
        }
    }

    /**
     * Displays an error alert with the specified message.
     * @param message the message to display
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class RegisterJobGUI {

    @FXML
    private TextField txtJobName;

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnBack;
    @FXML
    public void handleHrm() {
        loadUI("/HrmGUI.fxml");
    }

    private final CreateJobController controller = new CreateJobController();

    @FXML
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
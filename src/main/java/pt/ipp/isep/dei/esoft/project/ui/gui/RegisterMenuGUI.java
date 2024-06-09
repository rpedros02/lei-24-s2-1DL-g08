package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * This class provides a user interface for registering a user with a specific role.
 */
public class RegisterMenuGUI {

    @FXML
    // TextField for entering the user's email
    private TextField txtEmail;

    @FXML
    // PasswordField for entering the user's password
    private PasswordField txtPassword;

    @FXML
    // PasswordField for confirming the user's password
    private PasswordField txtConfirmPassword;

    @FXML
    // ComboBox for selecting the user's role
    private ComboBox<String> cbRole;

    // Repository for accessing authentication data
    private AuthenticationRepository authenticationRepository;

    /**
     * Constructs a RegisterMenuGUI with an AuthenticationRepository.
     */
    public RegisterMenuGUI() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    @FXML
    /**
     * Handles the action of registering a user.
     * It is triggered when the Register button is clicked.
     * It validates the input, creates a user if the input is valid, and displays a message indicating the result.
     */
    public void handleRegister() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String role = cbRole.getValue();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled out.");
            alert.showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match.");
            alert.showAndWait();
            return;
        }

        boolean userAdded = authenticationRepository.addUserWithRole(email, password, confirmPassword, role);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (userAdded) {
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("User has been successfully registered.");

            Stage stage = (Stage) cbRole.getScene().getWindow();
            stage.close();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("User registration failed. Please try again.");
        }
        alert.showAndWait();
    }
}

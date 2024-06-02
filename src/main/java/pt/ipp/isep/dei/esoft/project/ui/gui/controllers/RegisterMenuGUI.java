package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterMenuGUI {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> cbRole;

    @FXML
    private PasswordField txtConfirmPassword;

    private AuthenticationRepository authenticationRepository;

    public RegisterMenuGUI() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    @FXML
    public void handleRegister() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String role = cbRole.getValue();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role == null) {
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

        boolean userAdded = authenticationRepository.addUserWithRole(name, email, password, role);

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

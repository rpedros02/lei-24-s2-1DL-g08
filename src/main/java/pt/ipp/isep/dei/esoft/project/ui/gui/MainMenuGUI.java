package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainMenuGUI{

    @FXML
    private VBox mainBox;

    @FXML
    private TextField txtLoginId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnDevTeam;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        mainBox.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Handles the login button action.
     */
    @FXML
    public void handleLogin() {
        AuthenticationController ctrl = new AuthenticationController();

        String id = txtLoginId.getText();
        String password = txtPassword.getText();

        boolean loginSuccessful = ctrl.doLogin(id, password);
        if (loginSuccessful) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No role assigned to user.");
                alert.showAndWait();
            } else {
                UserRoleDTO role = roles.get(0); // Get the single role directly
                switch (role.getDescription()) {
                    case AuthenticationController.ROLE_HRM:
                        loadHrmMenu();

                        Stage stage = (Stage) btnDevTeam.getScene().getWindow();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_VFM:
                        loadVfmMenu();

                        stage = (Stage) btnDevTeam.getScene().getWindow();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_GSM:
                        loadGsmMenu();

                        stage = (Stage) btnDevTeam.getScene().getWindow();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_COLLABORATOR:
                        handleCollaboratorMenu();

                        stage = (Stage) btnDevTeam.getScene().getWindow();
                        stage.close();
                        break;
                    default:
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Role not recognized.");
                        alert.showAndWait();
                        break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Login failed. Wrong credentials.");
            alert.showAndWait();
        }
        txtLoginId.clear();
        txtPassword.clear();
    }

    /**
     * Loads the HRM menu.
     */
    private void loadHrmMenu() {
        loadUI("/HrmGUI.fxml");
    }

    /**
     * Loads the VFM menu.
     */
    private void loadVfmMenu() {
        loadUI("/VfmGUI.fxml");
    }

    /**
     * Handles the DevTeam button action.
     */
    @FXML
    public void handleDevTeam() {
        loadUI("/DevTeamGUI.fxml");
    }

    /**
     * Loads the GSM menu.
     */
    private void loadGsmMenu() {
        loadUI("/GsmGUI.fxml");
    }

    /**
     * Handles the Register button action.
     */
    @FXML
    public void handleRegister() {
        loadUI("/RegisterMenuGUI.fxml");
    }

    @FXML
    public void handleCollaboratorMenu() {
        loadUI("/CollaboratorGUI.fxml");
    }

    /**
     * Loads the UI from a FXML file.
     *
     * @param fxmlPath the FXML file path
     */
    private void loadUI(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
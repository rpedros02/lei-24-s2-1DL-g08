package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;
import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.showAlert;

public class MainMenuGUI {

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
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                showAlert("No roles found.");
            } else {
                UserRoleDTO role = roles.getFirst(); // Get the single role directly
                Stage stage = (Stage) btnDevTeam.getScene().getWindow();
                switch (role.getDescription()) {
                    case AuthenticationController.ROLE_HRM:
                        loadHrmMenu();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_VFM:
                        loadVfmMenu();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_GSM:
                        loadGsmMenu();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_COLLABORATOR:
                        handleCollaboratorMenu();
                        stage.close();
                        break;
                    case AuthenticationController.ROLE_ADMIN:
                        loadAdminMenu();
                        stage.close();
                        break;
                    default:
                        showAlert("Role not recognized.").showAndWait();
                        break;
                }
            }
        } else {
            showAlert("Login failed. Double-check your credentials.").showAndWait();
        }
        txtLoginId.clear();
        txtPassword.clear();
    }

    /**
     * Loads the HRM menu.
     */
    private void loadHrmMenu() {
        UtilsGUI.handleHRM(btnDevTeam);
    }

    private void loadAdminMenu() {
        Stage stage = (Stage) btnDevTeam.getScene().getWindow();
        loadUI("/AdminGUI.fxml");
        stage.close();
    }

    /**
     * Loads the VFM menu.
     */
    private void loadVfmMenu() {
        UtilsGUI.handleVFM(btnDevTeam);
    }

    /**
     * Handles the DevTeam button action.
     */
    @FXML
    public void handleDevTeam() {
        Stage stage = (Stage) btnDevTeam.getScene().getWindow();
        loadUI("/DevTeamGUI.fxml");
        stage.close();
    }

    /**
     * Loads the GSM menu.
     */
    private void loadGsmMenu() {
        UtilsGUI.handleGSM(btnDevTeam);
    }

    @FXML
    public void handleCollaboratorMenu() {
        UtilsGUI.handleCollaborator(btnDevTeam);
    }

}
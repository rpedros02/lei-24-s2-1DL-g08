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

/**
 * This class provides the main user interface for the application.
 */
public class MainMenuGUI {

    @FXML
    // The main VBox layout for the user interface
    private VBox mainBox;

    @FXML
    // The TextField for the user to enter their login ID
    private TextField txtLoginId;

    @FXML
    // The PasswordField for the user to enter their password
    private PasswordField txtPassword;

    @FXML
    // The Button for the user to switch to the DevTeam user interface
    private Button btnDevTeam;

    /**
     * Initializes the user interface.
     * This method is called after all @FXML annotated members have been injected.
     */
    @FXML
    public void initialize() {
        mainBox.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Handles the action of the user clicking the login button.
     * This method attempts to log the user in with the entered login ID and password.
     * If the login is successful, it switches to the user interface corresponding to the user's role.
     * If the login is not successful, it displays an error message.
     */
    @FXML
    public void handleLogin() {
        AuthenticationController ctrl = new AuthenticationController();

        String id = txtLoginId.getText();
        String password = txtPassword.getText();

        boolean loginSuccessful = ctrl.doLogin(id, password);
        if (loginSuccessful) {
            new Alert(Alert.AlertType.INFORMATION);
            Alert alert;
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
                    case AuthenticationController.ROLE_ADMIN:
                        loadAdminMenu();

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
            showAlert("Login failed. Double-check your credentials.").showAndWait();
        }
        txtLoginId.clear();
        txtPassword.clear();
    }

    /**
     * Loads the HRM menu.
     * This method is called when the user's role is HRM.
     */
    private void loadHrmMenu() {
        UtilsGUI.handleHRM(btnDevTeam);
    }

    /**
     * Loads the Admin menu.
     * This method is called when the user's role is Admin.
     */
    private void loadAdminMenu() {
        Stage stage = (Stage) btnDevTeam.getScene().getWindow();
        loadUI("/AdminGUI.fxml");
        stage.close();
    }

    /**
     * Loads the VFM menu.
     * This method is called when the user's role is VFM.
     */
    private void loadVfmMenu() {
        UtilsGUI.handleVFM(btnDevTeam);
    }

    /**
     * Handles the action of the user clicking the DevTeam button.
     * This method switches to the DevTeam user interface.
     */
    @FXML
    public void handleDevTeam() {
        Stage stage = (Stage) btnDevTeam.getScene().getWindow();
        loadUI("/DevTeamGUI.fxml");
        stage.close();
    }

    /**
     * Loads the GSM menu.
     * This method is called when the user's role is GSM.
     */
    private void loadGsmMenu() {
        UtilsGUI.handleGSM(btnDevTeam);
    }

    /**
     * Handles the action of the user clicking the Collaborator button.
     * This method switches to the Collaborator user interface.
     */
    @FXML
    public void handleCollaboratorMenu() {
        UtilsGUI.handleCollaborator(btnDevTeam);
    }

}
package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This class provides utility methods for the GUI.
 */
public class UtilsGUI {

    /**
     * Returns the path to the FXML file for the current user's role.
     * @return the path to the FXML file
     */
    public static String getCurrentRoleXml(){
        UserRole role = ApplicationSession.getInstance().getCurrentSession().getUserRole();
        if(role.hasId("admin")){
            return("/AdminGUI.fxml");
        } else if (role.hasId("gsm")) {
            return("/GsmGUI.fxml");
        } else if (role.hasId("hrm")) {
            return("/HrmGUI.fxml");
        } else if (role.hasId("vfm")) {
            return("/VfmGUI.fxml");
        } else{
            return("/CollaboratorMenuGUI.fxml");
        }
    }

    /**
     * Loads the user interface from the specified FXML file.
     * @param fxmlPath the path to the FXML file
     */
    public static void loadUI(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(UtilsGUI.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            showAlert(e.getMessage());
        }
    }

    /**
     * Displays an error alert with the specified message.
     * @param message the message to display
     * @return the created Alert
     */
    public static Alert showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    /**
     * Displays a success alert with the specified message.
     * @param message the message to display
     * @return the created Alert
     */
    public static Alert showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    /**
     * Handles the action of returning to the HRM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the HrmGUI.
     * @param btnBack the Back button
     */
    public static void handleHRM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/HrmGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of returning to the GSM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the GsmGUI.
     * @param btnBack the Back button
     */
    public static void handleGSM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/GsmGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of returning to the VFM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the VfmGUI.
     * @param btnBack the Back button
     */
    public static void handleVFM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/VfmGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of returning to the Collaborator user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the CollaboratorMenuGUI.
     * @param btnBack the Back button
     */
    public static void handleCollaborator(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/CollaboratorMenuGUI.fxml");
        stage.close();
    }

    public static String getLoggedInUserEmail(){
        return ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    /**
     * @param localDate the local date to be converted
     *                  <p>
     *                  Converts a local date to a date
     *                  <p>
     * @return the date
     */
    public static Date convertToDate(LocalDate localDate) {
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }
}

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

public class UtilsGUI {

    /**
     * @return the xml file that corresponds to the current role
     * @see UserRole
     * @see ApplicationSession
     * @see UserRole#hasId(String)
     * @see ApplicationSession#getCurrentSession()
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
     * @param fxmlPath the path to the fxml file
     *                 <p>
     *                 Loads the fxml file
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
     * @param message the message to be shown
     *                <p>
     *                Shows an alert with the message
     *                <p>
     * @return the alert
     */
    public static Alert showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    /**
     * @param message the message to be shown
     *                <p>
     *                Shows an alert with the message
     *                <p>
     * @return the alert
     */
    public static Alert showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    /**
     * @param btnBack the button that will be used to go back
     *                <p>
     *                Handles the back button for the HRM
     *                <p>
     */
    public static void handleHRM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/HrmGUI.fxml");
        stage.close();
    }

    /**
     * @param btnBack the button that will be used to go back
     *                <p>
     *                Handles the back button for the GSM
     */
    public static void handleGSM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/GsmGUI.fxml");
        stage.close();
    }

    /**
     * @param btnBack the button that will be used to go back
     *                <p>
     *                Handles the back button for the VFM
     *                <p>
     */
    public static void handleVFM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/VfmGUI.fxml");
        stage.close();
    }

    /**
     * @param btnBack  the button that will be used to go back
     *                 <p>
     *                  Handles the back button for the Admin
     *                 <p>
     */
    public static void handleCollaborator(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/CollaboratorMenuGUI.fxml");
        stage.close();
    }

    /**
     * @return the logged in collaborator
     */
    public static String getLoggedInUserEmail() {
        ApplicationSession appSession = ApplicationSession.getInstance();
        return appSession.getCurrentSession().getUserEmail();
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

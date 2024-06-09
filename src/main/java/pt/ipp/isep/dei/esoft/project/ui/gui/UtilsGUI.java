package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.io.IOException;

public class UtilsGUI {
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

    public static Alert showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    public static Alert showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    public static void handleHRM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/HrmGUI.fxml");
        stage.close();
    }

    public static void handleGSM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/GsmGUI.fxml");
        stage.close();
    }

    public static void handleVFM(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/VfmGUI.fxml");
        stage.close();
    }

    public static void handleCollaborator(Button btnBack) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        loadUI("/CollaboratorMenuGUI.fxml");
        stage.close();
    }
}

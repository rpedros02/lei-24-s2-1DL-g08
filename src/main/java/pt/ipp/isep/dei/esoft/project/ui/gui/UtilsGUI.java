package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.io.IOException;

import static pt.ipp.isep.dei.esoft.project.ui.Main.showAlert;

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
}

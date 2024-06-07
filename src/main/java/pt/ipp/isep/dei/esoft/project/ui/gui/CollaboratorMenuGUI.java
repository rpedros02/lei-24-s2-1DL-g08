package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CollaboratorMenuGUI {

    @FXML
    private Button btnMainMenu;

    /**
     * Handles the Consult Tasks button action.
     */
    @FXML
    public void handleConsultTheTasksAssignInTheBetweenDates() {
        loadUI("/ConsultTheTasksAssignInTheBetweenDatesGUI.fxml");
    }

    /**
     * Handles the Record Task Completion button action.
     */
    @FXML
    public void handleRecordTheCompletionOfTask() {
        loadUI("/RecordTheCompletionOfTaskGUI.fxml");
    }

    /**
     * Handles the MainMenu button action.
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

    /**
     * Handles the MainMenu button action.
     */
    @FXML
    public void handleMainMenu( ) {
        loadUI("/MainMenuGUI.fxml");

        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.close();
    }
}
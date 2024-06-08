package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

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
    @FXML
    public void handleMainMenu( ) {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        stage.close();
    }
}
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class CollaboratorMenuGUI {

    @FXML
    private Button btnConsultTheTasksAssignInTheBetweenDates;

    @FXML
    private Button btnRecordTheCompletionOfTask;

    @FXML
    private Button btnMainMenu;

    /**
     * Handles the Consult Tasks button action.
     */
    @FXML
    public void handleConsultTheTasksAssignInTheBetweenDates() {
        Stage stage = (Stage) btnConsultTheTasksAssignInTheBetweenDates.getScene().getWindow();
        loadUI("/ConsultTheTasksAssignInTheBetweenDatesGUI.fxml");
        stage.close();
    }

    /**
     * Handles the Record Task Completion button action.
     */
    @FXML
    public void handleRecordTheCompletionOfTask() {
        Stage stage = (Stage) btnRecordTheCompletionOfTask.getScene().getWindow();
        loadUI("/RecordTheCompletionOfTaskGUI.fxml");
        stage.close();
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
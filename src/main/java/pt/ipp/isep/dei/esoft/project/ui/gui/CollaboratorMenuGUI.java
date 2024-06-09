package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for the collaborator menu.
 */
public class CollaboratorMenuGUI {

    @FXML
    // Button for consulting the tasks assigned in the between dates
    private Button btnConsultTheTasksAssignInTheBetweenDates;

    @FXML
    // Button for recording the completion of a task
    private Button btnRecordTheCompletionOfTask;

    @FXML
    // Button for navigating to the main menu
    private Button btnMainMenu;

    /**
     * Handles the action of consulting the tasks assigned in the between dates.
     * It is triggered when the Consult The Tasks Assign In The Between Dates button is clicked.
     * It closes the current stage and loads the Consult The Tasks Assign In The Between Dates user interface.
     */
    @FXML
    public void handleConsultTheTasksAssignInTheBetweenDates() {
        Stage stage = (Stage) btnConsultTheTasksAssignInTheBetweenDates.getScene().getWindow();
        loadUI("/ConsultTheTasksAssignInTheBetweenDatesGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of recording the completion of a task.
     * It is triggered when the Record The Completion Of Task button is clicked.
     * It closes the current stage and loads the Record The Completion Of Task user interface.
     */
    @FXML
    public void handleRecordTheCompletionOfTask() {
        Stage stage = (Stage) btnRecordTheCompletionOfTask.getScene().getWindow();
        loadUI("/RecordTheCompletionOfTaskGUI.fxml");
        stage.close();
    }

    /**
     * Handles the action of navigating to the main menu.
     * It is triggered when the Main Menu button is clicked.
     * It closes the current stage and loads the main menu user interface.
     */
    @FXML
    public void handleMainMenu( ) {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        stage.close();
    }
}
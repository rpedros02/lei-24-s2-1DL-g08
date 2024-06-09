package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

/**
 * This class provides a user interface for recording the completion of a task.
 */
public class RecordTheCompletionOfTaskGUI {

    @FXML
    // ComboBox for selecting an entry
    private ComboBox<Entry> cbEntries;

    @FXML
    // ListView for displaying the entries
    private ListView<Entry> agendaListView;

    // Controller for managing the agenda
    private final AgendaController agendaController = new AgendaController();

    @FXML
    /**
     * Initializes the user interface.
     * This method is called after all @FXML annotated members have been injected.
     * It sets up the ComboBox with the available tasks.
     */
    public void initialize() {
        // Load available tasks
        cbEntries.getItems().addAll(/* Call the appropriate method to get the available tasks */);
    }

    @FXML
    /**
     * Handles the action of recording the completion of a task.
     * It is triggered when the corresponding button is clicked.
     * It gets the selected entry from the ComboBox and records its completion.
     * @param event the action event
     */
    void handleRecordTheCompletionOfTask(ActionEvent event) {
        Entry selectedEntry = cbEntries.getValue();
        // Call the appropriate method of the controller to record the completion of the selected task
    }
}

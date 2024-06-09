package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for cancelling an entry in the agenda.
 */
public class CancelAnEntryGUI {

    @FXML
    private ComboBox<String> comboBoxEntry;

    @FXML
    private Button btnCancelAnEntryInTheAgenda;

    @FXML
    private Button btnBack;

    private final CancelAnEntryController cancelAnEntryController = new CancelAnEntryController();

    /**
     * Handles the action of navigating to the GSM user interface.
     * It is triggered when the GSM button is clicked.
     */
    @FXML
    public void handleGsm() {
        handleGSM(btnBack);
    }

    public CancelAnEntryGUI() {

    }

    /**
     * Initializes the user interface.
     * It populates the ComboBox with the entries in the agenda.
     */
    @FXML
    public void initialize() {
        Organization organization = Repositories.getInstance().getOrganizationRepository().getOrganizationByEmployeeEmail(UtilsGUI.getLoggedInUserEmail());
        ToDoList toDoList = organization.getTodoList();
        List<Entry> entries = toDoList.getEntries();
        if (entries.isEmpty()) {
            showAlert("There are no entries in the agenda.").showAndWait();
        } else {
            for (Entry entry : entries) {
                if(!entry.getStatus().toString().equals("Canceled")) {
                    comboBoxEntry.getItems().add(entry.getTitle());
                }
            }
        }
    }

    /**
     * Handles the action of cancelling an entry.
     * It is triggered when the Cancel An Entry button is clicked.
     * It validates the selected entry and cancels it if it is valid.
     * It also updates the result label with the result of the operation.
     *
     * @param event the event that triggered the action
     */
    @FXML
    void handleCancelAnEntry() {
        String title = comboBoxEntry.getValue();
        if (cancelAnEntryController.cancelEntry(title)) {
            showSuccess("Entry cancelled successfully.").showAndWait();
        } else {
            showAlert("An error occurred. Please try again.").showAndWait();
        }
        handleGSM(btnBack);
    }
}

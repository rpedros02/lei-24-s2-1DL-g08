package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for cancelling an entry in the agenda.
 */
public class CancelAnEntryGUI {

    @FXML
    // ComboBox for selecting an entry
    private ComboBox<String> comboBoxEntry;

    @FXML
    // Button for cancelling an entry in the agenda
    private Button btnCancelAnEntryInTheAgenda;

    @FXML
    // Label for displaying the result of the operation
    private Label lblResult;

    @FXML
    // Button for going back
    private Button btnBack;

    @FXML
    /**
     * Handles the action of navigating to the GSM user interface.
     * It is triggered when the GSM button is clicked.
     */
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    // The agenda from which an entry is to be cancelled
    private final Agenda agenda;

    // Controller for cancelling an entry
    private final CancelAnEntryController cancelAnEntryController;

    /**
     * Constructs a new instance of CancelAnEntryGUI.
     * It initializes the agenda and the controller.
     * @param agenda the agenda from which an entry is to be cancelled
     */
    public CancelAnEntryGUI(Agenda agenda) {
        this.agenda = agenda;
        this.cancelAnEntryController = new CancelAnEntryController(agenda);
    }

    @FXML
    /**
     * Initializes the user interface.
     * It populates the ComboBox with the entries in the agenda.
     */
    public void initialize() {
        List<Entry> entries = agenda.getEntries();
        for (Entry entry : entries) {
            comboBoxEntry.getItems().add(entry.getTitle());
        }
    }

    @FXML
    /**
     * Handles the action of cancelling an entry.
     * It is triggered when the Cancel An Entry button is clicked.
     * It validates the selected entry and cancels it if it is valid.
     * It also updates the result label with the result of the operation.
     * @param event the event that triggered the action
     */
    void handleCancelAnEntry(ActionEvent event) {
        String title = comboBoxEntry.getValue();
        String result = cancelAnEntryController.cancelEntry(title);
        lblResult.setText(result);
    }
}

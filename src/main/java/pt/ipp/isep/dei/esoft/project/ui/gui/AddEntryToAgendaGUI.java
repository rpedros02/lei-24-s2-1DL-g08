package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * The GUI for adding an entry to the agenda.
 */
/**
 * This class provides a user interface for adding an entry to the agenda.
 */
public class AddEntryToAgendaGUI {

    @FXML
    // ComboBox for selecting entries
    private ComboBox<String> cbEntries;
    @FXML
    // Button for going back
    private Button btnBack;
    @FXML
    /**
     * Handles the action of clicking the GSM button.
     * It loads the GSM user interface.
     */
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    // Repository for organizations
    private final OrganizationRepository organizationRepository;
    // Controller for the to-do list
    private final ToDoListController toDoListController;
    // Controller for the agenda
    private final AgendaController agendaController;

    /**
     * Constructs a new instance of AddEntryToAgendaGUI.
     */
    public AddEntryToAgendaGUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.organizationRepository = Repositories.getInstance().getOrganizationRepository();
    }

    @FXML
    /**
     * Initializes the user interface.
     * It populates the ComboBox with the titles of the entries from the to-do list.
     */
    private void initialize() {
        List<Entry> toDoListEntries = organizationRepository.getOrganizationByEmployeeEmail(ApplicationSession.getInstance().getCurrentSession().getUserEmail()).getEntriesFromToDoList();
        for (Entry entry : toDoListEntries) {
            cbEntries.getItems().add(entry.getTitle());
        }
    }

    @FXML
    /**
     * Handles the action of adding an entry to the agenda.
     * It validates the selected entry and adds it to the agenda if it is valid.
     */
    private void handleAddEntryToAgenda() {
        Stage stage = (Stage) cbEntries.getScene().getWindow();
        String entryTitle = cbEntries.getValue();
        if (entryTitle == null || entryTitle.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please select an entry.");
            return;
        }

        Entry entry = toDoListController.getToDoListEntry(entryTitle);
        if (entry == null) {
            showAlert(AlertType.ERROR, "Error", "Entry not found in the To-Do List.");
            return;
        }

        if (agendaController.exists(entryTitle)) {
            showAlert(AlertType.WARNING, "Warning", "Entry already exists in the Agenda.");
            return;
        }

        if (entry.getGreenSpace().isManagedByGSM()) {
            boolean success = agendaController.addEntry(entry);
            if (success) {
                showAlert(AlertType.INFORMATION, "Success", "Entry added to the Agenda successfully.");
            } else {
                showAlert(AlertType.ERROR, "Error", "Failed to add entry to the Agenda.");
            }
        } else {
            showAlert(AlertType.WARNING, "Warning", "Entry is not associated with a green space managed by the GSM.");
        }
        stage.close();
    }

    /**
     * Displays an alert with the specified type, title, and message.
     * @param alertType the type of the alert
     * @param title the title of the alert
     * @param message the message of the alert
     */
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for consulting the tasks assigned in between dates.
 */
public class ConsultTheTasksAssignInTheBetweenDatesGUI {

    @FXML
    // DatePicker for selecting the initial date
    private DatePicker initialDatePicker;

    @FXML
    // DatePicker for selecting the end date
    private DatePicker endDatePicker;

    @FXML
    // ListView for displaying the tasks
    private ListView<String> agendaListView;

    @FXML
    // Button for going back
    private Button btnBack;

    @FXML
    /**
     * Handles the action of navigating to the Collaborator Menu user interface.
     * It is triggered when the Back button is clicked.
     */
    public void handleCollaboratorMenu() {
        loadUI("/CollaboratorMenuGUI.fxml");
    }

    // Controller for the agenda
    private final AgendaController agendaController = new AgendaController();

    @FXML
    /**
     * Handles the action of consulting the tasks assigned in between dates.
     * It is triggered when the Consult button is clicked.
     * It validates the selected dates and displays the tasks assigned in between them if they are valid.
     */
    private void handleConsultTheTasksAssignInTheBetweenDates() {
        LocalDate initialDate = initialDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (initialDate == null || endDate == null) {
            showAlert("Error", "Please select both dates.", AlertType.ERROR);
            return;
        }

        Date beginDate = convertToDate(initialDate);
        Date endDateDomain = convertToDate(endDate);

        List<Entry> entries = agendaController.getEntriesBetweenDates(beginDate, endDateDomain);

        agendaListView.getItems().clear();

        if (entries.isEmpty()) {
            showAlert("No Entries", "No tasks found between the given dates.", AlertType.INFORMATION);
        } else {
            for (Entry entry : entries) {
                agendaListView.getItems().add(entry.toString());
            }
        }
    }

    /**
     * Converts a LocalDate to a Date.
     * @param localDate the LocalDate to convert
     * @return the converted Date
     */
    private Date convertToDate(LocalDate localDate) {
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    /**
     * Displays an alert with the specified type, title, and message.
     * @param title the title of the alert
     * @param message the message of the alert
     * @param alertType the type of the alert
     */
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

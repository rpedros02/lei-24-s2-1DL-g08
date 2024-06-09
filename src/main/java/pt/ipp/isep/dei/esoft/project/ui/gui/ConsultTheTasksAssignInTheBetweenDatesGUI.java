package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.time.LocalDate;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

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

    /**
     * Handles the action of navigating to the Collaborator Menu user interface.
     * It is triggered when the Back button is clicked.
     */
    @FXML
    public void handleCollaboratorMenu() {
        handleCollaborator(btnBack);
    }

    // Controller for the agenda
    private final AgendaController agendaController = new AgendaController();

    /**
     * Handles the action of consulting the tasks assigned in between dates.
     * It is triggered when the Consult button is clicked.
     * It validates the selected dates and displays the tasks assigned in between them if they are valid.
     */
    @FXML
    private void handleConsultTheTasksAssignInTheBetweenDates() {
        LocalDate initialDate = initialDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (initialDate == null || endDate == null) {
            showAlert("Please select both initial and end dates.");
            return;
        }

        Date beginDate = convertToDate(initialDate);
        Date endDateDomain = convertToDate(endDate);

        List<Entry> entries = agendaController.getEntriesBetweenDates(beginDate, endDateDomain);

        agendaListView.getItems().clear();

        if (entries.isEmpty()) {
            showAlert("No tasks were found in the selected period.");
        } else {
            for (Entry entry : entries) {
                agendaListView.getItems().add(entry.getTitle());
            }
        }
    }
}

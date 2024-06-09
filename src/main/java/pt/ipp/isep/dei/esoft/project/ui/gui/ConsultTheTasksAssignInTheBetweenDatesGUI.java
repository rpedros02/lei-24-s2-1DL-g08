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

public class ConsultTheTasksAssignInTheBetweenDatesGUI {

    @FXML
    private DatePicker initialDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ListView<String> agendaListView;
    @FXML
    private Button btnBack;
    @FXML
    public void handleCollaboratorMenu() {
        handleCollaborator(btnBack);
    }

    private final AgendaController agendaController = new AgendaController();

    @FXML
    private void handleConsultTheTasksAssignInTheBetweenDates() {
        LocalDate initialDate = initialDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (initialDate == null || endDate == null) {
            showAlert("Please select both dates.");
            return;
        }

        Date beginDate = convertToDate(initialDate);
        Date endDateDomain = convertToDate(endDate);

        List<Entry> entries = agendaController.getEntriesBetweenDates(beginDate, endDateDomain);

        agendaListView.getItems().clear();

        if (entries.isEmpty()) {
            showAlert("No tasks found between the given dates.");
        } else {
            for (Entry entry : entries) {
                agendaListView.getItems().add(entry.toString());
            }
        }
    }


}

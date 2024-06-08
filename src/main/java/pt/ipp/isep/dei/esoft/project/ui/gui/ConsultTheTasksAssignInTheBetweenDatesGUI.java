package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
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

public class ConsultTheTasksAssignInTheBetweenDatesGUI {

    @FXML
    private DatePicker initialDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ListView<String> agendaListView;

    private final AgendaController agendaController = new AgendaController();

    @FXML
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

    private Date convertToDate(LocalDate localDate) {
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

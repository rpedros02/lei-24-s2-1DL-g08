package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;


import java.util.List;

public class ConsultTheTasksAssignInTheBetweenDatesGUI {
    @FXML
    private DatePicker initialDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ListView<Entry> agendaListView;

    private final AgendaController agendaController;

    public ConsultTheTasksAssignInTheBetweenDatesGUI() {
        this.agendaController = new AgendaController();
    }

    @FXML
    private void handleConsultTheTasksAssignInTheBetweenDates() {
        Date beginDate = getDateFromDatePicker(initialDatePicker);
        Date endDate = getDateFromDatePicker(endDatePicker);

        List<Entry> entries = agendaController.getEntriesBetweenDates(beginDate, endDate);
        agendaListView.getItems().setAll(entries);
    }

    private Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getValue().getDayOfMonth();
        int month = datePicker.getValue().getMonthValue();
        int year = datePicker.getValue().getYear();
        return new Date(day, month, year);
    }
}

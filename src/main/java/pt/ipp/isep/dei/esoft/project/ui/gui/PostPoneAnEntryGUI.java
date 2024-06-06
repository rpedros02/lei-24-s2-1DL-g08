package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.List;

public class PostPoneAnEntryGUI {

    @FXML
    private ComboBox<String> cbEntries;
    @FXML
    private TextField txtDay;
    @FXML
    private TextField txtMonth;
    @FXML
    private TextField txtYear;
    @FXML
    private Button btnOK;

    private final PostponeAnEntryController postponeAnEntryController;
    private final Agenda agenda;

    public PostPoneAnEntryGUI(Agenda agenda) {
        this.agenda = agenda;
        this.postponeAnEntryController = new PostponeAnEntryController(agenda);
    }

    @FXML
    private void initialize() {
        List<Entry> entries = agenda.getEntries();
        for (Entry entry : entries) {
            cbEntries.getItems().add(entry.getTitle());
        }
    }

    @FXML
    private void handlePostPoneAnEntry() {
        String title = cbEntries.getValue();
        if (title == null || title.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please select an entry.");
            return;
        }

        String day = txtDay.getText();
        String month = txtMonth.getText();
        String year = txtYear.getText();
        if (day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please enter a valid date.");
            return;
        }

        String dateStr = String.format("%02d-%02d-%04d", Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
        Date newDate = new Date(dateStr);
        String result = postponeAnEntryController.postponeEntry(title, newDate);
        showAlert(result.contains("successfully") ? AlertType.INFORMATION : AlertType.ERROR, result.contains("successfully") ? "Success" : "Error", result);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
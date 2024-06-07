package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Date;

public class PostPoneAnEntryGUI {
    @FXML
    private Label lblTitle;

    @FXML
    private Label lblSelectEntry;

    @FXML
    private ComboBox<String> cmbSelectEntry;

    @FXML
    private Label lblPostponeDate;

    @FXML
    private TextField txtDay;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtYear;

    @FXML
    private Button btnPostponeEntry;

    private PostponeAnEntryController controller;

    public void initialize() {
        // Initialize controller with a dummy agenda for demonstration
        Agenda agenda = new Agenda();
        controller = new PostponeAnEntryController(agenda);

        // Populate ComboBox with dummy data (entries)
        cmbSelectEntry.getItems().addAll("Entry 1", "Entry 2", "Entry 3");
    }

    @FXML
    void handlePostPoneAnEntry(ActionEvent event) {
        // Get selected entry from ComboBox
        String selectedEntry = cmbSelectEntry.getValue();

        // Get day, month, and year from TextFields
        String day = txtDay.getText();
        String month = txtMonth.getText();
        String year = txtYear.getText();

        // Validate input and postpone entry
        if (validateInput(day, month, year)) {
            int intDay = Integer.parseInt(day);
            int intMonth = Integer.parseInt(month);
            int intYear = Integer.parseInt(year);
            Date newDate = new Date(intDay, intMonth, intYear);
            String result = controller.postponeEntry(selectedEntry, newDate);
            // Display result (e.g., in a dialog box)
            System.out.println(result);
        } else {
            // Display error message (e.g., in a dialog box)
            System.out.println("Invalid input.");
        }
    }

    private boolean validateInput(String day, String month, String year) {
        // Validate day, month, and year inputs (e.g., check for numeric values and valid ranges)
        return isNumeric(day) && isNumeric(month) && isNumeric(year);
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}

package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.InputMismatchException;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for postponing an entry.
 */
public class PostPoneAnEntryGUI {

    @FXML
    // ComboBox for selecting an entry
    private ComboBox<String> cmbSelectEntry;

    @FXML
    // TextField for entering the day
    private TextField txtDay;

    @FXML
    // TextField for entering the month
    private TextField txtMonth;

    @FXML
    // TextField for entering the year
    private TextField txtYear;

    @FXML
    // Button for going back
    private Button btnBack;

    @FXML
    /**
     * Handles the action of going back to the GSM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the GsmGUI.
     */
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    // Controller for postponing an entry
    private PostponeAnEntryController controller;

    /**
     * Initializes the user interface.
     * This method is called after all @FXML annotated members have been injected.
     * It sets up the ComboBox with the titles of all entries.
     */
    public void initialize() {
        OrganizationRepository orgRepo = Repositories.getInstance().getOrganizationRepository();
        ApplicationSession appSession = ApplicationSession.getInstance();
        Agenda agenda = orgRepo.getOrganizationByEmployeeEmail(appSession.getCurrentSession().getUserEmail()).getAgenda();
        this.controller = new PostponeAnEntryController(agenda);

        for (Entry entry : agenda.getEntries()) {
            cmbSelectEntry.getItems().add(entry.getTitle());
        }
    }

    @FXML
    /**
     * Handles the action of postponing an entry.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, postpones the entry if the input is valid, and displays a message indicating the result.
     */
    void handlePostPoneAnEntry() {

        String selectedEntry = cmbSelectEntry.getValue();
        String day = txtDay.getText();
        String month = txtMonth.getText();
        String year = txtYear.getText();

        try {
            if (!validateInput(day, month, year)) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input. Please enter a valid date.");
            alert.showAndWait();
            return;
        }
        int intDay = Integer.parseInt(day);
        int intMonth = Integer.parseInt(month);
        int intYear = Integer.parseInt(year);
        Date newDate = new Date(intDay, intMonth, intYear);
        boolean result = controller.postponeEntry(selectedEntry, newDate);
        if(result) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Entry postponed successfully! New date: " + result + ".");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setHeaderText(null);
            alert.setContentText("There was an error postponing the entry. Please try again.");
            alert.showAndWait();
        }
    }

    /**
     * Validates the input for the day, month, and year.
     * @param day the day input
     * @param month the month input
     * @param year the year input
     * @return true if the input is valid, false otherwise
     */
    private boolean validateInput(String day, String month, String year) {
        return isNumeric(day) && isNumeric(month) && isNumeric(year);
    }

    /**
     * Checks if a string is numeric.
     * @param str the string to check
     * @return true if the string is numeric, false otherwise
     */
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}

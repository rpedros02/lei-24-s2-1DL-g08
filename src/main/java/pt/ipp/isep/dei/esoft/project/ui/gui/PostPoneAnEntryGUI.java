package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

public class PostPoneAnEntryGUI {

    @FXML
    private ComboBox<String> cmbSelectEntry;


    @FXML
    private TextField txtDay;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtYear;


    private PostponeAnEntryController controller;

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
    private boolean validateInput(String day, String month, String year) {
        return isNumeric(day) && isNumeric(month) && isNumeric(year);
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}

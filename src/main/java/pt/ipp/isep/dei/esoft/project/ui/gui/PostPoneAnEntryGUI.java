package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for postponing an entry.
 */
public class PostPoneAnEntryGUI {

    @FXML
    // ComboBox for selecting an entry
    private ComboBox<String> cmbSelectEntry;

    @FXML
    private DatePicker dpDate;

    @FXML
    // Button for going back
    private Button btnBack;

    /**
     * Handles the action of going back to the GSM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the GsmGUI.
     */
    @FXML
    public void handleGsm() {
        handleGSM(btnBack);
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
        ToDoList toDoList = orgRepo.getOrganizationByEmployeeEmail(appSession.getCurrentSession().getUserEmail()).getTodoList();
        this.controller = new PostponeAnEntryController();

        for (Entry entry : toDoList.getEntries()) {
            cmbSelectEntry.getItems().add(entry.getTitle());
        }
    }

    /**
     * Handles the action of postponing an entry.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, postpones the entry if the input is valid, and displays a message indicating the result.
     */
    @FXML
    void handlePostPoneAnEntry() {
        OrganizationRepository orgRepo = Repositories.getInstance().getOrganizationRepository();
        ApplicationSession appSession = ApplicationSession.getInstance();
        ToDoList toDoList = orgRepo.getOrganizationByEmployeeEmail(appSession.getCurrentSession().getUserEmail()).getTodoList();

        String selectedEntry = cmbSelectEntry.getValue();
        Date date = convertToDate(dpDate.getValue());
        boolean result = controller.postponeEntry(toDoList.getEntryByTitle(selectedEntry), date);
        if(result) {
            showSuccess("Entry postponed successfully.").showAndWait();
            handleGsm();
        } else {
            showAlert("An error occurred. Please try again.").showAndWait();
        }
    }


}

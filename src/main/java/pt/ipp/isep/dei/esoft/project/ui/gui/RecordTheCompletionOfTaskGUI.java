package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;


public class RecordTheCompletionOfTaskGUI {
    private final ToDoList toDoList = Repositories.getInstance().getOrganizationRepository().getOrganizationByEmployeeEmail(UtilsGUI.getLoggedInUserEmail()).getTodoList();
    private final OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
    private final ToDoListController toDoListController = new ToDoListController();
    Organization organization = organizationRepository.getOrganizationByEmployeeEmail(getLoggedInUserEmail());


    @FXML
    private ComboBox<String> cbEntries;

    @FXML
    private Button btnBack;

    @FXML
    public void handleCollaborator() {
        UtilsGUI.handleCollaborator(btnBack);
    }

    @FXML
    public void initialize() {
        List<Entry> entries = toDoList.getEntries();
        for (Entry entry : entries) {
            if (!entry.getStatus().toString().equals("Finished")) {
                cbEntries.getItems().add(entry.getTitle());
            }
        }
    }

    @FXML
    void handleRecordTheCompletionOfTask() {
        Entry selectedEntry = organization.getEntryFromToDoList(cbEntries.getValue());
        if (toDoListController.updateStatus(selectedEntry, EStatus.FINISHED)) {
            showSuccess("Task completed successfully!").showAndWait();
        } else {
            showAlert("Error completing task. Please try again.").showAndWait();
        }
    }


}

package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

public class AddEntryToToDoListGUI {

    @FXML
    private ComboBox<String> cbGreenSpaces;

    @FXML
    private TextField txtEntryTitle;

    @FXML
    private TextField txtEntryDescription;

    @FXML
    private ComboBox<String> cbDegreeOfUrgency;

    @FXML
    private DatePicker beginDatePicker;

    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button btnBack;

    @FXML
    public void handleGsm() {
        UtilsGUI.handleGSM(btnBack);
    }

    private final GreenSpaceController greenSpaceController;
    private final ToDoListController toDoListController;
    private final AuthenticationRepository authenticationRepository;

    public AddEntryToToDoListGUI() {
        this.greenSpaceController = new GreenSpaceController();
        this.toDoListController = new ToDoListController();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    @FXML
    public void initialize() {
        String email = this.authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        UserRole role = ApplicationSession.getInstance().getCurrentSession().getUserRole();
        List<GreenSpace> allGreenSpaces = greenSpaceController.getAllGreenSpaces();
        List<String> gsmGreenSpaces = new ArrayList<>();
        boolean flag = role.hasId("admin");

        for (GreenSpace greenSpace : allGreenSpaces) {
            if(flag || greenSpace.getGsm().hasEmail(email)) {
                gsmGreenSpaces.add(greenSpace.getName());
            }
        }

        if(gsmGreenSpaces.isEmpty()) {
            showAlert("There are no Green Spaces available.").showAndWait();
            handleGsm();
        }
        cbGreenSpaces.getItems().addAll(gsmGreenSpaces);

        List<String> degreesOfUrgency = DegreeOfUrgency.getDegreesOfUrgency();
        if(!degreesOfUrgency.isEmpty()) {
            showAlert("There are no degrees of urgency available.").showAndWait();
            handleGsm();
        }

        cbDegreeOfUrgency.getItems().addAll(degreesOfUrgency);
    }

    @FXML
    public void handleAddEntryToToDoList() {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        GreenSpace selectedGreenSpace = greenSpaceRepository.getGreenSpaceByName(cbGreenSpaces.getValue());
        String entryTitle = txtEntryTitle.getText();
        String entryDescription = txtEntryDescription.getText();
        String degreeOfUrgencyString = cbDegreeOfUrgency.getValue();
        Date entryBeginDate = convertToDate(beginDatePicker.getValue());
        Date entryEndDate = convertToDate(endDatePicker.getValue());

        if (selectedGreenSpace == null || entryTitle.isEmpty() || entryDescription.isEmpty() || cbDegreeOfUrgency.getValue() == null || !entryBeginDate.isValid() || !entryEndDate.isValid()) {
            showAlert("All fields must be filled.").showAndWait();
            return;
        }

        if (toDoListController.exists(entryTitle)) {
            showAlert("An entry with the same title already exists.").showAndWait();
            return;
        }

        DegreeOfUrgency degreeOfUrgency = DegreeOfUrgency.valueOf(degreeOfUrgencyString.toUpperCase());

        Entry entry = new Entry(entryTitle, entryDescription, degreeOfUrgency, entryBeginDate, entryEndDate, EStatus.PLANNED, selectedGreenSpace);

        if (toDoListController.addEntry(entry)) {
            showSuccess("Entry added to the To-Do List.").showAndWait();

            Stage stage = (Stage) cbGreenSpaces.getScene().getWindow();
            handleGsm();
            stage.close();
        } else {
            showAlert("An error occurred while adding the entry to the To-Do List.").showAndWait();
        }
    }
}
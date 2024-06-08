package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class AddEntryToToDoListGUI {

    @FXML
    private ComboBox<GreenSpace> cbGreenSpaces;

    @FXML
    private TextField txtEntryTitle;

    @FXML
    private TextField txtEntryDescription;

    @FXML
    private ComboBox<String> cbDegreeOfUrgency;

    @FXML
    private TextField txtEntryBeginDate;

    @FXML
    private TextField txtEntryEndDate;
    @FXML
    private Button btnBack;
    @FXML
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    private GreenSpaceController greenSpaceController;
    private ToDoListController toDoListController;
    private final AuthenticationRepository authenticationRepository;

    public AddEntryToToDoListGUI() {
        this.greenSpaceController = new GreenSpaceController();
        this.toDoListController = new ToDoListController();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    @FXML
    public void initialize() {
        String email = this.authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        List<GreenSpace> allGreenSpaces = greenSpaceController.getAllGreenSpaces();
        List<GreenSpace> gsmGreenSpaces = new ArrayList<>();

        for (GreenSpace greenSpace : allGreenSpaces) {
            if (greenSpace.getGsm().equals(email)) {
                gsmGreenSpaces.add(greenSpace);
            }
        }

        cbGreenSpaces.getItems().addAll(gsmGreenSpaces);

        List<String> degreesOfUrgency = DegreeOfUrgency.getDegreesOfUrgency();
        cbDegreeOfUrgency.getItems().addAll(degreesOfUrgency);
    }

    @FXML
    public void handleAddEntryToToDoList() {
        GreenSpace selectedGreenSpace = cbGreenSpaces.getValue();
        String entryTitle = txtEntryTitle.getText();
        String entryDescription = txtEntryDescription.getText();
        String degreeOfUrgencyString = cbDegreeOfUrgency.getValue();
        String entryBeginDate = txtEntryBeginDate.getText();
        String entryEndDate = txtEntryEndDate.getText();

        if (selectedGreenSpace == null || entryTitle.isEmpty() || entryDescription.isEmpty() || cbDegreeOfUrgency.getValue() == null || entryBeginDate.isEmpty() || entryEndDate.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        if (toDoListController.exists(entryTitle)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("A entry with this name already exists.");
            alert.showAndWait();
            return;
        }

        DegreeOfUrgency degreeOfUrgency = DegreeOfUrgency.valueOf(degreeOfUrgencyString.toUpperCase());

        Entry entry = new Entry(entryTitle, entryDescription, degreeOfUrgency, Utils.dateFromString(entryBeginDate), Utils.dateFromString(entryEndDate), EStatus.PLANNED, selectedGreenSpace);

        if (toDoListController.addEntry(entry)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Entry successfully added to the To-Do List.");
            alert.showAndWait();
            // Close the current stage
            Stage stage = (Stage) cbGreenSpaces.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add entry to the To-Do List.");
            alert.showAndWait();
        }
    }
}
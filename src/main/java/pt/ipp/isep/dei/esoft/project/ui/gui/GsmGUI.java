package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class GsmGUI {

    @FXML
    private Button btnMainMenu;

    public GsmGUI() {

    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleRegisterGreenSpace() {
        loadUI("/RegisterGreenSpaceGUI.fxml");
    }

    @FXML
    public void handleAddEntryToToDoList() {
        loadUI("/AddEntryToToDoListGUI.fxml");
    }

    @FXML
    public void handleAddEntryToAgenda() {
        loadUI("/AddEntryToAgendaGUI.fxml");
    }

    @FXML
    public void handleAssignTeamToAgendaEntry() {
        loadUI("/AssignTeamToAgendaEntryGUI.fxml");
    }

    @FXML
    public void handlePostPoneAnEntry() {
        loadUI("/PostPoneAnEntryGUI.fxml");
    }

    @FXML
    public void handleCancelAnEntry() {
        loadUI("/CancelAnEntryGUI.fxml");
    }
    @FXML
    public void handleAssignOneOrMoreVehicles() {
        loadUI("/AssignOneOrMoreVehiclesGUI.fxml");
    }
    @FXML
    public void handleListAllGreenSpaces() {
        loadUI("/ListAllGreenSpacesGUI.fxml");
    }


    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        currentStage.close();
    }
}

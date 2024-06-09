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
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/RegisterGreenSpaceGUI.fxml");
        currentStage.close();
    }

    @FXML
    public void handleAddEntryToToDoList() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AddEntryToToDoListGUI.fxml");
        currentStage.close();
    }

    @FXML
    public void handleAddEntryToAgenda() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AddEntryToAgendaGUI.fxml");
        currentStage.close();
    }

    @FXML
    public void handleAssignTeamToAgendaEntry() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AssignTeamToAgendaEntryGUI.fxml");
        currentStage.close();
    }

    @FXML
    public void handlePostPoneAnEntry() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/PostPoneAnEntryGUI.fxml");
        currentStage.close();
    }

    @FXML
    public void handleCancelAnEntry() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/CancelAnEntryGUI.fxml");
        currentStage.close();
    }
    @FXML
    public void handleAssignOneOrMoreVehicles() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AssignOneOrMoreVehiclesGUI.fxml");
        currentStage.close();
    }
    @FXML
    public void handleListAllGreenSpaces() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/ListAllGreenSpacesGUI.fxml");
        currentStage.close();
    }


    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI(UtilsGUI.getCurrentRoleXml());
        currentStage.close();
    }
}

package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for managing green spaces.
 */
public class GsmGUI {

    @FXML
    // Main menu button
    private Button btnMainMenu;

    /**
     * Constructor for GsmGUI.
     */
    public GsmGUI() {

    }

    /**
     * Initializes the user interface.
     */
    @FXML
    public void initialize() {

    }

    /**
     * Handles the action of registering a green space.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the RegisterGreenSpaceGUI.
     */
    @FXML
    public void handleRegisterGreenSpace() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/RegisterGreenSpaceGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of adding an entry to the to-do list.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the AddEntryToToDoListGUI.
     */
    @FXML
    public void handleAddEntryToToDoList() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AddEntryToToDoListGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of adding an entry to the agenda.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the AddEntryToAgendaGUI.
     */
    @FXML
    public void handleAddEntryToAgenda() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AddEntryToAgendaGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of assigning a team to an agenda entry.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the AssignTeamToAgendaEntryGUI.
     */
    @FXML
    public void handleAssignTeamToAgendaEntry() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AssignTeamToAgendaEntryGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of postponing an entry.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the PostPoneAnEntryGUI.
     */
    @FXML
    public void handlePostPoneAnEntry() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/PostPoneAnEntryGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of cancelling an entry.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the CancelAnEntryGUI.
     */
    @FXML
    public void handleCancelAnEntry() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/CancelAnEntryGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of assigning one or more vehicles.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the AssignOneOrMoreVehiclesGUI.
     */
    @FXML
    public void handleAssignOneOrMoreVehicles() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AssignOneOrMoreVehiclesGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of listing all green spaces.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the ListAllGreenSpacesGUI.
     */
    @FXML
    public void handleListAllGreenSpaces() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/ListAllGreenSpacesGUI.fxml");
        currentStage.close();
    }

    /**
     * Handles the action of returning to the main menu.
     * It is triggered when the corresponding button is clicked.
     * It closes the current stage and loads the main menu user interface.
     */
    @FXML
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        loadUI("/AdminGUI.fxml");
        currentStage.close();
    }
}

package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GsmGUI {

    @FXML
    private Button btnRegisterGreenSpace;
    @FXML
    private Button btnAddEntryToToDoList;
    @FXML
    private Button btnAddEntryToAgenda;
    @FXML
    private Button btnAssignTeamToAgendaEntry;
    @FXML
    private Button btnPostPoneAnEntryInTheAgenda;
    @FXML
    private Button btnCancelAnEntryInTheAgenda;
    @FXML
    private Button btnMainMenu;

    private GreenSpaceController controller;

    public GsmGUI() {
        this.controller = new GreenSpaceController();
    }

    @FXML
    public void initialize() {
        // Initialize UI components if needed
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
    public void handleMainMenu() {
        Stage currentStage = (Stage) btnMainMenu.getScene().getWindow();
        currentStage.close();
        loadUI("/MainMenuGUI.fxml");
    }

    private void loadUI(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

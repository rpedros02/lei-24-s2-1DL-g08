package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
        loadUI("/MainMenuGUI.fxml");
        currentStage.close();
    }

    private void loadUI(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            showAlert(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

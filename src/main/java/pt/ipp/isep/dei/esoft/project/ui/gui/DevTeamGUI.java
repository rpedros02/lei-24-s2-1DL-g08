package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class DevTeamGUI {

    @FXML
    private Label lblMessage;

    @FXML
    private Button btnReturn;

    @FXML
    public void initialize() {
        lblMessage.setText("Bem-vindo à equipa de desenvolvimento!");
    }

    @FXML
    public void handleReturn() {
        Stage stage = (Stage) this.btnReturn.getScene().getWindow();
        loadUI("/MainMenuGUI.fxml");
        stage.close();
    }

}

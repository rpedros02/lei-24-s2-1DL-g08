package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DevTeamGUI {

    @FXML
    private Label lblMessage;

    @FXML
    public void initialize() {
        lblMessage.setText("Bem-vindo à equipa de desenvolvimento!");
    }
}

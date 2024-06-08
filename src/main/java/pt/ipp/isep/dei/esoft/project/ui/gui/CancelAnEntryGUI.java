package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.List;

public class CancelAnEntryGUI {

    @FXML
    private ComboBox<String> comboBoxEntry;

    @FXML
    private Button btnCancelAnEntryInTheAgenda;

    @FXML
    private Label lblResult;

    private final Agenda agenda;
    private final CancelAnEntryController cancelAnEntryController;

    public CancelAnEntryGUI(Agenda agenda) {
        this.agenda = agenda;
        this.cancelAnEntryController = new CancelAnEntryController(agenda);
    }

    @FXML
    public void initialize() {

        List<Entry> entries = agenda.getEntries();
        for (Entry entry : entries) {
            comboBoxEntry.getItems().add(entry.getTitle());
        }
    }

    @FXML
    void handleCancelAnEntry(ActionEvent event) {
        String title = comboBoxEntry.getValue();
        String result = cancelAnEntryController.cancelEntry(title);
        lblResult.setText(result);
    }
}

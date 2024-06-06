package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.List;
public class CancelAnEntryGUI {

    @FXML
    private ComboBox<String> comboBoxEntry;

    @FXML
    private TextField txtDay;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtYear;

    @FXML
    private Button btnCancelAnEntryInTheAgenda;

    private final Agenda agenda;
    private final CancelAnEntryController cancelAnEntryController;

    public CancelAnEntryGUI(Agenda agenda) {
        this.agenda = agenda;
        this.cancelAnEntryController = new CancelAnEntryController(agenda);
    }

    @FXML
    public void initialize() {
        // Inicializar combobox com títulos de entradas da agenda
        List<Entry> entries = agenda.getEntries();
        for (Entry entry : entries) {
            comboBoxEntry.getItems().add(entry.getTitle());
        }
    }

    @FXML
    void handleCancelAnEntry(ActionEvent event) {
        String title = comboBoxEntry.getValue();
        String day = txtDay.getText();
        String month = txtMonth.getText();
        String year = txtYear.getText();

        // Implementar lógica para validar e cancelar a entrada com o título fornecido
        String result = cancelAnEntryController.cancelEntry(title);

    }
}
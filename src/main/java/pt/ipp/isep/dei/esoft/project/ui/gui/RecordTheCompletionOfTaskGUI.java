package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

public class RecordTheCompletionOfTaskGUI {

    @FXML
    private ComboBox<Entry> cbEntries;

    @FXML
    private ListView<Entry> agendaListView;

    private final AgendaController agendaController = new AgendaController();

    @FXML
    public void initialize() {
        // Carregar tarefas disponíveis
        cbEntries.getItems().addAll(/* Chame o método adequado para obter as tarefas disponíveis */);
    }

    @FXML
    void handleRecordTheCompletionOfTask(ActionEvent event) {
        Entry selectedEntry = cbEntries.getValue();
        // Chame o método adequado do controller para registrar a conclusão da tarefa selecionada
    }
}

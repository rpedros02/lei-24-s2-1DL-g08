//package pt.ipp.isep.dei.esoft.project.ui.gui;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
//import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
//import pt.ipp.isep.dei.esoft.project.domain.Entry;
//
//import java.util.List;
//
//public class AddEntryToAgendaGUI {
//
//    @FXML
//    private ComboBox<String> cbEntries;
//    @FXML
//    private Button btnAddEntry;
//
//    private final ToDoListController toDoListController;
//    private final AgendaController agendaController;
//
//    public AddEntryToAgendaGUI() {
//        this.toDoListController = new ToDoListController(); // Assumindo que há um construtor padrão
//        this.agendaController = new AgendaController();
//    }
//
//    @FXML
//    private void initialize() {
//        List<Entry> toDoListEntries = toDoListController.getToDoListEntry(); // Método atualizado
//        for (Entry entry : toDoListEntries) {
//            cbEntries.getItems().add(entry.getTitle());
//        }
//    }
//
//    @FXML
//    private void handleAddEntryToAgenda() {
//        String entryTitle = cbEntries.getValue();
//        if (entryTitle == null || entryTitle.isEmpty()) {
//            showAlert(AlertType.ERROR, "Error", "Please select an entry.");
//            return;
//        }
//
//        Entry entry = toDoListController.getToDoListEntry(entryTitle);
//        if (entry == null) {
//            showAlert(AlertType.ERROR, "Error", "Entry not found in the To-Do List.");
//            return;
//        }
//
//        if (agendaController.exists(entryTitle)) {
//            showAlert(AlertType.WARNING, "Warning", "Entry already exists in the Agenda.");
//            return;
//        }
//
//        if (entry.getGreenSpace().isManagedByGSM()) {
//            boolean success = agendaController.addEntry(entry);
//            if (success) {
//                showAlert(AlertType.INFORMATION, "Success", "Entry added to the Agenda successfully.");
//            } else {
//                showAlert(AlertType.ERROR, "Error", "Failed to add entry to the Agenda.");
//            }
//        } else {
//            showAlert(AlertType.WARNING, "Warning", "Entry is not associated with a green space managed by the GSM.");
//        }
//    }
//
//    private void showAlert(AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}
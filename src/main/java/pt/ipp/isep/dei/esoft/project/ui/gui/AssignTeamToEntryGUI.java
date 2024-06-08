package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.Optional;

public class AssignTeamToEntryGUI {

    @FXML
    private TextField entryTitleTextField;
    @FXML
    private TextField teamNameTextField;
    @FXML
    private Button btnAssignTeam;

    private final ToDoListController toDoListController;
    private final AgendaController agendaController;

    public AssignTeamToEntryGUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
    }

    @FXML
    private void handleAssignTeam() {
        String entryTitle = entryTitleTextField.getText();
        if (entryTitle == null || entryTitle.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please enter the title of the entry.");
            return;
        }

        Optional<Entry> entryOpt = agendaController.getEntryByTitle(entryTitle);
        if (entryOpt.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Entry not found in the Agenda.");
            return;
        }

        Entry entry = entryOpt.get();

        String teamName = teamNameTextField.getText();
        if (teamName == null || teamName.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please enter the name of the team.");
            return;
        }

        Optional<Team> teamOpt = toDoListController.getTeam(teamName);
        if (teamOpt.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Team not found.");
            return;
        }

        Team team = teamOpt.get();
        entry.setAssignedTeam(team);
        showAlert(AlertType.INFORMATION, "Success", "Team assigned to the entry successfully.");

        sendNotificationToTeamMembers(team);
    }

    private void sendNotificationToTeamMembers(Team team) {
        StringBuilder notificationMessage = new StringBuilder("Sending notification to team members:\n");
        for (String member : team.getMembers()) {
            notificationMessage.append("Notification sent to: ").append(member).append("\n");
        }
        showAlert(AlertType.INFORMATION, "Notifications", notificationMessage.toString());
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


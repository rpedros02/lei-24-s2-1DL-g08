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

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for assigning a team to an entry.
 */
public class AssignTeamToEntryGUI {

    @FXML
    // TextField for inputting the entry title
    private TextField entryTitleTextField;
    @FXML
    // TextField for inputting the team name
    private TextField teamNameTextField;
    @FXML
    // Button for going back
    private Button btnBack;
    @FXML
    /**
     * Handles the action of navigating to the GSM user interface.
     * It is triggered when the GSM button is clicked.
     */
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    // Controller for the to-do list
    private final ToDoListController toDoListController;
    // Controller for the agenda
    private final AgendaController agendaController;
    // Service for sending emails
    private final EmailService emailService;

    /**
     * Constructs a new instance of AssignTeamToEntryGUI.
     * It initializes the controllers and the email service.
     */
    public AssignTeamToEntryGUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.emailService = EmailServiceController.createEmailServiceFromConfig();
    }

    @FXML
    /**
     * Handles the action of assigning a team to an entry.
     * It validates the input data and assigns the team to the entry if it is valid.
     * It also sends a notification to the team members.
     */
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

    /**
     * Sends a notification to the members of a team.
     * @param team the team to which the notification is sent
     */
    private void sendNotificationToTeamMembers(Team team) {
        StringBuilder notificationMessage = new StringBuilder("Sending notification to team members:\n");
        for (String member : team.getMembers()) {
            emailService.sendEmail(member, "Team Assignment Notification", "You have been assigned to a team.");
            notificationMessage.append("Notification sent to: ").append(member).append("\n");
        }
        showAlert(AlertType.INFORMATION, "Notifications", notificationMessage.toString());
    }

    /**
     * Displays an alert with the specified type, title, and message.
     * @param alertType the type of the alert
     * @param title the title of the alert
     * @param message the message of the alert
     */
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

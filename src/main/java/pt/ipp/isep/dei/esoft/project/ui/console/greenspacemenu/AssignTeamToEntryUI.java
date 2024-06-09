package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.gui.EmailService;
import pt.ipp.isep.dei.esoft.project.ui.gui.EmailServiceController;

import java.util.Optional;
import java.util.Scanner;


/**
 * This class provides the user interface for assigning a team to an entry.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AssignTeamToEntryUI implements Runnable {
    /**
     * The controller that handles the To-Do List.
     */
    private final ToDoListController toDoListController;

    /**
     * The controller that handles the agenda.
     */
    private final AgendaController agendaController;

    /**
     * The scanner to read user input.
     */
    private final Scanner scanner;

    /**
     * The service to send emails.
     */
    private final EmailService emailService;

    /**
     * Constructs a new AssignTeamToEntryUI instance.
     * It initializes the controllers, the scanner, and the email service.
     */
    public AssignTeamToEntryUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.scanner = new Scanner(System.in);
        this.emailService = EmailServiceController.createEmailServiceFromConfig();
    }

    /**
     * Constructs a new AssignTeamToEntryUI instance with provided controllers and email service.
     *
     * @param toDoListController The ToDoListController instance.
     * @param agendaController   The AgendaController instance.
     * @param emailService       The EmailService instance.
     */
    public AssignTeamToEntryUI(ToDoListController toDoListController, AgendaController agendaController, EmailService emailService) {
        this.toDoListController = toDoListController;
        this.agendaController = agendaController;
        this.emailService = emailService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interface for assigning a team to an entry.
     * It prompts the user to enter the title of the entry and the name of the team.
     * It then checks if the entry and the team exist.
     * If the entry and the team exist, it assigns the team to the entry and sends a notification to the team members.
     * If the entry or the team does not exist, it prints an error message.
     */
    public void run() {
        System.out.println("Enter the title of the entry to assign a team:");
        String entryTitle = scanner.nextLine();

        Optional<Entry> entryOpt = agendaController.getEntryByTitle(entryTitle);
        if (entryOpt.isEmpty()) {
            System.out.println("Entry not found in the Agenda.");
            return;
        }

        Entry entry = entryOpt.get();

        System.out.println("Enter the name of the team to assign:");
        String teamName = scanner.nextLine();
        Optional<Team> teamOpt = toDoListController.getTeam(teamName);
        if (teamOpt.isEmpty()) {
            System.out.println("Team not found.");
            return;
        }

        Team team = teamOpt.get();

        entry.setAssignedTeam(team);
        System.out.println("Team assigned to the entry successfully.");

        sendNotificationToTeamMembers(team);
    }

    /**
     * Sends a notification to all team members.
     * The notification informs the team members that they have been assigned to a team.
     *
     * @param team The team to which the notification is sent.
     */
    private void sendNotificationToTeamMembers(Team team) {
        System.out.println("Sending notification to team members:");
        for (String member : team.getMembers()) {
            emailService.sendEmail(member, "Team Assignment Notification", "You have been assigned to a team.");
            System.out.println("Notification sent to: " + member);
        }
    }

    /**
     * Returns the controller that handles the agenda.
     *
     * @return The AgendaController.
     */
    public AgendaController getAgendaController() {
        return agendaController;
    }
}


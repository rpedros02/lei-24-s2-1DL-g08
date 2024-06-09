package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.Scanner;

/**
 * This class provides the user interface for generating a team.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AddEntryToAgendaUI implements Runnable {
    /**
     * The controller that handles the To-Do List.
     */
    private final ToDoListController toDoListController;
    /**
     * The controller that handles the agenda.
     */
    private final AgendaController agendaController;
    /**
     * The controller that handles the generation of teams.
     */
    private final GenerateTeamController generateTeamController;
    /**
     * The scanner to read user input.
     */
    private final Scanner scanner;

    /**
     * Constructs a new instance of AddEntryToAgendaUI.
     * It initializes the controllers and the scanner.
     */
    public AddEntryToAgendaUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.generateTeamController = new GenerateTeamController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the user interface for adding an entry to the agenda.
     * It prompts the user to enter the title of the entry.
     * It then checks if the entry exists in the To-Do List and the agenda, and if it is associated with a green space managed by the GSM.
     * If the entry passes all checks, it adds the entry to the agenda and assigns a team to the entry.
     * It then sends a notification to the team members regarding the assignment of the entry.
     */
    public void run() {
        System.out.println("Enter the title of the entry to add to the agenda:");
        String entryTitle = scanner.nextLine();
        Entry entry = toDoListController.getToDoListEntry(entryTitle);
        if (entryTitle == null) {
            return;
        }
        if (entry == null) {
            System.out.println("Entry not found in the To-Do List.");
            return;
        }

        if (agendaController.exists(entryTitle)) {
            System.out.println("Entry already exists in the Agenda.");
            return;
        }

        if (!entry.getGreenSpace().isManagedByGSM()) {
            System.out.println("Entry is not associated with a green space managed by the GSM.");
            return;
        }

        boolean success = agendaController.addEntry(entry);
        if (success) {
            System.out.println("Entry added to the Agenda successfully.");
            System.out.println("Enter the number of members for the team:");
            int numMembers = Integer.parseInt(scanner.nextLine());
            Team team = generateTeamController.generateTeam(numMembers, numMembers, entry.getSkills());
            boolean teamAssigned = agendaController.assignTeamToEntry(entryTitle, team);
            if (teamAssigned) {
                System.out.println("Team assigned to the entry successfully.");

                sendNotificationToTeamMembers(team, entryTitle);
            } else {
                System.out.println("Failed to assign team to the entry.");
            }
        } else {
            System.out.println("Failed to add entry to the Agenda.");
        }
    }

    /**
     * Gets the ToDoListController.
     *
     * @return The ToDoListController instance.
     */
    public ToDoListController getToDoListController() {
        return toDoListController;
    }

    /**
     * Gets the AgendaController.
     *
     * @return The AgendaController instance.
     */
    public AgendaController getAgendaController() {
        return agendaController;
    }

    /**
     * Gets the GenerateTeamController.
     *
     * @return The GenerateTeamController instance.
     */
    public GenerateTeamController getGenerateTeamController() {
        return generateTeamController;
    }

    /**
     * Gets the scanner.
     *
     * @return The Scanner instance.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Sends a notification to the team members regarding the assignment of the entry.
     *
     * @param team       The team to notify.
     * @param entryTitle The title of the assigned entry.
     */
    private void sendNotificationToTeamMembers(Team team, String entryTitle) {
        System.out.println("Sending notification to team members:");
        for (String member : team.getMembers()) {
            System.out.println("Notification sent to: " + member + " regarding the assignment of entry " + entryTitle);
        }
    }
}

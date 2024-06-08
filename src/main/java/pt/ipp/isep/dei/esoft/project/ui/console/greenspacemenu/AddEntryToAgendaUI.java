package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.Scanner;

public class AddEntryToAgendaUI implements Runnable {
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;
    private final GenerateTeamController generateTeamController;
    private final Scanner scanner;

    /**
     * Constructs a new AddEntryToAgendaUI instance.
     */
    public AddEntryToAgendaUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.generateTeamController = new GenerateTeamController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the user interface for adding an entry to the agenda.
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

    public ToDoListController getToDoListController() {
        return toDoListController;
    }

    public AgendaController getAgendaController() {
        return agendaController;
    }

    public GenerateTeamController getGenerateTeamController() {
        return generateTeamController;
    }

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


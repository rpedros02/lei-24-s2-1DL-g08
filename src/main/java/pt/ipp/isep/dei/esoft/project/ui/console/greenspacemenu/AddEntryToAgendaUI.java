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

    public AddEntryToAgendaUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.generateTeamController = new GenerateTeamController();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Enter the title of the entry to add to the agenda:");
        String entryTitle = scanner.nextLine();

        Entry entry = toDoListController.getToDoListEntry(entryTitle);
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


            int minMembers = 1;
            int maxMembers = 5;
            Team team = generateTeamController.generateTeam(minMembers, maxMembers, entry.getSkills());


            boolean teamAssigned = agendaController.assignTeamToEntry(entryTitle, team);
            if (teamAssigned) {
                System.out.println("Team assigned to the entry successfully.");
            } else {
                System.out.println("Failed to assign team to the entry.");
            }
        } else {
            System.out.println("Failed to add entry to the Agenda.");
        }
    }
}





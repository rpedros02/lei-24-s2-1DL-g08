package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.Scanner;
import java.util.Optional; // Adicionando a importação do Optional

public class AssignTeamToEntryUI implements Runnable {
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;
    private final GenerateTeamController generateTeamController;
    private final Scanner scanner;

    public AssignTeamToEntryUI() {
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.generateTeamController = new GenerateTeamController();
        this.scanner = new Scanner(System.in);
    }

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

    private void sendNotificationToTeamMembers(Team team) {

        System.out.println("Sending notification to team members:");
        for (String member : team.getMembers()) {
            System.out.println("Notification sent to: " + member);
        }
    }
}

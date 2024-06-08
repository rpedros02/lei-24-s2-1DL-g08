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

    public AssignTeamToEntryUI(ToDoListController toDoListController, AgendaController agendaController, GenerateTeamController generateTeamController) {
        this.toDoListController = toDoListController;
        this.agendaController = agendaController;
        this.generateTeamController = generateTeamController;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Enter the title of the entry to assign a team:");
        String entryTitle = scanner.nextLine();

        // Verificar se a entrada existe na agenda
        Optional<Entry> entryOpt = agendaController.getEntryByTitle(entryTitle);
        if (entryOpt.isEmpty()) {
            System.out.println("Entry not found in the Agenda.");
            return;
        }

        Entry entry = entryOpt.get(); // Alteração aqui, usando get() em vez de orElseThrow

        // Obter a equipe a ser atribuída
        System.out.println("Enter the name of the team to assign:");
        String teamName = scanner.nextLine();
        Optional<Team> teamOpt = toDoListController.getTeam(teamName);
        if (teamOpt.isEmpty()) {
            System.out.println("Team not found.");
            return;
        }

        Team team = teamOpt.get(); // Alteração aqui, usando get() em vez de orElseThrow

        // Atribuir a equipe à entrada na agenda
        entry.setAssignedTeam(team);
        System.out.println("Team assigned to the entry successfully.");

        // Enviar mensagem para os membros da equipe
        sendNotificationToTeamMembers(team);
    }

    private void sendNotificationToTeamMembers(Team team) {
        // Implementação básica: apenas exibir a mensagem
        System.out.println("Sending notification to team members:");
        for (String member : team.getMembers()) {
            System.out.println("Notification sent to: " + member);
        }
    }
}

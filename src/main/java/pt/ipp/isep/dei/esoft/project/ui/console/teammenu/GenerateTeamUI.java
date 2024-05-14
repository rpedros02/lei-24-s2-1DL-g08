package pt.ipp.isep.dei.esoft.project.ui.console.teammenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Scanner;

public class GenerateTeamUI implements Runnable {

    private final GenerateTeamController controller;
    private int teamSize;
    private SkillsRepository listOfSkills;

    public GenerateTeamUI() {
        controller = new GenerateTeamController();
    }

    private GenerateTeamController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Team ------------------------");
        System.out.println("\n\n--- Min Team Size ------------------------");
        int minMembers = requestTeamSize();

        System.out.println("\n\n--- Max Team Size ------------------------");
        int maxMembers = requestTeamSize();

        System.out.println("\n\n--- Team Skills ------------------------");
        String[] teamSkills = requestTeamSkills();

        submitData(minMembers, maxMembers, teamSkills);
    }

    private void submitData(int minMembers, int maxMembers, String[] teamSkills) {
        Team team = getController().generateTeam(minMembers, maxMembers, teamSkills);
        if (team != null) {
            System.out.println(team);
            System.out.println("\nTeam successfully generated!");
        } else {
            System.out.println("\nTeam not generated!");
        }
    }

    private int requestTeamSize() {
        int number = 0;
        while (number <= 0) {
            number = Utils.readIntegerFromConsole("Team Size: ");
        }

        return number;
    }

    private String[] requestTeamSkills() {
        Scanner input = new Scanner(System.in);
        System.out.print("Team Skills: ");
        return input.nextLine().split(",");
    }

}

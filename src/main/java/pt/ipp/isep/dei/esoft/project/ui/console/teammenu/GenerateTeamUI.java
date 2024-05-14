package pt.ipp.isep.dei.esoft.project.ui.console.teammenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
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
        List<Skill> teamSkills = requestTeamSkills();

        submitData(minMembers, maxMembers, teamSkills);
    }

    private void submitData(int minMembers, int maxMembers, List<Skill> teamSkills) {
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

    private List<Skill> requestTeamSkills() {
        Scanner input = new Scanner(System.in);
        int option = 1;
        List<Skill> teamSkills = new ArrayList<>();
        while (option != 0){
            System.out.println("Skill Id:");
            int id = input.nextInt();
            System.out.println("Skill Name: ");
            String skillName = input.nextLine();
            teamSkills.add(new Skill(id,skillName));
            System.out.println("Add another Skill? (0 - No, 1 - Yes)");
            option = input.nextInt();
        }
        System.out.print("Team Skills: ");
        return teamSkills;
    }

}

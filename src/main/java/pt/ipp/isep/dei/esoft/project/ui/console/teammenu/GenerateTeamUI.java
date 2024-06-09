package pt.ipp.isep.dei.esoft.project.ui.console.teammenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides the user interface for generating a team.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class GenerateTeamUI implements Runnable {

    // Controller for generating a team
    private final GenerateTeamController controller;
    // Size of the team
    private int teamSize;
    // Repository of skills
    private SkillsRepository listOfSkills;

    /**
     * Constructs a new instance of GenerateTeamUI.
     */
    public GenerateTeamUI() {
        controller = new GenerateTeamController();
    }

    /**
     * Returns the controller for generating a team.
     * @return the controller for generating a team
     */
    private GenerateTeamController getController() {
        return controller;
    }

    /**
     * Starts the user interface for generating a team.
     * It prompts the user to input the minimum and maximum team size and the team skills.
     * It then submits the data to generate a team.
     */
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

    /**
     * Submits the data to generate a team.
     * It generates a team with the given minimum and maximum team size and team skills.
     * It then requests the user to confirm the team generation.
     * @param minMembers the minimum team size
     * @param maxMembers the maximum team size
     * @param teamSkills the team skills
     */
    private void submitData(int minMembers, int maxMembers, List<Skill> teamSkills) {
        Team team = getController().generateTeam(minMembers, maxMembers, teamSkills);

        if(requestConfirmation(team)){
            if (team != null) {
                System.out.println("\nTeam successfully generated!");
            } else {
                System.out.println("\nTeam not generated!");
            }
        }
    }

    /**
     * Requests the user to confirm the team generation.
     * If the user confirms, it registers the team.
     * @param team the team to be confirmed
     * @return true if the user confirms, false otherwise
     */
    private boolean requestConfirmation(Team team){
        boolean confirmation = Utils.getBooleanAnswer("Do you want to confirm the team generation?");
        if(confirmation){
            getController().registerTeam(team);
            return true;
        }
        return false;
    }

    /**
     * Requests the user to input the team size.
     * @return the team size
     */
    private int requestTeamSize() {
        int number = 0;
        while (number <= 0) {
            number = Utils.readIntegerFromConsole("Size: ");
        }
        return number;
    }

    /**
     * Requests the user to input the team skills.
     * @return the team skills
     */
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
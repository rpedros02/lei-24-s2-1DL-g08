package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * This class provides the user interface for assigning skills to a collaborator.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AssignSkillUI implements Runnable {

    /**
     * The controller that handles the skill assignment process.
     */
    private final AssignSkillController assignSkillController;

    /**
     * The list of skills to be assigned.
     */
    private List<Skill> skillList;

    /**
     * The list of collaborators to whom skills can be assigned.
     */
    private List<Collaborator> collaboratorList;

    /**
     * The collaborator to whom skills are being assigned.
     */
    private Collaborator collaborator;

    /**
     * The repository of collaborators.
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructs a new AssignSkillUI.
     * It initializes the controller.
     */
    public AssignSkillUI() {
        this.assignSkillController = new AssignSkillController();
    }

    /**
     * Returns the controller that handles the skill assignment process.
     *
     * @return the controller that handles the skill assignment process
     */
    private AssignSkillController getAssignSkillController() {
        return assignSkillController;
    }

    /**
     * Starts the skill assignment process.
     * It first requests the ID of the collaborator from the user.
     * It then validates the ID and requests the skills to be assigned.
     * The process is currently incomplete and needs to be finished.
     */
    public void run() {
        System.out.println("\n\n--- Assign a Skill to a Collaborator ---");
        int idNumber = requestIDFromUser();

        boolean validation = validateIDFromUser(idNumber);

//        while(!validation){
//            System.out.println("ID Number of Collaborator Not Valid!!");
//            validation = validateIDFromUser(idNumber);
//        }

        requestSkills();

        //requestData();
        //submitData();
    }

    /**
     * Requests the skills to be assigned from the user.
     * It displays a list of skills and allows the user to select one or more skills.
     */
    private void requestSkills() {
        List<Skill> showSkills = assignSkillController.getSkillList();

        boolean answer = true;

        while (answer) {
            Skill chosenSkill = (Skill) Utils.showAndSelectOne(showSkills, "Select a skill: ");
            skillList.add(chosenSkill);
            answer = Utils.getBooleanAnswer("Do you want to select more skills?");
        }

    }

    /**
     * Validates the ID of the collaborator.
     * This method is currently incomplete and needs to be finished.
     *
     * @param idNumber the ID of the collaborator
     * @return false
     */
    private boolean validateIDFromUser(int idNumber) {
        // verifica se o id existe, chama o metodo do controller que chama o repositorio
        // retorna verdadeiro ou falso
        return false;
    }

    /**
     * Requests the ID of the collaborator from the user.
     *
     * @return the ID of the collaborator
     */
    private int requestIDFromUser() {
        return Utils.readIntegerFromConsole("Input Collaborator ID: ");
    }

    /**
     * Requests the data for the skill assignment process.
     * This method is currently commented out and needs to be finished.
     */
    private void requestData() {
        collaborator = requestCollaborator();
        requestSelectedSkills(collaborator);
    }

    /**
     * Submits the data for the skill assignment process.
     * This method is currently empty and needs to be finished.
     */
    private void submitData() {

    }

    /**
     * Requests the collaborator from the user.
     * This method is currently commented out and needs to be finished.
     *
     * @return the collaborator
     */
    private Collaborator requestCollaborator() {
        collaboratorList = assignSkillController.getCollabortorList();
        System.out.println("--- Select a Collaborator by his IDNumber ---");
        selectCollaborator(collaboratorList);
        return collaborator;
    }

    /**
     * Allows the user to select a collaborator from a list of collaborators.
     * This method is currently commented out and needs to be finished.
     *
     * @param collaboratorList the list of collaborators
     */
    private void selectCollaborator(List<Collaborator> collaboratorList) {
        int i = 1;
        for (Collaborator collaborator : collaboratorList) {
            System.out.println("  " + i + " - " + collaborator.getName() + " - " + collaborator.getIdNumber());
            i++;
        }
        int answer = -1;
        int listSize = collaboratorList.size();
        do {
            Scanner input = new Scanner(System.in);
            while (answer < 0 || answer > listSize) {
                answer = input.nextInt();
            }
            if (answer != 0) {
                collaborator = collaboratorList.get(answer - 1);
            }
        } while (answer != 0);
    }

    /**
     * Requests the skills to be assigned from the user.
     * This method is currently commented out and needs to be finished.
     *
     * @param collaborator the collaborator to whom skills are being assigned
     */
    private void requestSelectedSkills(Collaborator collaborator) {
        skillList = assignSkillController.getSkillList();
        displaySkillList(skillList);
        selectSkill(collaborator, skillList);

    }

    /**
     * Displays a list of skills.
     * This method is currently commented out and needs to be finished.
     *
     * @param skillList the list of skills
     */
    private void displaySkillList(List<Skill> skillList) {
        int i = 1;
        for (Skill skill : skillList) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }

    /**
     * Allows the user to select a skill from a list of skills.
     * This method is currently commented out and needs to be finished.
     *
     * @param collaborator the collaborator to whom skills are being assigned
     * @param skillList the list of skills
     */
    private void selectSkill(Collaborator collaborator, List<Skill> skillList) {
        int answer = -1;
        int listSize = skillList.size();
        do {
            Scanner input = new Scanner(System.in);
            while (answer < 0 || answer > listSize) {
                answer = input.nextInt();
            }
            if (answer != 0) {
                assignSkillController.assignSkills(collaborator, (List<Skill>) skillList.get(answer - 1));
            }
        } while (answer != 0);
    }

}
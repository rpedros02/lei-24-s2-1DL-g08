package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class AssignSkillUI implements Runnable {

    private final AssignSkillController assignSkillController;
    private List<Skill> skillList;
    private List<Collaborator> collaboratorList;
    private Collaborator collaborator;

    private CollaboratorRepository collaboratorRepository;

    public AssignSkillUI() {
        this.assignSkillController = new AssignSkillController();
    }

    private AssignSkillController getAssignSkillController() {
        return assignSkillController;
    }


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

    private void requestSkills() {
        List<Skill> showSkills = assignSkillController.getSkillList();

        boolean answer = true;

        while (answer) {
            Skill chosenSkill = (Skill) Utils.showAndSelectOne(showSkills, "Select a skill: ");
            skillList.add(chosenSkill);
            answer = Utils.getBooleanAnswer("Do you want to select more skills?");
        }

    }

    private boolean validateIDFromUser(int idNumber) {
        // verifica se o id existe, chama o metodo do controller que chama o repositorio
        // retorna verdadeiro ou falso
        return false;
    }

    private int requestIDFromUser() {
        return Utils.readIntegerFromConsole("Input Collaborator ID: ");
    }

    private void requestData() {
        collaborator = requestCollaborator();
        requestSelectedSkills(collaborator);
    }

    private void submitData() {

    }

    private Collaborator requestCollaborator() {
        collaboratorList = assignSkillController.getCollabortorList();
        System.out.println("--- Select a Collaborator by his IDNumber ---");
        selectCollaborator(collaboratorList);
        return collaborator;
    }

    private void requestSelectedSkills(Collaborator collaborator) {
        skillList = assignSkillController.getSkillList();
        displaySkillList(skillList);
        selectSkill(collaborator, skillList);

    }

    private void displaySkillList(List<Skill> skillList) {
        int i = 1;
        for (Skill skill : skillList) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }


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
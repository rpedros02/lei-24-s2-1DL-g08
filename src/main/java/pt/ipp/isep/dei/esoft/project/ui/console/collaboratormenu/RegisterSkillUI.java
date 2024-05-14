package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class RegisterSkillUI implements Runnable {

    private final RegisterSkillController ctrl = new RegisterSkillController();
    private int skillId;
    private String name;

    public void run() {
        System.out.println("---------------- REGISTER SKILL ----------------\n");

            skillId = Utils.readIntegerFromConsole("Insert skill Id:");
            name = Utils.readLineFromConsole("Insert the skill name:");

            getConfirmation();

    }

    private void getConfirmation() {
        System.out.println("----------------- SKILL DATA -----------------\n");

        System.out.printf("Skill Id: %d\n", skillId);
        System.out.printf("Skill name: %s\n", name);

        System.out.println();

        boolean input = Utils.getBooleanAnswer("Do you wish to proceed?:\n");

        if (input) {
            System.out.println("Registration complete\n");
            // Volta ao menu das operações (ainda nao implementado)
            if (ctrl.registerSkill(skillId,name)){
                System.out.println("Skill register successfully.");

            } else {
                System.out.printf("Skill not valid.");

            }
        } else {
            System.out.println("Cancelling registration\n");
            run();
        }
    }
}


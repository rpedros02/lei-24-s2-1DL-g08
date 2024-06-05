package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class RegisterSkillUI implements Runnable {

    private final RegisterSkillController ctrl = new RegisterSkillController();
    private int skillId;
    private String name;

    public void run() {
        boolean flag = true;

        System.out.println("---------------- REGISTER SKILL ----------------\n");
        while (flag) {
            skillId = Utils.readIntegerFromConsole("Insert skill Id:");
            if (ctrl.skillIdExists(skillId)) {
                System.out.println("Skill Id already exists.");
                boolean flag2 = Utils.getBooleanAnswer("Do you wish to see next available Id?");
                if (flag2) {
                    skillId = ctrl.getNextSkillId();
                    System.out.printf("Next available Id: %d\n", skillId);
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }
            name = Utils.readLineFromConsole("Insert skill name:");
            getConfirmation(!flag);
        }
    }

    private void getConfirmation(boolean flag) {
        if (flag) {
            System.out.println("----------------- SKILL DATA -----------------\n\n");
            System.out.printf("Skill Id: %d\n", skillId);
            System.out.printf("Skill name: %s", name);
            System.out.println();
            boolean input = Utils.getBooleanAnswer("Do you wish to proceed?:\n");
            if (input && ctrl.registerSkill(skillId, name)) {
                System.out.println("Skill register successfully.");
                return;
            } else {
                System.out.println("Skill not valid.");
            }
            System.out.println("Cancelling registration\n");
        }
    }
}


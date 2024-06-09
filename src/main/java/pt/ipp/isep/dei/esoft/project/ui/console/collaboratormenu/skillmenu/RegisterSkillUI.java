package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

/**
 * This class provides the user interface for registering a skill.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class RegisterSkillUI implements Runnable {

    /**
     * The controller that handles the skill registration process.
     */
    private final RegisterSkillController ctrl = new RegisterSkillController();

    /**
     * The ID of the skill to be registered.
     */
    private int skillId;

    /**
     * The name of the skill to be registered.
     */
    private String name;

    /**
     * Starts the skill registration process.
     * It prompts the user to enter the ID and name of the skill.
     * If the entered ID already exists, it allows the user to see the next available ID.
     * It then asks the user for confirmation before registering the skill.
     */
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

    /**
     * Asks the user for confirmation before registering the skill.
     * If the user confirms, it attempts to register the skill.
     * If the skill registration is successful, it prints a success message.
     * If the skill registration is not successful, it prints an error message.
     *
     * @param flag a flag indicating whether to ask for confirmation
     */
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


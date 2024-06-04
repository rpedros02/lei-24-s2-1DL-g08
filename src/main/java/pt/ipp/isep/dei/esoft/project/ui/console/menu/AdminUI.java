package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {

    private final Agenda agenda;

    public AdminUI() {
        this.agenda = Repositories.getAgenda();
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Vehicle Menu", new VehicleMenuUI()));
        options.add(new MenuItem("Team Menu", new TeamMenuUI()));
        options.add(new MenuItem("Green Space Menu", new GreenSpaceMenuUI(agenda)));
        options.add(new MenuItem("Agenda Menu", new AgendaMenuUI()));
        options.add(new MenuItem("Collaborator Menu", new CollaboratorMenuUI()));
        options.add(new MenuItem("Create a Job", new CreateJobUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}